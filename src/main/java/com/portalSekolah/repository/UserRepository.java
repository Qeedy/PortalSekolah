package com.portalSekolah.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import com.portalSekolah.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

	public Optional<User> findByEmailAddress(String emailAddress);
	
}