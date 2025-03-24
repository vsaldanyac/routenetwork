package com.caravelo.business.service;

import com.caravelo.business.model.external.StationData;

import java.util.List;

public interface FlightsApiService {

	List<StationData> getFlights();
}
