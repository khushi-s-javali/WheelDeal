package com.ecom.ecom_proj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.ecom_proj.model.UserCred;
import com.ecom.ecom_proj.repo.UserRepo;

@Service
public class AuthServer {
	
	@Autowired
	UserRepo repo;

	public UserCred addUser(UserCred user) {
		// TODO Auto-generated method stub
		return repo.save(user);
		
	}

	public UserCred login(UserCred user) {
		// TODO Auto-generated method stub
		 return repo.findByUsernameAndPassword(user.getUsername(), user.getPassword())
	               .orElse(null);
	}

}
