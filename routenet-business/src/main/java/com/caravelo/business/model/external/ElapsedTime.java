package com.caravelo.business.model.external;

import lombok.Data;

@Data
public class ElapsedTime {

	private String layerName;
	private String methodName;
	private String timeElapsed;
	private int timeElapsedMilliseconds;
}
