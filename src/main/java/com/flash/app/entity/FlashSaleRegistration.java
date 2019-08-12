package com.flash.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class FlashSaleRegistration {

	@OneToOne
	@JoinColumn(name = "username")
	Users user;
	private String status;
	@OneToOne
	@JoinColumn(name = "flashSaleEventId")
	private FlashSaleEvent flashSaleEvent;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FlashSaleEvent getFlashSaleEvent() {
		return flashSaleEvent;
	}

	public void setFlashSaleEvent(FlashSaleEvent flashSaleEvent) {
		this.flashSaleEvent = flashSaleEvent;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
}
