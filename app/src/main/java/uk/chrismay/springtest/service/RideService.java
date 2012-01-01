package uk.chrismay.springtest.service;

import java.util.Collection;

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

	Collection<Ride> getAllRides();

	Ride getRide(long id);

	Route getRoute(long id);

	Collection<Route> getAllRoutes();

}