package com.ecom.ecom_proj.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"userid", "prodId"})})
public class UserCart {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid") // FK to UserCred
	private UserCred user;
	
	@ManyToOne
    @JoinColumn(name = "prodId", referencedColumnName = "id") // FK to Product
    private Product product;
	
	private int quantity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserCred getUser() {
		return user;
	}
	public void setUser(UserCred user) {
		this.user = user;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public UserCart() {
		
	}
	public UserCart(int id, UserCred user, Product product, int quantity) {
		super();
		this.id = id;
		this.user = user;
		this.product = product;
		this.quantity = quantity;
	}
	
}
