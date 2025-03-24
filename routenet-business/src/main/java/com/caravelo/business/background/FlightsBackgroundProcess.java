package com.caravelo.business.background;

import com.caravelo.business.handler.FlightDataHandler;
import com.caravelo.common.TenantContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class FlightsBackgroundProcess {

	private static final Logger LOGGER = LogManager.getLogger(FlightsBackgroundProcess.class);

	private final FlightDataHandler flightsDataHandler;

	@Value("${caravelo.background.tenant.config:1}")
	private String[] tenantListForBackground;

	public FlightsBackgroundProcess(FlightDataHandler flightsDataHandler) {
		this.flightsDataHandler = flightsDataHandler;
	}

	@Scheduled(cron = "${caravelo.background.flights.cron:0/15 * * * * *}")
	public void executeProcessGetFlights() {
		LOGGER.info("Start Executing flights background process...");
		try {
			for (String tenant : tenantListForBackground) {
				TenantContext.setTenantId(tenant);
				flightsDataHandler.getFlightsData();
			}
		} catch (Exception e) {
			LOGGER.error("ERROR retrieving data from endpoint", e);
		} finally {
			TenantContext.clear();
		}
		LOGGER.info("End Executing flights background process...");
	}

}
