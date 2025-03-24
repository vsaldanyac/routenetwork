package com.caravelo.persistence.mapper;

import com.caravelo.persistence.dto.RouteResponseDTO;
import com.caravelo.business.model.Route;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RouteMapper {

	RouteMapper INSTANCE = Mappers.getMapper(RouteMapper.class);

	RouteResponseDTO map(Route routeResponseDTO);

	List<RouteResponseDTO> map(List<Route> route);
}
