package uk.chrismay.springtest.dao.jpa;

import java.util.Collection;

import org.springframework.orm.jpa.support.JpaDaoSupport;

import uk.chrismay.springtest.dao.RideDao;
import uk.chrismay.springtest.domain.Ride;
import uk.chrismay.springtest.domain.Route;

public class RideDaoJpa extends JpaDaoSupport implements RideDao {

	@Override
	public Ride findById(long id) {
		return getJpaTemplate().find(Ride.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Ride> findByRoute(Route route) {
		return getJpaTemplate().find(Ride.QUERY_FIND_BY_ROUTE, route);
	}

	@Override
	public Ride save(Ride r) {
		getJpaTemplate().persist(r);
		return r;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Ride> findAll() {
		return getJpaTemplate().find(Ride.QUERY_FIND_ALL);
	}

}
