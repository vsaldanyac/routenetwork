package com.caravelo.api.dto;

import com.caravelo.business.model.Airline;
import com.caravelo.business.model.Station;
import lombok.Data;

/**
 * Route API response
 */
@Data
public class RouteResponseDTO {

	private String id;

	private String externalId;

	private String name;

	private String flightNumber;

	private Station originStation;

	private Station destinationStation;

	private Airline airline;
}
