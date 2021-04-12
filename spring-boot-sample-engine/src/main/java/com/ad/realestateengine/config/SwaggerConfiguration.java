package com.ad.realestateengine.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SwaggerConfiguration {
	

	@Bean
	public Docket commonApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Common")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ad.realestateengine.controllers.v1.common"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metadata())
				.securityContexts(Arrays.asList(securityContext()))
			    .securitySchemes(Arrays.asList(apiKey()));
	}
	
	
	@Bean
	public Docket AuthenticationApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("Authentication")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.ad.realestateengine.controllers.v1.authentication"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metadata());
	}
	
	private ApiInfo metadata() {
		return new ApiInfoBuilder().version("0.0.1-SNAPSHOT").title("Real Estate APIs")
				.description("Services that exposes APIs to access real estate data")
				.build();
	}
	
	private ApiKey apiKey() { 
	    return new ApiKey( "Authorization:","JWT", "header"); 
	}
	
	private SecurityContext securityContext() { 
	    return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
	} 

	private List<SecurityReference> defaultAuth() { 
	    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
	    authorizationScopes[0] = authorizationScope; 
	    return Arrays.asList(new SecurityReference("JWT", authorizationScopes)); 
	}
}
