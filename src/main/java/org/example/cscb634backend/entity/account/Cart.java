package org.example.cscb634backend.entity.account;


import jakarta.persistence.*;
import org.example.cscb634backend.entity.product.ProductOffer;

@Entity
@Table(name = "cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "quantity")
	private Long quantity;
	
	@ManyToOne
	@JoinColumn(name = "product_offer_id")
	private ProductOffer productOffer;
	
	//todo
	// User relationship manyToOne
	// (finish user)(auth)
}

