package com.ad.realestateengine.controllers;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ad.realestateengine.controllers.v1.common.UserController;
import com.ad.realestateengine.entities.User;
import com.ad.realestateengine.entities.repositories.UserRepository;
import com.ad.realestateengine.exceptions.RealEstateEngineException;
import com.ad.realestateengine.mocks.UserMock;
import com.ad.realestateengine.security.rules.SecurityRoles;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserControllerTest {
	
	private Logger logger = LoggerFactory.getLogger(UserControllerTest.class);
	
	@Autowired 
	UserController userController;
	
	@MockBean 
	UserRepository userRepository;
	
	@Before
	public void setUp() {
		Optional<User> userOpt = UserMock.createBasicUserOpt();
		Mockito.when(userRepository.findById(1L)).thenReturn(userOpt);
		Mockito.when(userRepository.findByUsername("test")).thenReturn(userOpt);
	}
	
	
	@Test
	public void promoteUserTestOk() {
		logger.info("[TEST] - promoteUserTestOk");
		userController.promoteUser(1L, SecurityRoles.ACCESS_ALL_CITY);
	}
	
	@Test(expected = RealEstateEngineException.class)
	public void promoteUserTestNotFound() {
		logger.info("[TEST] - promoteUserTestNotFound");
		userController.promoteUser(2L, SecurityRoles.ACCESS_ALL_CITY);
	}
	
	@Test
	public void demoteUserTestOkRolePresent() {
		logger.info("[TEST] - demoteUserTestOkRolePresent");
		userController.demoteUser(1L, SecurityRoles.ACCESS_MY_USER);
	}
	
	@Test
	public void demoteUserTestOkRoleNotFound() {
		logger.info("[TEST] - demoteUserTestOk");
		userController.demoteUser(1L, SecurityRoles.ACCESS_ALL_CITY);
	}
	
	@Test(expected = RealEstateEngineException.class)
	public void demoteUserTestNotFound() {
		logger.info("[TEST] - demoteUserTestNotFound");
		userController.demoteUser(2L, SecurityRoles.ACCESS_ALL_CITY);
	}
	
	@Test
	public void updateUserOK() {
		logger.info("[TEST] - updateUserOK");
		User user = new User();
		user.setFirstName("test2");
		user.setLastName("test2");
		user.setId(1L);
		userController.updateUser(1L, user);
	}
	
	@Test(expected = RealEstateEngineException.class)
	public void updateUserNotFound() {
		logger.info("[TEST] - updateUserNotFound");
		User user = new User();
		user.setFirstName("test2");
		user.setLastName("test2");
		user.setId(2L);
		userController.updateUser(2L, user);
	}
	
	@Test
	public void getUserByIdTestOk() {
		logger.info("[TEST] - getUserByIdTestOk");
		User user = userController.getUserById(1L);
		assertEquals(user.getFirstName(), "test");
		assertEquals(user.getLastName(), "test");
		assertEquals(user.getEmail(), "test@test.com");
		assertEquals(user.getUsername(), "test@test.com");
		assertFalse(user.getRoles().isEmpty());
		
	}
	

}
