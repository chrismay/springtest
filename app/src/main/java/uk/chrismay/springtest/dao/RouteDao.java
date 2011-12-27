package uk.chrismay.springtest.dao;

import java.util.Collection;

import uk.chrismay.springtest.domain.Route;

public interface RouteDao {

	Route save(Route r);
	Collection<Route> findByName(String name);
	Route findById(long id);
}
