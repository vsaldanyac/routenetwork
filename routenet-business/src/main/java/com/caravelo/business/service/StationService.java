package com.caravelo.business.service;

import com.caravelo.business.model.Station;

import java.util.List;

public interface StationService {

	List<Station> findAll();

	void saveAll(List<Station> routeEntities);
}
