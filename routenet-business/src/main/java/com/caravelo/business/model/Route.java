package com.caravelo.business.model;

import lombok.Data;

@Data
public class Route {

	private String id;

	private String externalId;

	private String name;

	private String flightNumber;

	private Station originStation;

	private Station destinationStation;

	private Airline airline;
}
