package com.ad.realestateengine.mocks;

import java.util.Optional;

import com.ad.realestateengine.entities.User;
import com.ad.realestateengine.security.rules.SecurityRoles;
import com.ad.realestatemodel.auth.request.SignupRequest;

public class UserMock {
	
	public static Optional<User> createBasicUserOpt() {
		User user = new User();
		user.setEmail("test@test.com");
		user.setFirstName("test");
		user.setLastName("test");
		user.setId(1L);
		user.setUsername("test@test.com");
		user.addRole(SecurityRoles.ACCESS_MY_USER);
		return Optional.ofNullable(user);
	}
	
	public static SignupRequest createSignupRequest() {
		SignupRequest sr = new SignupRequest();
		sr.setEmail("test@test.com");
		sr.setUsername("test");
		sr.setPassword("test");
		
		return sr;
	}

}
