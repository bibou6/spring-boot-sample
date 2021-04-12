package com.ad.realestateengine.config.hibernate;

import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vladmihalcea.hibernate.type.util.CamelCaseToSnakeCaseNamingStrategy;

@Configuration
public class JpaConfiguration {

	/*@Bean
	public PhysicalNamingStrategy physical() {
		System.out.println("test naming strategy");
	    return new CamelCaseToSnakeCaseNamingStrategy();
	}*/

}
