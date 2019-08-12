package com.flash.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flash.app.entity.Product;
import com.flash.app.entity.Users;
import com.flash.app.model.PurchaseOrder;
import com.flash.app.service.FlashSaleService;
import com.flash.app.util.*;

@RestController
@RequestMapping("/ecomm/webapp/")
public class FlashSaleController {
	
	@Autowired
	private FlashSaleService flashSaleService;
	
	@PostMapping("/register/flashSale/{flashSaleId}")
	public ResponseEntity<String> registerUserForSale(@RequestBody Users user, @PathVariable("flashSaleId") Integer flashSaleId){
		
		boolean status = flashSaleService.registerUser(user,flashSaleId);
		if(!status) 
			return new ResponseEntity<String>(ApplicationConstants.FAILED,HttpStatus.OK);
		
		return new ResponseEntity<String>(ApplicationConstants.SUCCESS,HttpStatus.OK);
		
	}
	
	@RequestMapping("/product/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable("productId") Integer id) {
		
		Product product =  flashSaleService.getItem(id);
		if(product==null)
			return new ResponseEntity<Product>(new Product(), HttpStatus.BAD_REQUEST);
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);	
	}
	
	@RequestMapping("/products/flashSale/{flashSaleId}")
	public ResponseEntity<Iterable<Product>> getAllFlashSaleProducts(@PathVariable("flashSaleId") Integer flashSaleId){
		
		Iterable<Product> products = flashSaleService.getItems(flashSaleId);
		return new ResponseEntity<Iterable<Product>>(products, HttpStatus.OK);
	}
	
	@PostMapping("/order/checkout")
	public ResponseEntity<String> purchase(@RequestBody PurchaseOrder purchaseOrder) {
		
		String responseStatus =  flashSaleService.placeOrder(purchaseOrder);
		return new ResponseEntity<String>(responseStatus, HttpStatus.OK);	
	}
	
	
	
}
