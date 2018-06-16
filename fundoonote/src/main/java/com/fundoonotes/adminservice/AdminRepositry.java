package com.fundoonotes.adminservice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fundoonotes.userservice.User;

public interface AdminRepositry extends JpaRepository<User,Integer>{
	
	User findByEmail(String email);
	 

	
}
