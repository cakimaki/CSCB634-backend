package org.example.cscb634backend.service.product;

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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductOfferServiceImpl implements ProductOfferService {
	private final ProductOfferRepository productOfferRepository;
	private final TokenService tokenService;
	private final SupplierRepository supplierRepository;
	private final MyUserRepository userRepository;
	
	private final ProductRepository productRepository;
	public ProductOfferServiceImpl(ProductOfferRepository productOfferRepository, TokenService tokenService, SupplierRepository supplierRepository, MyUserRepository userRepository, ProductRepository productRepository) {
		this.productOfferRepository = productOfferRepository;
		this.tokenService = tokenService;
		this.supplierRepository = supplierRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
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
		ProductOffer productOffer = new ProductOffer();
		//productOffer.setProduct(offerDto.getProduct());
		productOffer.setAvailable(offerDto.isAvailable());
		productOffer.setQuantity(offerDto.getQuantity());
		productOffer.setPrice(offerDto.getPrice());
		productOffer.setOfferStart(offerStart);
		productOffer.setSupplier(supplier);
		productOffer.setApproved(supplier.isSoloPosting());
		//save the final productOffer
		ProductOffer finalProductOffer = productOfferRepository.save(productOffer);
		
		//return it as dto
		return convertToDto(finalProductOffer);
		
	}
	@Override
	public List<ProductOfferDto> fetchAllValidOffers(){
		List<ProductOffer> productOfferList = productOfferRepository.getAllValidOffers();
		return productOfferList.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}
	private ProductOfferDto convertToDto(ProductOffer productOffer) {
		ProductOfferDto dto = new ProductOfferDto();
		
		dto.setAvailable(productOffer.isAvailable());
		dto.setOfferStart(productOffer.getOfferStart());
		//dto.setProduct(productOffer.getProduct());
		//dto.setSupplier(productOffer.getSupplier());
		dto.setPrice(productOffer.getPrice());
		
		return dto;
	}
}
