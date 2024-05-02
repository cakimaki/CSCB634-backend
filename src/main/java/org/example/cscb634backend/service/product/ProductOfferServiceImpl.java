package org.example.cscb634backend.service.product;

import org.example.cscb634backend.dto.product.ProductOfferDto;
import org.example.cscb634backend.entity.product.ProductOffer;
import org.example.cscb634backend.repository.product.ProductOfferRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductOfferServiceImpl implements ProductOfferService{
	private final ProductOfferRepository productOfferRepository;
	
	public ProductOfferServiceImpl(ProductOfferRepository productOfferRepository) {
		this.productOfferRepository = productOfferRepository;
	}
	
	
	@Override
	public ProductOffer createProductOffer(ProductOfferDto offerDto){
		ProductOffer productOffer = new ProductOffer();
		productOffer.setProduct(offerDto.getProduct());
		productOffer.setAvailable(offerDto.isAvailable());
		productOffer.setPrice(offerDto.getPrice());
		productOffer.setOfferStart(LocalDateTime.now());//now.
		
		return productOfferRepository.save(productOffer);
	}
}
