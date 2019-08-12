package com.flash.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FlashSaleEvent {

	@Id
	@Column(name = "flashSaleEventId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String flashSaleName;
	private boolean status;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Integer getFlashSaleId() {
		return id;
	}
	public void setFlashSaleId(Integer flashSaleId) {
		this.id = flashSaleId;
	}
	public String getFlashSaleName() {
		return flashSaleName;
	}
	public void setFlashSaleName(String flashSaleName) {
		this.flashSaleName = flashSaleName;
	}
}
