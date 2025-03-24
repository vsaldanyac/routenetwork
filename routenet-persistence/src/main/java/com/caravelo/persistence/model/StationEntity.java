package com.caravelo.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Table(name = "STATION", uniqueConstraints=
@UniqueConstraint(columnNames={"tenantId", "externalId"}))
@Getter
@Setter
@Entity
public class StationEntity extends AuditableEntity {

	public String name;

	private String code;

	private String alias;

	private String country;
}
