package uk.chrismay.springtest.service;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import uk.chrismay.springtest.dao.RideDao;
import uk.chrismay.springtest.domain.Ride;
import uk.chrismay.springtest.domain.Route;

public class RideService {

	private final RideDao rideDao;
	
	public RideService(RideDao rideDao) {
		this.rideDao = rideDao;
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
	
}
