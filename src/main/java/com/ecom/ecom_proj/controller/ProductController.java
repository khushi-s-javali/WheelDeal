package com.ecom.ecom_proj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.ecom_proj.model.Product;
import com.ecom.ecom_proj.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	ProductService service;
	
	@RequestMapping("/")
	public String greet() {
		return "Helloooo Gannu";
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(){
		return  new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
	}
	
	@GetMapping("/product/{Id}")
	public ResponseEntity<Product> getProductById(@PathVariable int Id ){
		if(service.getProductById(Id)!=null)
			return  new ResponseEntity<>(service.getProductById(Id), HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/product")
	public ResponseEntity<?> addProduct(@RequestPart("product") String productJson, @RequestPart("image") MultipartFile imageFile) {
	    try {
	        ObjectMapper objectMapper = new ObjectMapper();
	        Product product = objectMapper.readValue(productJson, Product.class);
	        return new ResponseEntity<>(service.addProduct(product, imageFile), HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	@GetMapping("/product/{Id}/image")
	public ResponseEntity<byte[]> getImageById(@PathVariable int Id ){
		Product product=service.getProductById(Id);
		byte[] imageFile=product.getImageData();
		return ResponseEntity.ok()
				.contentType(MediaType.valueOf(product.getImageType()))
				.body(imageFile);
	}
	@GetMapping("/product/search")
	public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
		List<Product> products=service.searchProducts(keyword);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
}
