package uk.chrismay.springtest.service;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import uk.chrismay.springtest.domain.Ride;
import uk.chrismay.springtest.domain.Route;

public interface RideService {

	public final static long NON_EXISTENT_ENTITY_ID=-1;

	@Transactional
	public long createRide(Route route);
	
	@Transactional
	public long createRoute(String name);

	@Transactional(readOnly=true)
	public Collection<Ride> getAllRides();

	@Transactional(readOnly=true)
	public Ride getRide(long id);
	
	@Transactional(readOnly=true)
	public Route getRoute(long id);

	@Transactional(readOnly=true)
	public Collection<Route> getAllRoutes();
	
}