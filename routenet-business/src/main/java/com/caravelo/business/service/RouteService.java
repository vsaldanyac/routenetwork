package com.caravelo.business.service;

import com.caravelo.business.model.Route;

import java.util.List;

public interface RouteService {

	List<Route> findAll();

	void saveAll(List<Route> routeEntities);
}
