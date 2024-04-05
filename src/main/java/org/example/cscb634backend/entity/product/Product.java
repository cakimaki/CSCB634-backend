package org.example.cscb634backend.entity.product;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	private String image_url;
	
	@Column(name="sku_number")
	private String skuNumber;
	
	public Product() {
	}
	
	public Product(String name, String image_url, String skuNumber) {
		this.name = name;
		this.image_url = image_url;
		this.skuNumber = skuNumber;
	}
}
