package com.flash.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flash.app.entity.FlashSaleRegistration;
import com.flash.app.entity.Users;
import java.util.List;

@Repository
public interface FlashSaleRegistrationRepository extends CrudRepository<FlashSaleRegistration, Users>{

	
	List<FlashSaleRegistration> findByUser(Users user);
}
