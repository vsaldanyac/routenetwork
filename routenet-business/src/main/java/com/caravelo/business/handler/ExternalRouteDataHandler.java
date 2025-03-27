package com.caravelo.business.handler;

import com.caravelo.business.model.Route;
import com.caravelo.business.model.Station;
import com.caravelo.business.model.external.FlightData;
import com.caravelo.business.model.external.StationData;
import com.caravelo.business.model.mapper.ExternalMapper;
import com.caravelo.business.service.BackupRoutesExtApiService;
import com.caravelo.business.service.RoutesExtApiService;
import com.caravelo.business.service.RouteService;
import com.caravelo.business.service.StationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Handle external API calls
 */
@Component
public class ExternalRouteDataHandler {

	private static final Logger LOGGER = LogManager.getLogger(ExternalRouteDataHandler.class);

	private final BackupRoutesExtApiService backupRoutesExtApiService;

	private final RoutesExtApiService flightsApiService;

	private final RouteService routeService;

	private final StationService stationService;

	private final ExternalMapper externalMapper = ExternalMapper.INSTANCE;

	public ExternalRouteDataHandler(BackupRoutesExtApiService backupRoutesExtApiService, RoutesExtApiService flightsApiService, RouteService routeService, StationService stationService) {
		this.backupRoutesExtApiService = backupRoutesExtApiService;
		this.flightsApiService = flightsApiService;
		this.routeService = routeService;
		this.stationService	= stationService;
	}

	/**
	 * Get the routes from the main API. If fails the process it is done on the backup one
	 */
	public void getRoutesData() {
		try {
			List<StationData> flights = flightsApiService.getRoutes();
			extractStations(flights);
			List<Route> routes = externalMapper.mapExtDataToRoutes(flights);
			routeService.saveAll(routes);
		} catch(Exception e) {
			List<FlightData> flights = backupRoutesExtApiService.getRoutes();
			extractBackupStations(flights);
			routeService.saveAll(externalMapper.mapBackupDataToRoutes(flights));
		}
	}

	/**
	 * Storage different stations. It is preferable to have this data manually set on database instead of getting it dynamically
	 * @param routes routes extracted from the backup API
	 */
	private void extractBackupStations(List<FlightData> routes) {
		try {
			List<Station> stations = externalMapper.mapFlightDataToStations(routes);
			stationService.saveAll(stations);
		} catch(Exception e) {
			LOGGER.error("Error storing Stations from backup", e);
		}
	}

	/**
	 * Storage different stations. It is preferable to have this data manually set on database instead of getting it dynamically
	 * @param routes routes extracted from the API
	 */
	private void extractStations(List<StationData> routes) {
		try {
			List<Station> stations = externalMapper.mapStationsFromSD(routes);
			stationService.saveAll(stations);
		} catch(Exception e) {
			LOGGER.error("Error storing Stations from main API", e);
		}
	}

}
