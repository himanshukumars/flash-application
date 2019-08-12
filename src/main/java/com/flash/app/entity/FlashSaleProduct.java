package com.flash.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FlashSaleProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;
	@ManyToOne
	@JoinColumn(name = "productId")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "flashSaleEventId")
	private FlashSaleEvent flashSaleEvent;
	private Integer quantity;	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public FlashSaleEvent getFlashSaleEvent() {
		return flashSaleEvent;
	}
	public void setFlashSaleEvent(FlashSaleEvent flashSaleEvent) {
		this.flashSaleEvent = flashSaleEvent;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
}
