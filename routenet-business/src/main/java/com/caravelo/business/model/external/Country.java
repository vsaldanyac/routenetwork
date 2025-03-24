package com.caravelo.business.model.external;

import lombok.Data;

import java.util.List;

@Data
public class Country {
	private String countryCode;
	private String countryName;
	private List<ElapsedTime> elapsedTimes;
}
