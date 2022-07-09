package com.portalSekolah.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.portalSekolah.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

	public Optional<User> findByEmailAddress(String emailAddress);
	
	public Optional<User> findByUsername(String username);

	@Transactional
	@Modifying
	@Query("UPDATE User u SET u.enabled = TRUE WHERE u.emailAddress = :email")
	int enableUser(@Param("email")String email);

}