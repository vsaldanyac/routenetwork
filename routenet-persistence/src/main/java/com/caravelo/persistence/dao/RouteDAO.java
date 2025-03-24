package com.caravelo.persistence.dao;

import com.caravelo.persistence.model.RouteEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteDAO extends JpaRepository<RouteEntity, Long> {

	List<RouteEntity> findAllByTenantId(String tenantId, Pageable pageable);

	List<RouteEntity> findByTenantIdAndOriginStation_Code(String tenantId, String stationCode, Pageable pageable);
}
