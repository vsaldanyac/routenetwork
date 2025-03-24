package com.caravelo.business.model.external;

import lombok.Data;

@Data
public class GlobalPosition {
	private int coordTextX;
	private int coordTextY;
	private int coordX;
	private int coordY;
	private int globalPositionID;
	private int stationID;
	private String textColor;
}
