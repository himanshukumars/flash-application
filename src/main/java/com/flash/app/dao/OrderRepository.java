package com.flash.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flash.app.entity.Orders;
@Repository
public interface OrderRepository extends CrudRepository<Orders, Integer> {

}
