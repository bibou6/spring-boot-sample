package com.ad.realestateengine.schedulers;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ad.realestateengine.services.CronService;

@Component
public class TestJob implements Job {

	@Autowired
	private CronService cronService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		this.cronService.testJob();
	}

}
