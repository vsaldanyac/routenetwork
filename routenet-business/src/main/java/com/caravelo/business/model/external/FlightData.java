package com.caravelo.business.model.external;

import lombok.Data;

import java.util.List;

@Data
public class FlightData {
	private String airline_iata;
	private String airline_icao;
	private String flight_number;
	private String flight_iata;
	private String flight_icao;
	private String cs_airline_iata;
	private String cs_flight_iata;
	private String cs_flight_number;
	private String dep_iata;
	private String dep_icao;
	private List<String> dep_terminals;
	private String dep_time;
	private String dep_time_utc;
	private String arr_iata;
	private String arr_icao;
	private List<String> arr_terminals;
	private String arr_time;
	private String arr_time_utc;
	private Integer duration;
	private String aircraft_icao;
	private Integer counter;
	private String updated;
	private List<String> days;
}
