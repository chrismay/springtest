package uk.chrismay.springtest.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import uk.chrismay.springtest.domain.Route;

public interface RouteDao extends CrudRepository<Route,Long> {

	List<Route> findByName(String name);
}
