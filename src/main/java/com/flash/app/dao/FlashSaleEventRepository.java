package com.flash.app.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.flash.app.entity.FlashSaleEvent;

public interface FlashSaleEventRepository extends CrudRepository<FlashSaleEvent, Integer> {

	   List<FlashSaleEvent> findByStatus(Boolean status);

}
