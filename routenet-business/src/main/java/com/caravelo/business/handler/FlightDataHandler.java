package com.caravelo.business.handler;

import com.caravelo.business.model.Route;
import com.caravelo.business.model.Station;
import com.caravelo.business.model.external.FlightData;
import com.caravelo.business.model.external.StationData;
import com.caravelo.business.model.mapper.ExternalMapper;
import com.caravelo.business.service.BackupFlightsApiService;
import com.caravelo.business.service.FlightsApiService;
import com.caravelo.business.service.RouteService;
import com.caravelo.business.service.StationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlightDataHandler {

	private static final Logger LOGGER = LogManager.getLogger(FlightDataHandler.class);

	private final BackupFlightsApiService backupFlightsApiService;

	private final FlightsApiService flightsApiService;

	private final RouteService routeService;

	private final StationService stationService;

	private final ExternalMapper externalMapper = ExternalMapper.INSTANCE;

	public FlightDataHandler(BackupFlightsApiService backupFlightsApiService, FlightsApiService flightsApiService, RouteService routeService, StationService stationService) {
		this.backupFlightsApiService = backupFlightsApiService;
		this.flightsApiService = flightsApiService;
		this.routeService = routeService;
		this.stationService	= stationService;
	}

	public void getFlightsData() {
		try {
			List<StationData> flights = flightsApiService.getFlights();
			extractStations(flights);
			List<Route> routes = externalMapper.mapExtDataToRoutes(flights);
			routeService.saveAll(routes);
		} catch(Exception e) {
			List<FlightData> flights = backupFlightsApiService.getFlights();
			extractBackupStations(flights);
			routeService.saveAll(externalMapper.mapBackupDataToRoutes(flights));
		}
	}

	private void extractBackupStations(List<FlightData> flights) {
		try {
			List<Station> stations = externalMapper.mapFlightDataToStations(flights);
			stationService.saveAll(stations);
		} catch(Exception e) {
			LOGGER.error("Error storing Stations from backup", e);
		}
	}

	private void extractStations(List<StationData> flights) {
		try {
			List<Station> stations = externalMapper.mapStationsFromSD(flights);
			stationService.saveAll(stations);
		} catch(Exception e) {
			LOGGER.error("Error storing Stations from main API", e);
		}
	}

}
