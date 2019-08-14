package com.flash.app.model;

public class PurchaseOrder {
	
	private Integer productId;
	private String username;
	private Integer quantity;
	private Integer flashSaleId;
	
	public PurchaseOrder(Integer productId, String username, Integer quantity, Integer flashSaleId) {
		this.productId = productId;
		this.username = username;
		this.quantity = quantity;
		this.flashSaleId = flashSaleId;
	}	
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getFlashSaleId() {
		return flashSaleId;
	}
	public void setFlashSaleId(Integer flashSaleId) {
		this.flashSaleId = flashSaleId;
	}
	
	
}
