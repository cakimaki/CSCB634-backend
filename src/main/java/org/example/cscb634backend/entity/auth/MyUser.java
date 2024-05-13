package org.example.cscb634backend.entity.auth;

import jakarta.persistence.*;
import org.example.cscb634backend.entity.account.Cart;
import org.example.cscb634backend.entity.account.PurchaseHistory;

import java.util.List;

@Entity
@Table(name = "user")
public class MyUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true, nullable = true)
	private String username;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = true,unique = true)
	private String email;
	
	@Column(name = "deleted",nullable = false)
	private boolean deleted;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private List<Role> roleList;
	
	@OneToMany(mappedBy = "myUser")
	private List<PurchaseHistory> purchaseHistoryList;
	
	@OneToMany(mappedBy = "myUser")
	private List<Cart> cartList;
	
	public boolean getDeleted() {
		return deleted;
	}
	
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public List<Role> getRoleList() {
		return roleList;
	}
	
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	public List<PurchaseHistory> getPurchaseHistoryList() {
		return purchaseHistoryList;
	}
	
	public void setPurchaseHistoryList(List<PurchaseHistory> purchaseHistoryList) {
		this.purchaseHistoryList = purchaseHistoryList;
	}
	
	public List<Cart> getCartList() {
		return cartList;
	}
	
	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}
}

