package uk.chrismay.springtest.service;

import org.springframework.transaction.annotation.Transactional;

import uk.chrismay.springtest.domain.Ride;
import uk.chrismay.springtest.domain.Route;

@Transactional(readOnly=true)
public interface RideService {

	long NON_EXISTENT_ENTITY_ID = -1;

	@Transactional(readOnly = false)
	long createRide(Ride r);

	@Transactional(readOnly = false)
	long createRoute(String name);

	Iterable<Ride> getAllRides();

	Ride getRide(long id);

	Route getRoute(long id);

	Iterable<Route> getAllRoutes();

}