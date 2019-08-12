package com.flash.app.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flash.app.dao.*;
import com.flash.app.entity.*;
import com.flash.app.model.*;
import com.flash.app.util.ApplicationConstants;

@Service
public class FlashSaleService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	FlashSaleRegistrationRepository flashSaleRegistrationRepository;
	
	@Autowired
	FlashSaleProductRepository flashSaleProductRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	FlashSaleEventRepository flashSaleEventRepository;
	
	
	public boolean registerUser(Users user, Integer flashSaleId) {
		
		boolean userExists = checkIfUserExists(user.getUsername());
		boolean saleExists = checkIfFlashSaleExists(flashSaleId);
		
		if(userExists || !saleExists) {
			return false;
		}
		
		userRepository.save(user);
		return true;
	}
	
	public Iterable<Product> getItems(Integer flashSaleId) {
		
		List<Integer> products = new ArrayList<Integer>();
		FlashSaleEvent fs = new FlashSaleEvent();
		fs.setFlashSaleId(flashSaleId);
		
		List<FlashSaleProduct> fp = flashSaleProductRepository.findByFlashSaleEvent(fs);
		
		for (FlashSaleProduct flashSaleProduct : fp) 
			products.add(flashSaleProduct.getProduct().getId());
		
		Iterable<Integer> iterable = products;
		return productRepository.findAllById(iterable);
	}
	
	public Product getItem(Integer productId) {
		
		boolean productAvailable = checkIfProductExists(productId);
		return productAvailable ? productRepository.findById(productId).get() : null;
	}
	
	public boolean checkIfUserExists(String userId) {
		return userRepository.findById(userId).isPresent();
	}
	
	public boolean checkIfProductExists(Integer productId) {
		return productRepository.findById(productId).isPresent();
	}
	
	public boolean checkIfFlashSaleExists(Integer flashSaleId) {
		return flashSaleEventRepository.findById(flashSaleId).isPresent();
	}
	
	
	public String placeOrder(PurchaseOrder purchaseOrder) {
		
		String userId = purchaseOrder.getUsername();
		Integer productId = purchaseOrder.getProductId();
		Integer flashSaleId = purchaseOrder.getFlashSaleId();
		Integer quantity = purchaseOrder.getQuantity();
		
		Users user= new Users();
		user.setUsername(userId);
		
		FlashSaleEvent fs = new FlashSaleEvent();
		fs.setFlashSaleId(flashSaleId);
		
		List<FlashSaleRegistration> userObj = flashSaleRegistrationRepository.findByUser(user);
		List<FlashSaleEvent> event = flashSaleEventRepository.findByStatus(true);
		
		if(userObj.isEmpty())
			return ApplicationConstants.ALREADY_REGISTERED;
		
		if(event.isEmpty())
			return ApplicationConstants.INVALID_EVENT;
		
		FlashSaleRegistration regUser = userObj.get(0);
				
		if(regUser.getStatus().equals(RegistrationStatus.PURCHASED.toString()))
			return ApplicationConstants.ALREADY_BUYER; 
		
		Product product = productRepository.findById(productId).get();
		FlashSaleProduct flashSaleProduct  = flashSaleProductRepository.findByProduct(product).get(0);
		
		if(flashSaleProduct.getQuantity() == 0 || flashSaleProduct.getQuantity() < quantity)
			return ApplicationConstants.OUT_OF_STOCK;  
		
		Orders order = new Orders();
		order.setUser(user);
		order.setCreatedAt(Calendar.getInstance().getTime());
		order.setLastUpdated(Calendar.getInstance().getTime());
		order.setProduct(product);
		order.setStatus(OrderStatus.APPROVED.toString());
		Double bill = product.getPrice() * quantity;
		order.setBillAmount(bill);
		
		orderRepository.save(order);

		flashSaleProduct.setQuantity(flashSaleProduct.getQuantity() - 1); 
		flashSaleProductRepository.save(flashSaleProduct);
	
		regUser.setStatus(RegistrationStatus.PURCHASED.toString());
		flashSaleRegistrationRepository.save(regUser);
		
		return ApplicationConstants.ORDER_SUCCESS;
		
	}
}
