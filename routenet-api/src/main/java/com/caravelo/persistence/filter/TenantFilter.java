package com.caravelo.persistence.filter;


import com.caravelo.common.TenantContext;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TenantFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		  throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String tenantId = httpRequest.getHeader("X-Tenant-ID");

		try {
			if (tenantId != null) {
				TenantContext.setTenantId(tenantId);
			}
			chain.doFilter(request, response);
		} finally {
			TenantContext.clear();
		}
	}

	@Override
	public void init(FilterConfig filterConfig) {}

	@Override
	public void destroy() {}
}
