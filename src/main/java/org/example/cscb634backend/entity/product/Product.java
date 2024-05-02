package org.example.cscb634backend.entity.product;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "image_url")
	private String image_url;
	
	@Column(name="sku_number")
	private String skuNumber;
	
	@OneToMany(mappedBy = "product")
	private List<ProductOffer> productOfferList;
	
	public Product() {
	}
	
	public Product(String name, String image_url, String skuNumber) {
		this.name = name;
		this.image_url = image_url;
		this.skuNumber = skuNumber;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getImage_url() {
		return image_url;
	}
	
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
	public String getSkuNumber() {
		return skuNumber;
	}
	
	public void setSkuNumber(String skuNumber) {
		this.skuNumber = skuNumber;
	}
	
	public List<ProductOffer> getProductOfferList() {
		return productOfferList;
	}
	
	public void setProductOfferList(List<ProductOffer> productOfferList) {
		this.productOfferList = productOfferList;
	}
}
