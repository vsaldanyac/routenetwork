package com.caravelo.business.model.mapper;

import com.caravelo.business.model.Station;
import com.caravelo.persistence.model.StationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StationEntityMapper {

	StationEntityMapper INSTANCE = Mappers.getMapper(StationEntityMapper.class);

	StationEntity mapToEntity(Station station);

	List<StationEntity> mapToEntity(List<Station> stations);

	List<Station> map(List<StationEntity> stations);
}
