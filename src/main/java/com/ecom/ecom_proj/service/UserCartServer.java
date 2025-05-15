package com.ecom.ecom_proj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.ecom_proj.dto.ProductQuantityDTO;
import com.ecom.ecom_proj.model.Product;
import com.ecom.ecom_proj.model.UserCart;
import com.ecom.ecom_proj.model.UserCred;
import com.ecom.ecom_proj.repo.ProductRepo;
import com.ecom.ecom_proj.repo.UserCartRepo;
import com.ecom.ecom_proj.repo.UserRepo;

@Service
public class UserCartServer {
	
	@Autowired
	UserCartRepo repo;
	@Autowired
	UserRepo userrepo;
	@Autowired
	ProductRepo prodrepo;

	public UserCart addToCart(int prodid, int userid, int quantity) {
		// TODO Auto-generated method stub
		//System.out.println(quantity);
		UserCred user=userrepo.findById(userid).orElseThrow(() -> new RuntimeException("User not found"));
		
		Product prod=prodrepo.findById(prodid).orElseThrow(() -> new RuntimeException("Product not found"));
		
		UserCart existRow=repo.findByUserAndProduct(user,prod).orElse(null);
		
		if(existRow!=null && prod.getQuantity()!=0) {
			prod.setQuantity(prod.getQuantity()-quantity);
			prodrepo.save(prod);
			existRow.setQuantity(quantity+existRow.getQuantity());
			return repo.save(existRow);
		}
		else if( existRow==null && prod.getQuantity()!=0) {
		UserCart cart=new UserCart();
		cart.setProduct(prod);
		cart.setQuantity(quantity);
		cart.setUser(user);
		
		prod.setQuantity(prod.getQuantity()-quantity);
		prodrepo.save(prod);
		return repo.save(cart);
		}
		else {
			prod.setAvailable(false);
			prodrepo.save(prod);
			return null;
		}
	}
	
	public UserCart updateToCart(int prodid, int userid, int quantity) {
		UserCred user=userrepo.findById(userid).orElseThrow(() -> new RuntimeException("User not found"));
		
		Product prod=prodrepo.findById(prodid).orElseThrow(() -> new RuntimeException("Product not found"));
		
		UserCart existRow=repo.findByUserAndProduct(user,prod).orElseThrow(()-> new RuntimeException("Product not found"));
		if(prod.getQuantity()!=0) {
			prod.setQuantity(prod.getQuantity()-(quantity -existRow.getQuantity()));
			existRow.setQuantity(quantity);
			prodrepo.save(prod);
			return repo.save(existRow);
		}
		prod.setAvailable(false);
		prodrepo.save(prod);
		return null;
	}

	public List<ProductQuantityDTO> displayCart(int userid) {
		return repo.findAllByUserid(userid);	
	}

	public Integer totalPrice(int userid) {
		return repo.findPriceByUserid(userid);
	}

	public void delItem(int prodid, int userid) {
		// TODO Auto-generated method stub
		UserCred user=userrepo.findById(userid).orElseThrow(() -> new RuntimeException("User not found"));
		
		Product prod=prodrepo.findById(prodid).orElseThrow(() -> new RuntimeException("Product not found"));
		
		UserCart existRow=repo.findByUserAndProduct(user,prod).orElseThrow(()-> new RuntimeException("Product not found"));
		repo.delete(existRow);
		prod.setQuantity(prod.getQuantity()+1);
		prodrepo.save(prod);
	}
	
}
