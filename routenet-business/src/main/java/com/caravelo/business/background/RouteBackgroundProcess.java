package com.caravelo.business.background;

import com.caravelo.business.handler.ExternalRouteDataHandler;
import com.caravelo.common.TenantContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Background process to get the routes from external API's
 */
@Configuration
public class RouteBackgroundProcess {

	private static final Logger LOGGER = LogManager.getLogger(RouteBackgroundProcess.class);

	private final ExternalRouteDataHandler flightsDataHandler;

	/**
	 * List of tenants to get the different routes
	 */
	@Value("${caravelo.background.tenant.config:1}")
	private String[] tenantListForBackground;

	public RouteBackgroundProcess(ExternalRouteDataHandler externalRoutesDataHandler) {
		this.flightsDataHandler = externalRoutesDataHandler;
	}

	/**
	 * Cron to get the  Routes from external API. Default value for execution: every 5 minutes on Application properties. Default every 15 second
	 * The process is launched for each configured tenant on application properties
	 */
	@Scheduled(cron = "${caravelo.background.flights.cron:0/15 * * * * *}")
	public void executeProcessGetRoutes() {
		LOGGER.info("Start Executing flights background process...");
		try {
			for (String tenant : tenantListForBackground) {
				TenantContext.setTenantId(tenant);
				flightsDataHandler.getRoutesData();
			}
		} catch (Exception e) {
			LOGGER.error("ERROR retrieving data from endpoint", e);
		} finally {
			TenantContext.clear();
		}
		LOGGER.info("End Executing flights background process...");
	}

}
