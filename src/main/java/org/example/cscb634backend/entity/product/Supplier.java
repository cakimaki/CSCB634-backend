package org.example.cscb634backend.entity.product;

import jakarta.persistence.*;
import org.example.cscb634backend.entity.auth.MyUser;

import java.util.List;

@Entity
@Table(name = "supplier")
public class Supplier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name= "name")
	private String name;
	
	@Column(name = "solo_posting")
	private boolean soloPosting;
	@Column(name = "valid")
	private boolean valid;
	@OneToMany(mappedBy = "supplier")
	private List<ProductOffer> productOfferList;
	
	@OneToMany(mappedBy = "supplier")
	private List<MyUser> userList;
	

	
	public Supplier() {
	}
	
	public List<MyUser> getUserList() {
		return userList;
	}
	
	public void setUserList(List<MyUser> userList) {
		this.userList = userList;
	}
	
	public boolean isSoloPosting() {
		return soloPosting;
	}
	
	public void setSoloPosting(boolean soloPosting) {
		this.soloPosting = soloPosting;
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	public List<ProductOffer> getProductOfferList() {
		return productOfferList;
	}
	
	public void setProductOfferList(List<ProductOffer> productOfferList) {
		this.productOfferList = productOfferList;
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
}
