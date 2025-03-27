package com.caravelo.api.config;

import com.caravelo.api.filter.TenantFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class should allow a filter to get the tenant for each customer
 */
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
