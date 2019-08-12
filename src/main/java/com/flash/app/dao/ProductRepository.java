package com.flash.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flash.app.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{
	
}
