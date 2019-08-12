package com.flash.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flash.app.entity.FlashSaleProduct;
import com.flash.app.entity.FlashSaleEvent;
import java.util.List;
import com.flash.app.entity.Product;

@Repository
public interface FlashSaleProductRepository extends CrudRepository<FlashSaleProduct, String> {

	List<FlashSaleProduct> findByFlashSaleEvent(FlashSaleEvent flashsaleevent);
	List<FlashSaleProduct> findByProduct(Product product);
}
