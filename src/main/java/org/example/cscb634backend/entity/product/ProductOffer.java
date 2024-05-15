package org.example.cscb634backend.entity.product;

import jakarta.persistence.*;
import jdk.jfr.Category;
import lombok.Getter;
import lombok.Setter;
import org.example.cscb634backend.entity.account.Cart;
import org.example.cscb634backend.entity.account.PurchaseHistory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
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
	
	@Column(name="approved")
	private boolean approved;
	
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
	
	
	public boolean isApproved() {
		return approved;
	}
	
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getPrice() {
		return price;
	}
	
	public void setPrice(Long price) {
		this.price = price;
	}
	
	public Integer getDiscount() {
		return discount;
	}
	
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
	public LocalDateTime getDiscountStartingDate() {
		return discountStartingDate;
	}
	
	public void setDiscountStartingDate(LocalDateTime discountStartingDate) {
		this.discountStartingDate = discountStartingDate;
	}
	
	public LocalDateTime getDiscountEndingDate() {
		return discountEndingDate;
	}
	
	public void setDiscountEndingDate(LocalDateTime discountEndingDate) {
		this.discountEndingDate = discountEndingDate;
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
	
	public boolean isAvailable() {
		return available;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	public List<Cart> getCartList() {
		return cartList;
	}
	
	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}
	
	public List<PurchaseHistory> getPurchaseHistoryList() {
		return purchaseHistoryList;
	}
	
	public void setPurchaseHistoryList(List<PurchaseHistory> purchaseHistoryList) {
		this.purchaseHistoryList = purchaseHistoryList;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Supplier getSupplier() {
		return supplier;
	}
	
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
}
