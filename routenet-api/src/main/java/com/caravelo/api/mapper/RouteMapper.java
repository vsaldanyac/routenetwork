package com.caravelo.api.mapper;

import com.caravelo.business.model.Route;
import com.caravelo.api.dto.RouteResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Map the DTO objects to business objects
 */
@Mapper
public interface RouteMapper {

	RouteMapper INSTANCE = Mappers.getMapper(RouteMapper.class);

	RouteResponseDTO map(Route routeResponseDTO);

	List<RouteResponseDTO> map(List<Route> routeResponseDTO);
}
