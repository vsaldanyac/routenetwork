package com.caravelo.api.config;

import com.caravelo.api.filter.TenantFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

	@Bean
	public FilterRegistrationBean<TenantFilter> tenantFilter() {
		FilterRegistrationBean<TenantFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new TenantFilter());
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}
}
