package com.caravelo.persistence;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.caravelo.*"})
@OpenAPIDefinition
@EnableScheduling
public class CaraveloRouteNetworkApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CaraveloRouteNetworkApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CaraveloRouteNetworkApplication.class, args);
	}
}
