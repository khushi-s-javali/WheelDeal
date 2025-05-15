package com.ecom.ecom_proj.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.ecom_proj.model.UserCred;

@Repository
public interface UserRepo extends JpaRepository<UserCred, Integer> {

	//boolean existsByUsernameAndPassword(String username, String password);

	Optional<UserCred> findByUsernameAndPassword(String username, String password);

}
