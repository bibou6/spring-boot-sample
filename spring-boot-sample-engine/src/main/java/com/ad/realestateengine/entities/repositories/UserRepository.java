package com.ad.realestateengine.entities.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ad.realestateengine.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findByUsername(String username);
	
	Boolean existsByUsername(String usernamel);
	
	Boolean existsByEmail(String email);
}
