package com.caravelo.persistence.controllers;

import com.caravelo.business.service.RouteService;
import com.caravelo.persistence.dto.RouteResponseDTO;
import com.caravelo.persistence.mapper.RouteMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "routes")
public class RouteController {

	private static final Logger LOGGER = LogManager.getLogger(RouteController.class);

	private final RouteService routeService;

	private final RouteMapper routeMapper = RouteMapper.INSTANCE;

	public RouteController(RouteService routeService) {
		this.routeService = routeService;
	}

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "GET the routes", description = "GET the routes.")
	@ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json", schema = @Schema(implementation = RouteResponseDTO[].class)))
	public ResponseEntity<?> request(@RequestParam(value = "station", required = false) String stationCode, Pageable pageable) {

		List<RouteResponseDTO> routes;
		if (stationCode == null) {
			routes = routeMapper.map(routeService.findAll(pageable));
		} else {
			routes = routeMapper.map(routeService.findByStation(pageable, stationCode));
		}
		return ResponseEntity.ok(routes);

	}
}
