package com.caravelo.persistence.dao;

import com.caravelo.persistence.model.StationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationDAO extends JpaRepository<StationEntity, Long> {

	List<StationEntity> findAllByTenantId(String tenantId);
}
