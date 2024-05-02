package org.example.cscb634backend.dto.product;

import org.example.cscb634backend.entity.product.ProductOffer;

import java.util.List;

public class SupplierDto {
	private Long id;
	private String name;
	
	private boolean soloPosting;
	private boolean valid;
	private List<ProductOffer> productOfferList;
	
	public boolean isSoloPosting() {
		return soloPosting;
	}
	
	public void setSoloPosting(boolean soloPosting) {
		this.soloPosting = soloPosting;
	}
	
	public boolean getValid() {
		return valid;
	}
	
	public void setValid(boolean valid) {
		this.valid = valid;
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
	
	public List<ProductOffer> getProductOfferList() {
		return productOfferList;
	}
	
	public void setProductOfferList(List<ProductOffer> productOfferList) {
		this.productOfferList = productOfferList;
	}
}
