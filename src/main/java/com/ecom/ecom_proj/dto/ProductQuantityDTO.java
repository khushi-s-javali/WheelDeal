package com.ecom.ecom_proj.dto;

import com.ecom.ecom_proj.model.Product;

public class ProductQuantityDTO {
	private Product product;
	private int quantity;
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
	public ProductQuantityDTO(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}
	public ProductQuantityDTO() {
		
	}

}
