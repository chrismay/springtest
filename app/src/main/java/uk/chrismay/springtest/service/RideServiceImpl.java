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
	public Ride createRide(Route route){
		Ride r = new Ride(route);
		return rideDao.save(r);
	}
	

	@Transactional
	public Collection<Ride> getAllRides(){
		return rideDao.findAll();
	}
	
	@Transactional
	public Route createRoute(String name){
		Route r = new Route(name);
		return routeDao.save(r);
	}
	
}
