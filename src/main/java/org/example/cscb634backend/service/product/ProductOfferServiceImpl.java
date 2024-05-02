package org.example.cscb634backend.service.product;

import org.example.cscb634backend.dto.product.ProductOfferDto;
import org.example.cscb634backend.entity.product.ProductOffer;
import org.example.cscb634backend.repository.product.ProductOfferRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductOfferServiceImpl implements ProductOfferService{
	private final ProductOfferRepository productOfferRepository;
	
	public ProductOfferServiceImpl(ProductOfferRepository productOfferRepository) {
		this.productOfferRepository = productOfferRepository;
	}
	
/*	public ProductOffer createProductOffer(ProductOfferDto dto){
	
	}*/
}
