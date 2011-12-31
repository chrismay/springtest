package uk.chrismay.springtest.service;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import uk.chrismay.springtest.domain.Ride;
import uk.chrismay.springtest.domain.Route;

public interface RideService {

	long NON_EXISTENT_ENTITY_ID = -1;

	@Transactional
	long createRide(Ride r);

	@Transactional
	long createRoute(String name);

	@Transactional(readOnly = true)
	Collection<Ride> getAllRides();

	@Transactional(readOnly = true)
	Ride getRide(long id);

	@Transactional(readOnly = true)
	Route getRoute(long id);

	@Transactional(readOnly = true)
	Collection<Route> getAllRoutes();

}