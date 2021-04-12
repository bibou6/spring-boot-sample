package com.ad.realestateengine.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ad.realestateengine.entities.User;
import com.ad.realestateengine.entities.repositories.UserRepository;
import com.ad.realestateengine.exceptions.RealEstateEngineException;
import com.ad.realestatemodel.common.ErrorCode;

@Component
public class UserUtils {

	@Autowired
	private UserRepository userRepository;
	
	public User getUserByIdOrElseException(Long userId) {
		
		Optional<User> user = this.userRepository.findById(userId);
		
		if(!user.isPresent()) {
			throw new RealEstateEngineException(ErrorCode.USER_NOT_FOUND);
		}
		return user.get();
		
	}
	
	
}
