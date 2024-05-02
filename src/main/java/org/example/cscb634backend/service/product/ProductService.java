package org.example.cscb634backend.service.product;

import org.example.cscb634backend.dto.product.ProductDto;
import org.example.cscb634backend.entity.product.Product;

public interface ProductService {
	Product createProduct(ProductDto dto);
}
