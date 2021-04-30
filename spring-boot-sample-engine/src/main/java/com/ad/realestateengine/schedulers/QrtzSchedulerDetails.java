package com.ad.realestateengine.schedulers;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import com.ad.realestateengine.properties.CronProperties;


@Component
public class QrtzSchedulerDetails implements ApplicationRunner {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired(required = false)
	private SchedulerFactoryBean schedulerFactoryBean;

	@Autowired
	private Environment env;

	@Autowired
	private CronProperties cronProperties;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		logger.info("[CRON][CONFIGURATION] - Starts"); 
		try {
			if (!"false".equals(env.getProperty("app.scheduling.enabled"))) {
				startSchedulers();
			}
		} catch (Exception e) {
			throw new SchedulerException("Could not start scheduler");
		}

	}

	public void startSchedulers() throws SchedulerException {
		Scheduler scheduler = null;

		if (this.schedulerFactoryBean != null) {
			scheduler = this.schedulerFactoryBean.getScheduler();
		} else {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
		}

		
		logger.info("[CRON][CONFIGURATION] - TestJob Active : {}",this.cronProperties.getTestJob().isActive());
		if (this.cronProperties.getTestJob().isActive()) {
			JobDetail testJobDetail = buildTestJobDetail();
			Trigger testJobTrigger = buildtestJobTrigger(testJobDetail, Instant.now());
			scheduler.scheduleJob(testJobDetail,testJobTrigger);
		}

	}

	private JobDetail buildTestJobDetail() {
		return JobBuilder.newJob(TestJob.class).withIdentity(UUID.randomUUID().toString(), "csp-test-job")
				.withDescription("CSP Test Job").storeDurably().build();
	}

	private Trigger buildtestJobTrigger(JobDetail jobDetail, Instant now) {
		
		logger.info("[CRON][CONFIGURATION] - TestJob : {}",this.cronProperties.getTestJob().getCron());
		
		return TriggerBuilder.newTrigger().forJob(jobDetail).withIdentity("csp-test-job-trigger")
				.withDescription("CSP Test Job Trigger").startAt(Date.from(now))
				.withSchedule(CronScheduleBuilder.cronSchedule(this.cronProperties.getTestJob().getCron())).build();
	}
}
