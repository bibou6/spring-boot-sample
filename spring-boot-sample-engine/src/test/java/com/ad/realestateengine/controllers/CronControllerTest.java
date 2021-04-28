package com.ad.realestateengine.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ad.realestateengine.controllers.v1.supervision.CronController;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CronControllerTest {
	
	private Logger logger = LoggerFactory.getLogger(CronControllerTest.class);
	
	@Autowired 
	private CronController cronController;
	
	
	@Before
	public void setUp() {
	}
	
	
	@Test
	public void testJobTest() {
		logger.info("[TEST] - testJobTest");
		this.cronController.testJob();
	}
	


	

}
