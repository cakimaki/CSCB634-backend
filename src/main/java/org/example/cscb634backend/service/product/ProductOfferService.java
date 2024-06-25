package org.example.cscb634backend.service.product;

import org.example.cscb634backend.dto.product.ProductOfferDto;
import org.example.cscb634backend.entity.product.ProductOffer;

import java.util.List;

public interface ProductOfferService {
	
	ProductOfferDto createProductOffer(ProductOfferDto offerDto, String jwt);
	
	
	
	List<ProductOfferDto> fetchAllValidOffers();
	
	void deleteOffer(Long id);
}
