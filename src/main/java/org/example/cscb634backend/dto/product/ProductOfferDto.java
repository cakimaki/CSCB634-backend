package org.example.cscb634backend.dto.product;

import lombok.Getter;
import lombok.Setter;
import org.example.cscb634backend.entity.product.Product;
import org.example.cscb634backend.entity.product.Supplier;

import java.time.LocalDateTime;

public class ProductOfferDto {
	private ProductDto product;//name, image, sku
	
	private boolean available;
	
	private long discount;
	private LocalDateTime discount_start;
	private LocalDateTime discount_end;
	private Long price;
	
	private LocalDateTime offerStart;
	private LocalDateTime offerEnd;
	private Long quantity;
	private SupplierDto supplier;
	
	public ProductDto getProduct() {
		return product;
	}
	
	public void setProduct(ProductDto product) {
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
	
	public LocalDateTime getOfferStart() {
		return offerStart;
	}
	
	public void setOfferStart(LocalDateTime offerStart) {
		this.offerStart = offerStart;
	}
	
	public LocalDateTime getOfferEnd() {
		return offerEnd;
	}
	
	public void setOfferEnd(LocalDateTime offerEnd) {
		this.offerEnd = offerEnd;
	}
	
	public Long getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	public SupplierDto getSupplier() {
		return supplier;
	}
	
	public void setSupplier(SupplierDto supplier) {
		this.supplier = supplier;
	}
}
