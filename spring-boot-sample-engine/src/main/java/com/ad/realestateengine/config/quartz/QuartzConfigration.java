package com.ad.realestateengine.config.quartz;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@ConditionalOnProperty(value = "app.scheduling.enabled", havingValue = "true", matchIfMissing = true)
@Configuration
@EnableScheduling
public class QuartzConfigration {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(ApplicationContext applicationContext,
			QuartzProperties quartzProperties) {
		AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();
		jobFactory.setApplicationContext(applicationContext);

		Properties properties = new Properties();
		properties.putAll(quartzProperties.getProperties());

		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setOverwriteExistingJobs(true);
		factory.setQuartzProperties(properties);
		factory.setJobFactory(jobFactory);

		return factory;
	}

}
