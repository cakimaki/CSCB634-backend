package org.example.cscb634backend.entity.product;

import jakarta.persistence.*;
import jdk.jfr.Category;
import org.example.cscb634backend.entity.account.Cart;
import org.example.cscb634backend.entity.account.PurchaseHistory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "product_offer")
public class ProductOffer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "price")
	private Long price;
	
	@Column(name = "discount")
	private Integer discount;
	
	@Column(name = "discount_start")
	private LocalDateTime discountStartingDate;
	
	@Column(name = "discount_end")
	private LocalDateTime discountEndingDate;
	
	@Column(name  = "offer_start")
	private LocalDateTime offerStart;
	
	@Column(name = "offer_end")
	private LocalDateTime offerEnd;
	
	@Column(name = "quantity")
	private Long quantity;
	
	@Column(name = "available")
	private boolean available;
	
	@OneToMany(mappedBy = "productOffer")
	private List<Cart> cartList;
	
	@OneToMany(mappedBy = "productOffer")
	private List<PurchaseHistory> purchaseHistoryList;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
}
