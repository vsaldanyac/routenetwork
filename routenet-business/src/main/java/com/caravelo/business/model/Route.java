package com.caravelo.business.model;

import lombok.Data;

@Data
public class Route {

	private String id;

	private String externalId;

	public String name;

	public String flightNumber;

	public Station originStation;

	public Station destinationStation;

	public Airline airline;
}
