package com.caravelo.business.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	@Bean
	RestTemplate restTemplateBuilder(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.basicAuthentication("", "").build();
	}
}
