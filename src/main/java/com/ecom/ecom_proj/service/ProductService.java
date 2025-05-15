package com.ecom.ecom_proj.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.ecom_proj.model.Product;
import com.ecom.ecom_proj.repo.ProductRepo;


@Service
public class ProductService {
	
	@Autowired
	ProductRepo repo;
	
	public List<Product> getAllProducts() {
		
		return repo.findAll();
	}
	
	public Product getProductById(int id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElse(null);
	}

	public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
		// TODO Auto-generated method stub
		product.setImageName(imageFile.getOriginalFilename());
		product.setImageType(imageFile.getContentType());
		product.setImageData(imageFile.getBytes());
		return repo.save(product);
	}

	public List<Product> searchProducts(String keyword) {
		return repo.searchProducts(keyword);
	}

}
