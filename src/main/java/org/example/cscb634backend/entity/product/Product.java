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
}
