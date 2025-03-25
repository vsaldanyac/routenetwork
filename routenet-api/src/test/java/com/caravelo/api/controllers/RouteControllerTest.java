package com.caravelo.api.controllers;

import com.caravelo.api.dto.RouteResponseDTO;
import com.caravelo.business.model.Route;
import com.caravelo.business.model.Station;
import com.caravelo.business.service.impl.RouteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RouteControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@InjectMocks
	RouteController routeController;

	@Mock
	RouteServiceImpl routeService;

	private Pageable pageable;

	private final String STATION_CODE = "MEX";

	private final String ROUTE_NAME = "MEX_ROUTE_1";

	private List<Route> ROUTES = new ArrayList<>();

	private final Route ROUTE_1 = new Route();
	private final Station STATION_1 = new Station();

	private final RouteResponseDTO ROUTE_DTO = new RouteResponseDTO();

	private List<RouteResponseDTO> ROUTES_DTO = new ArrayList<>();

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	public void init() {

		STATION_1.setCode(STATION_CODE);
		ROUTE_1.setOriginStation(STATION_1);
		ROUTE_1.setName(ROUTE_NAME);
		ROUTES.add(ROUTE_1);

		ROUTE_DTO.setName(ROUTE_NAME);
		ROUTE_DTO.setOriginStation(STATION_1);

		ROUTES_DTO.add(ROUTE_DTO);

		Mockito.when(routeService.findAll(Mockito.any())).thenReturn(new ArrayList<>());
		Mockito.when(routeService.findByStation(Mockito.any(), Mockito.any())).thenReturn(ROUTES);

	}

	@Test
	public void getEndpointTest() {
		String url = "http://localhost:" + port + "/routes";
		ResponseEntity<?> response = restTemplate.getForEntity(url, Object.class);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(response.getHeaders().getContentType(), "application/json");
	}

	@Test
	public void getRouteTest() {

		ResponseEntity<?> response = routeController.getRoutes(STATION_CODE, pageable);
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		assertEquals(response.getBody(), ROUTES_DTO);
		List<RouteResponseDTO> listRoutes = (List<RouteResponseDTO>) response.getBody();
		assertEquals(listRoutes.get(0), ROUTE_DTO);
		assertEquals(listRoutes.get(0).getOriginStation().getCode(), STATION_CODE);
	}


}
