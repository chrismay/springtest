package uk.chrismay.springtest.service;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import uk.chrismay.springtest.domain.Ride;
import uk.chrismay.springtest.domain.Route;

public interface RideService {

	@Transactional
	public abstract Ride createRide(Route route);

	@Transactional
	public abstract Collection<Ride> getAllRides();

	@Transactional
	public abstract Route createRoute(String name);

}