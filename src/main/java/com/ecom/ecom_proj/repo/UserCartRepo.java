package com.ecom.ecom_proj.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecom.ecom_proj.dto.ProductQuantityDTO;
import com.ecom.ecom_proj.model.Product;
import com.ecom.ecom_proj.model.UserCart;
import com.ecom.ecom_proj.model.UserCred;

@Repository
public interface UserCartRepo extends JpaRepository<UserCart, Integer> {

	Optional<UserCart> findByUserAndProduct(UserCred user, Product prod);
	@Query("Select new com.ecom.ecom_proj.dto.ProductQuantityDTO(u.product, u.quantity) from UserCart u where u.user.userid=:userid")
	List<ProductQuantityDTO> findAllByUserid(@Param("userid") int userid);
	
	@Query("Select sum(u.product.price * u.quantity) from UserCart u where u.user.userid=:userid")
	Integer findPriceByUserid(@Param("userid") int userid);

}
