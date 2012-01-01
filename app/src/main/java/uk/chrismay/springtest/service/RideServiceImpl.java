package uk.chrismay.springtest.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

	public long createRide(Ride ride) {
		Route persistentRoute = routeDao.findById(ride.getRoute().getId());
		ride.setRoute(persistentRoute);
		return rideDao.save(ride).getId();
	}

	public Collection<Ride> getAllRides() {
		return rideDao.findAll();
	}

	public long createRoute(String name) {
		Collection<Route> preExisting = routeDao.findByName(name);
		if (preExisting.isEmpty()) {
			Route r = new Route(name);
			return routeDao.save(r).getId();
		} else {
			return NON_EXISTENT_ENTITY_ID;
		}
	}

	public Ride getRide(long id) {
		return rideDao.findById(id);
	}

	public Route getRoute(long id) {
		return routeDao.findById(id);
	}

	public Collection<Route> getAllRoutes() {
		return routeDao.findAll();
	}

}
