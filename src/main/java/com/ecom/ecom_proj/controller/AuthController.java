package com.ecom.ecom_proj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.ecom_proj.model.UserCred;
import com.ecom.ecom_proj.service.AuthServer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class AuthController {
	@Autowired
	AuthServer service;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody UserCred user) {
        try {
            return new ResponseEntity<>(service.addUser(user), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Signup failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserCred user, HttpServletRequest request) {
		
		UserCred userActive=service.login(user);
		if(userActive==null)
			return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
		try {
			HttpSession session= request.getSession(true);
			session.setAttribute("uid", userActive.getUserid());
			//System.out.println(userActive.getUserid());
            return new ResponseEntity<>(userActive, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(" failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
	}
	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestBody UserCred user, HttpServletRequest request) {
		HttpSession session= request.getSession(false);
		if(session!=null)
			session.invalidate();
		return new ResponseEntity<>("Loged out sucessfully", HttpStatus.OK);
		
		}
}
