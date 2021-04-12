package com.ad.realestateengine.services;

import com.ad.realestateengine.entities.User;
import com.ad.realestateengine.security.rules.SecurityRoles;

public interface UserService {
	
	/**
	 * Promote a User with a given role
	 * @param userId
	 * @param role
	 */
	public void promoteUser(Long userId, SecurityRoles role);

	/**
	 * Remove a Role to a given user
	 * @param userId
	 * @param role
	 */
	public void demoteUser(Long userId, SecurityRoles role);

	/**
	 * Updates a User
	 * @param user
	 */
	public void updateUser(User user);

	/**
	 * Retrieve a user by its id
	 * @param user
	 * @return
	 */
	public User getUserById(Long userId);

}
