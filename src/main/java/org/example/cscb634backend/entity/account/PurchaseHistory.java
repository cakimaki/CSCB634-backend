package org.example.cscb634backend.entity.account;


import jakarta.persistence.*;
import org.example.cscb634backend.entity.auth.MyUser;
import org.example.cscb634backend.entity.product.ProductOffer;

import java.time.LocalDateTime;

@Entity
@Table(name = "purchase_history")
public class PurchaseHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "quantity")
	private Long quantity;
	
	@Column(name = "date")
	private LocalDateTime date;
	
	@ManyToOne
	@JoinColumn(name ="product_offer_id")
	private ProductOffer productOffer;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private MyUser myUser;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
}
