package com.caravelo.business.model.external;

import lombok.Data;

import java.util.List;

@Data
public class Route {

	private List<Connection> connection;
	private List<Direct> direct;
}
