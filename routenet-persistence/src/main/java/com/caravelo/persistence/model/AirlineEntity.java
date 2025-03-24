package com.caravelo.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Table(name = "AIRLINE", uniqueConstraints=
@UniqueConstraint(columnNames={"tenantId", "externalId"}))
@Getter
@Setter
@Entity
public class AirlineEntity extends AuditableEntity {

	public String name;
}
