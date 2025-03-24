package com.caravelo.business.model.external;

import lombok.Data;

import java.util.List;

@Data
public class StationData {
	private List<ElapsedTime> elapsedTimes;
	private String exclusiveCarrier;
	private String stationCode;
	private String stationLocalTime;
	private String stationLocalTimeString;
	private String stationName;
	private String arrivalTerminal;
	private String terminal;
	private String alias;
	private String bestDayID;
	private Country country;
	private GlobalPosition globalPosition;
	private boolean isNew;
	private boolean isPrincipal;
	private Mac mac;
	private Route route;
}
