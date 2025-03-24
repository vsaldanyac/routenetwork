package com.caravelo.persistence.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(name = "ROUTE", uniqueConstraints=
@UniqueConstraint(columnNames={"tenantId", "externalId"}))
@Getter
@Setter
@Entity
public class RouteEntity extends AuditableEntity {

	public String name;

	public String flightNumber;

	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "originStationId")
	public StationEntity originStation;

	@ManyToOne (cascade = CascadeType.ALL)
	@JoinColumn(name = "destinationStationId")
	public StationEntity destinationStation;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "airlineId")
	public AirlineEntity airline;
}
