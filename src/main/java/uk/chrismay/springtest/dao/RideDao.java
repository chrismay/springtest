package uk.chrismay.springtest.dao;

import java.util.Collection;

import uk.chrismay.springtest.domain.Ride;
import uk.chrismay.springtest.domain.Route;

public interface RideDao {
	Ride findById(long id);
	Collection<Ride> findByRoute(Route route);
	Ride save(Ride r);
	Collection<Ride> findAll();
}
