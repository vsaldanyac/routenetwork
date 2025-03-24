package com.caravelo.business.model.mapper;

import com.caravelo.business.model.Route;
import com.caravelo.persistence.model.RouteEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {StationEntityMapper.class})
public interface RouteEntityMapper {

	RouteEntityMapper INSTANCE = Mappers.getMapper(RouteEntityMapper.class);



	Route map(RouteEntity routeEntity);

	@InheritInverseConfiguration
	RouteEntity mapToEntity(Route route);

	List<RouteEntity> mapToEntity(List<Route> routes);

	List<Route> map(List<RouteEntity> route);


}
