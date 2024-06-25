package org.example.cscb634backend.service.product;

import org.example.cscb634backend.dto.product.ProductDto;
import org.example.cscb634backend.dto.product.ProductOfferDto;
import org.example.cscb634backend.entity.auth.MyUser;
import org.example.cscb634backend.entity.product.Product;
import org.example.cscb634backend.entity.product.ProductOffer;
import org.example.cscb634backend.entity.product.Supplier;
import org.example.cscb634backend.repository.auth.MyUserRepository;
import org.example.cscb634backend.repository.product.ProductOfferRepository;
import org.example.cscb634backend.repository.product.ProductRepository;
import org.example.cscb634backend.repository.product.SupplierRepository;
import org.example.cscb634backend.service.auth.TokenService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductOfferServiceImpl implements ProductOfferService {
	private final ProductOfferRepository productOfferRepository;
	private final TokenService tokenService;
	private final SupplierRepository supplierRepository;
	private final MyUserRepository userRepository;
	
	private final ProductRepository productRepository;
	private final ProductService productService;
	public ProductOfferServiceImpl(ProductOfferRepository productOfferRepository, TokenService tokenService, SupplierRepository supplierRepository, MyUserRepository userRepository, ProductRepository productRepository, ProductService productService) {
		this.productOfferRepository = productOfferRepository;
		this.tokenService = tokenService;
		this.supplierRepository = supplierRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.productService = productService;
	}
	
	
	@Override
	public ProductOfferDto createProductOffer(ProductOfferDto offerDto, String email) {
		//extracting the user from jwt token
		if(email.isEmpty() || email.isBlank()){
			throw new IllegalStateException("email is empty.");
		}
		MyUser user = userRepository.findByEmail(email)
				.orElseThrow(()-> new IllegalStateException("The email is not found. (from jwt info)"));
		
		//supplier logic
		Supplier supplier = supplierRepository.findByName(user.getSupplier().getName())
				.orElseThrow(()-> new IllegalStateException("No supplier found linked to this account email."));
		if (!supplier.isValid()) {
			throw new IllegalStateException("Supplier is not permitted to post yet.");
		}
		LocalDateTime offerStart = Optional.ofNullable(offerDto.getOfferStart()).orElse(LocalDateTime.now());
		//Assign the product offer
		//Product product = offerDto.getProduct();
		//productRepository.save(product);
		
		// Create and save the product
		
		Product product = productService.createProduct(offerDto.getProduct());
		
		// Create and save the product offer
		ProductOffer productOffer = new ProductOffer();
		productOffer.setProduct(product);
		productOffer.setAvailable(offerDto.isAvailable());
		productOffer.setQuantity(offerDto.getQuantity());
		productOffer.setPrice(offerDto.getPrice());
		productOffer.setOfferStart(offerStart);
		productOffer.setSupplier(supplier);
		productOffer.setApproved(true);
		//productOffer.setApproved(supplier.isSoloPosting());
		
		ProductOffer finalProductOffer = productOfferRepository.save(productOffer);
		
		return convertToDto(finalProductOffer);
		
	}
	@Override
	public List<ProductOfferDto> fetchAllValidOffers(){
		List<ProductOffer> productOfferList = productOfferRepository.getAllValidOffers();
		return productOfferList.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}
	
	
	@Override
	public void deleteOffer(Long id){
		ProductOffer offer = productOfferRepository.findById(id)
				.orElseThrow(()-> new NoSuchElementException("No offer found with such id."));
		
		offer.setDeleted(true);
		productOfferRepository.save(offer);
	}
	private ProductOfferDto convertToDto(ProductOffer productOffer) {
		ProductOfferDto dto = new ProductOfferDto();
		
		// Set the product fields
		ProductDto productDto = new ProductDto();
		productDto.setId(productOffer.getProduct().getId());
		productDto.setName(productOffer.getProduct().getName());
		productDto.setSkuNumber(productOffer.getProduct().getSkuNumber());
		productDto.setImageUrl(productOffer.getProduct().getImage_url());
		dto.setProduct(productDto);
		
		// Set the available flag
		dto.setAvailable(productOffer.isAvailable());
		
		// Set the discount, if present
		if (productOffer.getDiscount() != null) {
			dto.setDiscount(productOffer.getDiscount());
		}
		
		// Set the offer start date, if present
		if (productOffer.getOfferStart() != null) {
			dto.setOfferStart(productOffer.getOfferStart());
		}
		
		// Set the offer end date, if present
		if (productOffer.getOfferEnd() != null) {
			dto.setOfferEnd(productOffer.getOfferEnd());
		}
		
		// Set the quantity, if present
		if (productOffer.getQuantity() != null) {
			dto.setQuantity(productOffer.getQuantity());
		}
		
		// Set the price
		dto.setPrice(productOffer.getPrice());
		
		return dto;
	}
	
	private Product convertToEntity(ProductDto productDto) {
		Product product = new Product();
		
		product.setName(productDto.getName());
		product.setSkuNumber(productDto.getSkuNumber());
		product.setImage_url(productDto.getImageUrl());
		
		return product;
	}
}
