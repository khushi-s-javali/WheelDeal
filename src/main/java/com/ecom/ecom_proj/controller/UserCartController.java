package com.ecom.ecom_proj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.ecom_proj.dto.ProductQuantityDTO;
import com.ecom.ecom_proj.model.UserCart;
import com.ecom.ecom_proj.service.UserCartServer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class UserCartController {
	@Autowired
	UserCartServer service;
	
	@PostMapping("/addToCart")
	public ResponseEntity<?> addToCart(@RequestParam int prodid,@RequestParam int quantity, HttpServletRequest request){
		
		HttpSession session= request.getSession(false);
		if (session == null || session.getAttribute("uid") == null) {
	        return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
	    }
		//System.out.println(prodid);
		
		int userid=(int) session.getAttribute("uid");
		UserCart cart=service.addToCart(prodid,userid, quantity);
		if(cart==null)
			return new ResponseEntity<>("Out of stock",HttpStatus.BAD_REQUEST);
		try {
			return new ResponseEntity<UserCart>(cart, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(" failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/updateCart")
	public ResponseEntity<?> updateToCart(@RequestParam int prodid,@RequestParam int quantity, HttpServletRequest request){
		
		HttpSession session= request.getSession(false);
		if (session == null || session.getAttribute("uid") == null) {
	        return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
	    }
		//System.out.println(prodid);
		
		int userid=(int) session.getAttribute("uid");
		UserCart cart=service.updateToCart(prodid,userid, quantity);
		if(cart==null)
			return new ResponseEntity<>("Out of stock",HttpStatus.BAD_REQUEST);
		try {
			return new ResponseEntity<UserCart>(cart, HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(" failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/displayCart")
	public ResponseEntity<?> displayCart(HttpServletRequest request){
		HttpSession session= request.getSession(false);
		if (session == null || session.getAttribute("uid") == null) {
	        return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
	    }
		int userid=(int) session.getAttribute("uid");
		try {
			return new ResponseEntity<List<ProductQuantityDTO>>(service.displayCart(userid), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(" failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("/totalPrice")
	public ResponseEntity<?> totalPrice(HttpServletRequest request){
		HttpSession session= request.getSession(false);
		if (session == null || session.getAttribute("uid") == null) {
	        return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
	    }
		int userid=(int) session.getAttribute("uid");
		try {
			return new ResponseEntity<Integer>(service.totalPrice(userid), HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(" failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
	@DeleteMapping("/delItem")
	public ResponseEntity<?> delItem(@RequestParam int prodid,HttpServletRequest request){
		HttpSession session= request.getSession(false);
		if (session == null || session.getAttribute("uid") == null) {
	        return new ResponseEntity<>("User not logged in", HttpStatus.UNAUTHORIZED);
	    }
		int userid=(int) session.getAttribute("uid");
		try {
			service.delItem(prodid,userid);
			return new ResponseEntity<>("Item deleted", HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<>(" failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
	}
}
