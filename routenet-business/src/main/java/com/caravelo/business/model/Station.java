package com.caravelo.business.model;

import lombok.Data;

@Data
public class Station {

	private String id;

	private String externalId;

	public String name;

	private String code;

	private String alias;

	private String country;
}
