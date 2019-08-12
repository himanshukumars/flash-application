package com.flash.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flash.app.entity.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, String>{

}
