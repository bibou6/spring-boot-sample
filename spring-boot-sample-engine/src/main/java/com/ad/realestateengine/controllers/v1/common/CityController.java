package com.ad.realestateengine.controllers.v1.common;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ad.realestateengine.controllers.v1.V1Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
@Api(value = "Goup APIs related to Cities", tags = "City")
public class CityController extends V1Controller {
	
	
	
	
	@ApiOperation(value = "Get all City by an Admin", notes = "Returns the list of all Cities")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "Rights of the user are unsufisiant"),
			@ApiResponse(code = 403, message = "User not connected - Forbidden"),
			@ApiResponse(code = 404, message = "Resource does not exists"),
			@ApiResponse(code = 500, message = "Internal Server Error during request processing")
	})
	@GetMapping(value = "/admin/city", produces = "application/json")
	public String getAllCityByAdmin() {
		return "toto";
	}
	
}
