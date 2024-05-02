package org.example.cscb634backend.service.product;

import org.example.cscb634backend.dto.product.ProductOfferDto;
import org.example.cscb634backend.entity.product.ProductOffer;

public interface ProductOfferService {
	ProductOffer createProductOffer(ProductOfferDto offerDto);
}
