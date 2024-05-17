package org.example.cscb634backend.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
	private Long id;
	private String name;
	private String imageUrl;
	private String skuNumber;
	
	public ProductDto(Long id,String name, String imageUrl, String skuNumber) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.skuNumber = skuNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getSkuNumber() {
		return skuNumber;
	}
	
	public void setSkuNumber(String skuNumber) {
		this.skuNumber = skuNumber;
	}
}
