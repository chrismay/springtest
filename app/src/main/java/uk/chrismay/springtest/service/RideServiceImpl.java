package uk.chrismay.springtest.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import uk.chrismay.springtest.dao.RideDao;
import uk.chrismay.springtest.dao.RouteDao;
import uk.chrismay.springtest.domain.Ride;
import uk.chrismay.springtest.domain.Route;

@Component("rideService")
public class RideServiceImpl implements RideService {

	private final RideDao rideDao;
	private final RouteDao routeDao;

	@Autowired
	public RideServiceImpl(RideDao rideDao, RouteDao routeDao) {
		this.rideDao = rideDao;
		this.routeDao = routeDao;
	}

	@Transactional
	public long createRide(Ride ride) {
		return rideDao.save(ride).getId();
	}

	@Transactional
	public Collection<Ride> getAllRides() {
		return rideDao.findAll();
	}

	@Transactional
	public long createRoute(String name) {
		Collection<Route> preExisting = routeDao.findByName(name);
		if (preExisting.isEmpty()) {
			Route r = new Route(name);
			return routeDao.save(r).getId();
		} else {
			return NON_EXISTENT_ENTITY_ID;
		}
	}

	@Transactional
	public Ride getRide(long id) {
		return rideDao.findById(id);
	}

	@Transactional
	public Route getRoute(long id) {
		return routeDao.findById(id);
	}

	@Transactional
	public Collection<Route> getAllRoutes() {
		// TODO Auto-generated method stub
		return routeDao.findAll();
	}

}
