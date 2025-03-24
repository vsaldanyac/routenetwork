package com.caravelo.business.model.mapper;

import com.caravelo.business.model.Route;
import com.caravelo.business.model.Station;
import com.caravelo.business.model.external.Direct;
import com.caravelo.business.model.external.FlightData;
import com.caravelo.business.model.external.StationData;
import org.mapstruct.AfterMapping;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
@DecoratedWith(RouteExternalMapperDecorator.class)
public interface ExternalMapper {

	ExternalMapper INSTANCE = Mappers.getMapper(ExternalMapper.class);

	List<Route> mapExtDataToRoutes(List<StationData> stations);

	List<Route> mapBackupDataToRoutes(List<FlightData> stations);

	List<Station> mapFlightDataToStations(List<FlightData> flightData);

	@Mapping(source = "stationCode", target = "code")
	@Mapping(source = "stationName", target = "name")
	@Mapping(source = "country.countryName", target = "country")
	Station mapDepStationDataToStation(StationData stationData);

	@AfterMapping
	default void concat(@MappingTarget Station station, StationData stationData) {
		station.setExternalId(stationData.getStationCode());
	}

	@Mapping(source = "arrivalStation", target = "name")
	Station mapArrStationDataToStation(Direct direct);

	@AfterMapping
	default void concat(@MappingTarget Station station, Direct direct) {
		station.setCode(direct.getArrivalStation());
		station.setAlias(direct.getArrivalStation());
		station.setExternalId(direct.getArrivalStation());
	}

	List<Station> mapStationsFromSD(List<StationData> stationData);

}
