package com.caravelo.business.service;

import com.caravelo.business.model.external.FlightData;

import java.util.List;

public interface BackupFlightsApiService {

	List<FlightData> getFlights();
}
