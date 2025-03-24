	package com.caravelo.business.service.impl;

	import com.caravelo.business.model.Route;
	import com.caravelo.business.model.mapper.RouteEntityMapper;
	import com.caravelo.business.service.RouteService;
	import com.caravelo.common.TenantContext;
	import com.caravelo.persistence.dao.RouteDAO;
	import org.springframework.data.domain.Pageable;
	import org.springframework.stereotype.Service;
	import org.springframework.transaction.annotation.Transactional;

	import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {

	private final RouteEntityMapper routeMapper = RouteEntityMapper.INSTANCE;
	private final RouteDAO routeDAO;

	public RouteServiceImpl(RouteDAO routeDAO) {
		this.routeDAO = routeDAO;
	}

	@Override
	public List<Route> findAll(Pageable pageable) {
		return routeMapper.map(routeDAO.findAllByTenantId(TenantContext.getTenantId(), pageable));
	}

	@Override
	public List<Route> findByStation(Pageable pageable, String stationCode) {
		return routeMapper.map(routeDAO.findByTenantIdAndOriginStation_Code(TenantContext.getTenantId(), stationCode, pageable));
	}

	@Override
	@Transactional
	public void saveAll(List<Route> routes) {
		routeDAO.saveAll(routeMapper.mapToEntity(routes));
	}
}
