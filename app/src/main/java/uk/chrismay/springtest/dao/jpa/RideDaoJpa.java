package uk.chrismay.springtest.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import uk.chrismay.springtest.dao.RideDao;
import uk.chrismay.springtest.domain.Ride;
import uk.chrismay.springtest.domain.Route;

@Component("rideDao")
public class RideDaoJpa  implements RideDao {

	@PersistenceContext
	private EntityManager entityManager;
	

	public Ride findById(long id) {
		return  entityManager.find(Ride.class, id);
	}

	@SuppressWarnings("unchecked")
	public Collection<Ride> findByRoute(Route route) {
		Query q =  entityManager.createQuery(Ride.QUERY_FIND_BY_ROUTE);
		q.setParameter(1, route);
		return q.getResultList();
	}

	public Ride save(Ride r) {
		entityManager.persist(r);
		return r;
	}

	@SuppressWarnings("unchecked")
	public Collection<Ride> findAll() {
		Query q = entityManager.createQuery(Ride.QUERY_FIND_ALL);
		return q.getResultList();
	}

}
