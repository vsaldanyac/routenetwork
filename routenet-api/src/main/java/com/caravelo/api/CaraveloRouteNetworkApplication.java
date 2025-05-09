	package com.caravelo.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan(basePackages = {"com.caravelo.*"})
@OpenAPIDefinition
@EnableScheduling
@EnableJpaRepositories("com.caravelo.persistence.*")
@EntityScan("com.caravelo.persistence.model")
public class CaraveloRouteNetworkApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CaraveloRouteNetworkApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(CaraveloRouteNetworkApplication.class, args);
	}
}
