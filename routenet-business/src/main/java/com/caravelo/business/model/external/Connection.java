package com.caravelo.business.model.external;

import lombok.Data;

@Data
public class Connection {
	private String arrivalStation;
	private String routeName;
	private String connectionStation;
	private int routeDirect;
}
