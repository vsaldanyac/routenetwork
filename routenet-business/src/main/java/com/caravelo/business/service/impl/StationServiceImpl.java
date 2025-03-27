	package com.caravelo.business.service.impl;

	import com.caravelo.business.model.Station;
	import com.caravelo.business.model.mapper.StationEntityMapper;
	import com.caravelo.business.service.StationService;
	import com.caravelo.common.TenantContext;
	import com.caravelo.persistence.dao.StationDAO;
	import org.springframework.stereotype.Service;
	import org.springframework.transaction.annotation.Transactional;

	import java.util.List;

	/**
	 * Service to manage the station access to data layer
	 */
	@Service
	public class StationServiceImpl implements StationService {

		private final StationEntityMapper stationMapper = StationEntityMapper.INSTANCE;

		private final StationDAO stationDAO;

		public StationServiceImpl(StationDAO stationDAO) {
			this.stationDAO = stationDAO;
		}

		@Override
		public List<Station> findAll() {
			return stationMapper.map(stationDAO.findAllByTenantId(TenantContext.getTenantId()));
		}

		@Override
		@Transactional
		public void saveAll(List<Station> stations) {
			stationDAO.saveAll(stationMapper.mapToEntity(stations));
		}
	}
