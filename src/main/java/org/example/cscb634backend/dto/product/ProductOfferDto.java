package org.example.cscb634backend.dto.product;

import lombok.Getter;
import lombok.Setter;
import org.example.cscb634backend.entity.product.Product;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductOfferDto {
	private Product product;//name, image, sku
	
	private boolean available;
	
	private long discount;
	private LocalDateTime discount_start;
	private LocalDateTime discount_end;
	private Long price;
	
	private Long quantity;
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public long getDiscount() {
		return discount;
	}
	
	public void setDiscount(long discount) {
		this.discount = discount;
	}
	
	public LocalDateTime getDiscount_start() {
		return discount_start;
	}
	
	public void setDiscount_start(LocalDateTime discount_start) {
		this.discount_start = discount_start;
	}
	
	public LocalDateTime getDiscount_end() {
		return discount_end;
	}
	
	public void setDiscount_end(LocalDateTime discount_end) {
		this.discount_end = discount_end;
	}
	
	public Long getPrice() {
		return price;
	}
	
	public void setPrice(Long price) {
		this.price = price;
	}
	
	public Long getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
}
