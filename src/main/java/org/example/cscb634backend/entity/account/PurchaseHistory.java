package org.example.cscb634backend.entity.account;


import jakarta.persistence.*;

@Entity
@Table(name = "purchase_history")
public class PurchaseHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "quantity")
	private Long quantity;
	
	@Column(name = "date")
	
	
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
}
