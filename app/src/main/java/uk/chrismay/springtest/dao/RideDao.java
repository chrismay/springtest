package uk.chrismay.springtest.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import uk.chrismay.springtest.domain.Ride;
import uk.chrismay.springtest.domain.Route;

public interface RideDao extends CrudRepository<Ride, Long>{
	List<Ride> findByRoute(Route route);
}
