package com.caravelo.api.filter;


import com.caravelo.common.TenantContext;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Filter to manage the tenant on the Headers of each request
 */
public class TenantFilter implements Filter {

	/**
	 * Filter each request in order to provide the TenantId on the Execution Context
	 * @param request  The request to process
	 * @param response The response associated with the request
	 * @param chain    Provides access to the next filter in the chain for this
	 *                 filter to pass the request and response to for further
	 *                 processing
	 *
	 * @throws IOException
	 * @throws ServletException
	 */
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
