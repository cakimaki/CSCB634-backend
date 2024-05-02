package org.example.cscb634backend.service.product;

import org.example.cscb634backend.dto.product.ProductDto;
import org.example.cscb634backend.entity.product.Product;
import org.example.cscb634backend.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{
	private final ProductRepository productRepository;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	
	@Override
	public Product createProduct(ProductDto dto){
		Product product= new Product();
		product.setName(dto.getName());
		product.setImage_url(dto.getImageUrl());
		product.setSkuNumber(dto.getSkuNumber());
		
		return productRepository.save(product);
	}
	
	
}
