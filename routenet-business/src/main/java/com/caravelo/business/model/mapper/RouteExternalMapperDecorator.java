package com.caravelo.business.model.mapper;

import com.caravelo.business.model.Route;
import com.caravelo.business.model.Station;
import com.caravelo.business.model.external.Direct;
import com.caravelo.business.model.external.FlightData;
import com.caravelo.business.model.external.StationData;

import java.util.ArrayList;
import java.util.List;

public class RouteExternalMapperDecorator implements ExternalMapper {

	private final ExternalMapper delegate;

	public RouteExternalMapperDecorator(ExternalMapper delegate) {
		this.delegate = delegate;
	}


	@Override
	public List<Route> mapExtDataToRoutes(List<StationData> stations) {
		List<Route> routes = new ArrayList<>();
		stations.stream().filter(ext -> ext.getRoute().getDirect() != null).forEach(extStation -> extStation.getRoute()
			  .getDirect().forEach(extRoute -> {
			if (extRoute != null) {
				Route newRoute = new Route();
				newRoute.setName(extRoute.getRouteName());
				newRoute.setOriginStation(mapDepStationDataToStation(extStation));
				newRoute.setDestinationStation(mapArrStationDataToStation(extRoute));
				routes.add(newRoute);
			}
		}));
		return routes;
	}

	@Override
	public List<Route> mapBackupDataToRoutes(List<FlightData> stations) {
		return delegate.mapBackupDataToRoutes(stations);
	}

	@Override
	public List<Station> mapFlightDataToStations(List<FlightData> flightDataList) {
		List<Station> stations = new ArrayList<>();
		flightDataList.forEach(flightData -> {
			Station departureStation = new Station();
			departureStation.setExternalId(flightData.getDep_iata());
			departureStation.setName(flightData.getDep_icao());

			Station arrivalStation = new Station();

			arrivalStation.setExternalId(flightData.getArr_iata());
			arrivalStation.setName(flightData.getArr_icao());
		});

		return stations;
	}

	@Override
	public Station mapDepStationDataToStation(StationData stationData) {
		return delegate.mapDepStationDataToStation(stationData);
	}

	@Override
	public Station mapArrStationDataToStation(Direct direct) {
		return delegate.mapArrStationDataToStation(direct);
	}

	@Override
	public List<Station> mapStationsFromSD(List<StationData> stationsData) {
		List<Station> stations = new ArrayList<>();
		stationsData.forEach(extStation -> {
			stations.add(mapDepStationDataToStation(extStation));
			if (extStation.getRoute().getDirect() != null) {
				extStation.getRoute().getDirect().forEach(extRoute -> stations.add(mapArrStationDataToStation(extRoute)));
			}
		});
		return stations;
	}

}
