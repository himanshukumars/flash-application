package com.flash.app.service;

import com.flash.app.entity.Product;
import com.flash.app.entity.Users;
import com.flash.app.model.PurchaseOrder;

public interface IFlashSaleService {

	public boolean registerUser(Users user, Integer flashSaleId);
	public Iterable<Product> getItems(Integer flashSaleId);
	public Product getItem(Integer productId);
	public String placeOrder(PurchaseOrder purchaseOrder);
}
