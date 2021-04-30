package com.ad.realestateengine.properties;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Component
@ConfigurationProperties(prefix = "cron")
@Validated
@Data
public class CronProperties {
	
	@NotNull
	@Valid
	private CronJob testJob;
	
	
	@Data
	public static class CronJob{
		private boolean active;
		
		private String cron;
	}

}
