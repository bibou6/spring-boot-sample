package com.ad.realestateengine.controllers.v1.authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ad.realestateengine.controllers.v1.V1Controller;
import com.ad.realestateengine.entities.User;
import com.ad.realestateengine.entities.repositories.UserRepository;
import com.ad.realestateengine.security.jwt.JwtUtils;
import com.ad.realestateengine.security.rules.SecurityRoles;
import com.ad.realestateengine.security.services.UserDetailsImpl;
import com.ad.realestateengine.services.UserService;
import com.ad.realestatemodel.auth.request.LoginRequest;
import com.ad.realestatemodel.auth.request.SignupRequest;
import com.ad.realestatemodel.auth.response.JwtResponse;
import com.ad.realestatemodel.common.ErrorCode;
import com.ad.realestatemodel.common.ErrorResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin
@RestController
@Api(value = "Goup APIs related to Authentication and User management", tags = "Authentication")
public class AuthController extends V1Controller{
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	
	@Autowired
	UserService userService;

	@ApiOperation(value = "Sign in to use APIs", notes = "Allow the authentication")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "Rights of the user are unsufisiant"),
			@ApiResponse(code = 403, message = "User not connected - Forbidden"),
			@ApiResponse(code = 404, message = "Resource does not exists"),
			@ApiResponse(code = 500, message = "Internal Server Error during request processing")
	})
	@PostMapping("/auth/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@ApiOperation(value = "Sign up to use APIs", notes = "Creates a user account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "Rights of the user are unsufisiant"),
			@ApiResponse(code = 403, message = "User not connected - Forbidden"),
			@ApiResponse(code = 404, message = "Resource does not exists"),
			@ApiResponse(code = 500, message = "Internal Server Error during request processing")
	})
	@PostMapping("/auth/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new ErrorResponse(HttpStatus.CONFLICT.value(),ErrorCode.USER_EXIST_BY_USERNAME));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new ErrorResponse(HttpStatus.CONFLICT.value(),ErrorCode.USER_EXIST_BY_EMAIL));
		}

		// Create new user's account
		User user = new User();
		user.setEmail(signUpRequest.getEmail());
		user.setUsername(signUpRequest.getUsername());
		user.setEncodedPassword(encoder.encode(signUpRequest.getPassword()));

		List<SecurityRoles> roles = new ArrayList<>();

		roles.add(SecurityRoles.ACCESS_MY_CITY);
		roles.add(SecurityRoles.ACCESS_MY_CONTACT);
		roles.add(SecurityRoles.ACCESS_MY_USER);
		
		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(null);
	}
	
	
}
