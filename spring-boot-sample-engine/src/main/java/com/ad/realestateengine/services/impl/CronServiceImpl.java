package com.ad.realestateengine.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ad.realestateengine.services.CronService;

@Component
public class CronServiceImpl implements CronService {

	private static Logger logger = LoggerFactory.getLogger(CronServiceImpl.class);

	@Override
	public void testJob() {
		logger.info("Running test job");

	}

}
