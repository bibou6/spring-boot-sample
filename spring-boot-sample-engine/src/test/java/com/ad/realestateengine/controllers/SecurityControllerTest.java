package com.ad.realestateengine.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ad.realestateengine.controllers.v1.authentication.AuthController;
import com.ad.realestateengine.entities.repositories.UserRepository;
import com.ad.realestateengine.mocks.UserMock;
import com.ad.realestatemodel.common.ErrorCode;
import com.ad.realestatemodel.common.ErrorResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class SecurityControllerTest {

	public static class MockSecurityContext implements SecurityContext {

		private static final long serialVersionUID = -1386535243513362694L;

		private Authentication authentication;

		public MockSecurityContext(Authentication authentication) {
			this.authentication = authentication;
		}

		@Override
		public Authentication getAuthentication() {
			return this.authentication;
		}

		@Override
		public void setAuthentication(Authentication authentication) {
			this.authentication = authentication;
		}
	}

	private Logger logger = LoggerFactory.getLogger(SecurityControllerTest.class);

	@Autowired
	AuthController authController;

	@MockBean
	UserRepository userRepository;

	@Before
	public void setUp() {
	}

	@Test
	public void registerUserOK() {
		logger.info("[TEST] - registerUserOK");
		ResponseEntity<?> response = authController.registerUser(UserMock.createSignupRequest());

		assertEquals(response, ResponseEntity.ok(null));
	}

	@Test
	public void registerUserExistUsernameException() {
		logger.info("[TEST] - registerUserExistUsernameException");
		when(userRepository.existsByUsername(Mockito.anyString())).thenReturn(true);

		ResponseEntity<?> response = authController.registerUser(UserMock.createSignupRequest());

		assertEquals(response, ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.CONFLICT.value(),
				ErrorCode.USER_EXIST_BY_USERNAME)));

	}

	@Test
	public void registerUserExistEmailException() {
		logger.info("[TEST] - registerUserExistEmailException");
		when(userRepository.existsByEmail(Mockito.anyString())).thenReturn(true);

		ResponseEntity<?> response = authController.registerUser(UserMock.createSignupRequest());

		assertEquals(response, ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.CONFLICT.value(),
				ErrorCode.USER_EXIST_BY_EMAIL)));

	}

}
