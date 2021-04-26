package com.ad.realestateengine.schedulers;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ad.realestateengine.services.impl.UserServiceImpl;


@Component
public class TestJob implements Job	{
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		logger.info("Executing Job");
		
	}
	
	

}
