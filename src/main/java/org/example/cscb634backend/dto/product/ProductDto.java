package org.example.cscb634backend.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
	private Long id;
	
	@NotBlank
	@NotNull
	private String name;
	private String imageUrl;
	
	@NotBlank
	@NotNull
	private String skuNumber;
	
	public ProductDto() {
	}
	
	public ProductDto(Long id, String name, String imageUrl, String skuNumber) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.skuNumber = skuNumber;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
