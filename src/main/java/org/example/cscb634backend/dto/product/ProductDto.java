package org.example.cscb634backend.dto.product;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
	private String name;
	private String imageUrl;
	private String skuNumber;
	
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
