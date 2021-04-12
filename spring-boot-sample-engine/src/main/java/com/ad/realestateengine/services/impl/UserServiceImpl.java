package com.ad.realestateengine.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ad.realestateengine.entities.User;
import com.ad.realestateengine.entities.repositories.UserRepository;
import com.ad.realestateengine.security.rules.SecurityRoles;
import com.ad.realestateengine.services.UserService;
import com.ad.realestateengine.utils.UserUtils;


@Service
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserUtils userUtils;
	
	@Override
	public void promoteUser(Long userId, SecurityRoles role) {
		logger.info("[USER][UPDATE][USER_ID:{}] - Promoting with role: {}",userId,role);
		User user = userUtils.getUserByIdOrElseException(userId);
		user.addRole(role);
		userRepository.save(user);
	}

	@Override
	public void demoteUser(Long userId, SecurityRoles role) {
		logger.info("[USER][UPDATE][USER_ID:{}] - Demoting with role: {}",userId,role);
		User user = userUtils.getUserByIdOrElseException(userId);
		user.removeRole(role);
		userRepository.save(user);
		
	}

	@Override
	public void updateUser(User user) {
		User bddUser = userUtils.getUserByIdOrElseException(user.getId());
		bddUser.setFirstName(user.getFirstName());
		bddUser.setLastName(user.getLastName());
		this.userRepository.save(bddUser);
	}

	@Override
	public User getUserById(Long userId) {
		return this.userUtils.getUserByIdOrElseException(userId);
	}

}
