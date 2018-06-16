package com.fundoonotes.userservice;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

@Repository(value = "userDao")
public interface UserDAO extends CrudRepository<User,Integer> {
     User findByEmail(String email);
	 User findByRandomId(String randomId);
}