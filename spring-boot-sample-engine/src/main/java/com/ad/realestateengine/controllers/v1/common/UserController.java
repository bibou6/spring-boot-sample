package com.ad.realestateengine.controllers.v1.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ad.realestateengine.controllers.v1.V1Controller;
import com.ad.realestateengine.entities.User;
import com.ad.realestateengine.security.rules.SecurityRoles;
import com.ad.realestateengine.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
@Api(value = "Goup APIs related to Cities", tags = "User")
public class UserController extends V1Controller {
	
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "Promotes a user to a role", notes = "Adds a roles to a user account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "Rights of the user are unsufisiant"),
			@ApiResponse(code = 403, message = "User not connected - Forbidden"),
			@ApiResponse(code = 404, message = "Resource does not exists"),
			@ApiResponse(code = 500, message = "Internal Server Error during request processing")
	})
	@PostMapping("/user/{userId}/promote")
	public void promoteUser(
			@ApiParam(value = "User id",required = true, example = "1") @PathVariable(name = "userId", required = true) Long userId,
			@ApiParam(value = "roles",required = true, example = "ACCESS_MY_CITY") @RequestParam(name = "roles", required = true) SecurityRoles role) {
		this.userService.promoteUser(userId, role);
	}
	
	
	@ApiOperation(value = "Demotes a user to a role", notes = "Remove a role to a user account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "Rights of the user are unsufisiant"),
			@ApiResponse(code = 403, message = "User not connected - Forbidden"),
			@ApiResponse(code = 404, message = "Resource does not exists"),
			@ApiResponse(code = 500, message = "Internal Server Error during request processing")
	})
	@PostMapping("/user/{userId}/demote")
	public void demoteUser(
			@ApiParam(value = "User id",required = true, example = "1") @PathVariable(name = "userId", required = true) Long userId,
			@ApiParam(value = "roles",required = true, example = "ACCESS_MY_CITY") @RequestParam(name = "roles", required = true) SecurityRoles role) {
		this.userService.demoteUser(userId, role);
	}
	
	
	@ApiOperation(value = "Updates a user", notes = "Updates a user account")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "Rights of the user are unsufisiant"),
			@ApiResponse(code = 403, message = "User not connected - Forbidden"),
			@ApiResponse(code = 404, message = "Resource does not exists"),
			@ApiResponse(code = 500, message = "Internal Server Error during request processing")
	})
	@PutMapping("/user/{userId}")
	public void updateUser(
			@ApiParam(value = "User id",required = true, example = "1") @PathVariable(name = "userId", required = true) Long userId,
			@ApiParam(value = "User id",required = true, example = "1") @RequestBody User user) {
		this.userService.updateUser(user);
	}
	
	
	@ApiOperation(value = "Get a user by its ID", notes = "Return User")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "Rights of the user are unsufisiant"),
			@ApiResponse(code = 403, message = "User not connected - Forbidden"),
			@ApiResponse(code = 404, message = "Resource does not exists"),
			@ApiResponse(code = 500, message = "Internal Server Error during request processing")
	})
	@GetMapping("/user/{userId}")
	public User getUserById(
			@ApiParam(value = "User id",required = true, example = "1") @PathVariable(name = "userId", required = true) Long userId) {
		return this.userService.getUserById(userId);
	}
	
	
	
	
}
