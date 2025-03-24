package com.caravelo.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import com.caravelo.common.TenantContext;

@MappedSuperclass
@Getter
@Setter
public class AuditableEntity extends CommonEntity {


	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATION_TIMESTAMP")
	private LocalDateTime creationTimestamp;


	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "UPDATE_TIMESTAMP")
	private LocalDateTime updateTimestamp;

	@PrePersist
	public void onPrePersist() {

		LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneId.of(ZoneOffset.UTC.getId()));
		setTenantId(TenantContext.getTenantId());
		setId(0L);
		setCreatedBy("system");
		setCreationTimestamp(now);
		setUpdatedBy("system");
		setUpdateTimestamp(now);
	}

	@PreUpdate
	public void onPreUpdate() {

		setUpdatedBy("system");
		setUpdateTimestamp(LocalDateTime.ofInstant(Instant.now(), ZoneId.of(ZoneOffset.UTC.getId())));

	}
}
