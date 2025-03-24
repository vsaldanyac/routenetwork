package com.caravelo.business.service;

import com.caravelo.business.model.Route;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RouteService {

	List<Route> findAll(Pageable pageable);

	List<Route> findByStation(Pageable pageable, String stationCode);

	void saveAll(List<Route> routeEntities);
}
