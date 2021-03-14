package com.shopping.cart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SwaggerConfig is the config class for swagger which facilitates in
 * documenting the API details.
 * 
 * @author Kusal
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * Docket instance provides the API configuration which helps in integration
	 * with Spring Boot
	 * 
	 * @return
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.shopping.cart")).build()
				.apiInfo(new ApiInfoBuilder().title("Shopping cart web app with spring boot")
						.description("Cart for users during online shopping").build())
				.enable(true);
	}
}
