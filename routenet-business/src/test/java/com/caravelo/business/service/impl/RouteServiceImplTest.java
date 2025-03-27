package com.caravelo.business.service.impl;

import com.caravelo.business.model.Route;
import com.caravelo.common.TenantContext;
import com.caravelo.persistence.dao.RouteDAO;
import com.caravelo.persistence.model.RouteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class RouteServiceImplTest {

	@InjectMocks
	private RouteServiceImpl routeService;

	@Mock
	private RouteDAO routeDAO;

	private Pageable pageable;
	private final String STATION_CODE = "MEX";

	private final String STATION_CODE_NOT_FOUND = "BCN";

	private final String TENANT_ID_VALID = "TENANT_VALID";

	private final String TENANT_ID_INVALID = "TENANT_INVALID";

	private final RouteEntity ROUTE_1 = new RouteEntity();

	private final String ROUTE_NAME = "ROUTE_NAME";

	private final List<RouteEntity> ROUTE_LIST = Collections.singletonList(ROUTE_1);

	private final List<RouteEntity> EMPTY_ROUTE_LIST = Collections.emptyList();

	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		ROUTE_1.setName(ROUTE_NAME);
		Mockito.when(routeDAO.findAllByTenantId(TENANT_ID_VALID, pageable)).thenReturn(ROUTE_LIST);
		Mockito.when(routeDAO.findAllByTenantId(TENANT_ID_INVALID, pageable)).thenReturn(EMPTY_ROUTE_LIST);
		Mockito.when(routeDAO.findByTenantIdAndOriginStation_Code(TENANT_ID_VALID, STATION_CODE, pageable)).thenReturn(ROUTE_LIST);
	}

	@Test
	public void findAllTest() {
		TenantContext.setTenantId(TENANT_ID_VALID);
		List<Route> routes = routeService.findAll(pageable);
		assertFalse(routes.isEmpty());
		assertEquals(routes.get(0).getName(), ROUTE_NAME);
	}

	@Test
	public void findAllInvalidTenantTest() {
		TenantContext.setTenantId(TENANT_ID_INVALID);
		List<Route> routes = routeService.findAll(pageable);
		assertTrue(routes.isEmpty());
	}

	@Test
	public void findByStationTest() {
		TenantContext.setTenantId(TENANT_ID_VALID);
		List<Route> routes = routeService.findByStation(pageable, STATION_CODE);
		assertFalse(routes.isEmpty());
		assertEquals(routes.get(0).getName(), ROUTE_NAME);
	}

	@Test
	public void findByStationNotFoundTest() {
		TenantContext.setTenantId(TENANT_ID_VALID);
		List<Route> routes = routeService.findByStation(pageable, STATION_CODE_NOT_FOUND);
		assertTrue(routes.isEmpty());
	}

	@Test
	public void findByStationInvalidTenantTest() {
		TenantContext.setTenantId(TENANT_ID_INVALID);
		List<Route> routes = routeService.findByStation(pageable, STATION_CODE);
		assertTrue(routes.isEmpty());
	}

}
