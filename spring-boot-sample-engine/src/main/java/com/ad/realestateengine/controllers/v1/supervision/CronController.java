package com.ad.realestateengine.controllers.v1.supervision;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ad.realestateengine.controllers.v1.V1Controller;
import com.ad.realestateengine.services.CronService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@CrossOrigin
@Api(value = "Goup APIs for the Cron Jobs", tags = "Crons")
public class CronController extends V1Controller{
	
	@Autowired
	private CronService cronService;

	
	@ApiOperation(value = "Triggers Test Job", notes = "Prints a strring")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 401, message = "Rights of the user are unsufisiant"),
			@ApiResponse(code = 403, message = "User not connected - Forbidden"),
			@ApiResponse(code = 404, message = "Resource does not exists"),
			@ApiResponse(code = 500, message = "Internal Server Error during request processing")
	})
	@GetMapping(value = "/supervision/crons/testJob", produces = "application/json")
	public void testJob() {
		this.cronService.testJob();
	}
	
}
