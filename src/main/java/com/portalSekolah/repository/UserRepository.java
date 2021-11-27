package com.portalSekolah.repository;

import org.springframework.data.repository.CrudRepository;
import com.portalSekolah.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

}