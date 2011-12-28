package uk.chrismay.springtest.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;

import uk.chrismay.springtest.dao.RouteDao;
import uk.chrismay.springtest.domain.Route;

@Component("routeDao")
public class RouteDaoJpa  implements RouteDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Route save(Route r) {
		entityManager.persist(r);
		return r;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Route> findByName(String name) {
		Query q = entityManager.createQuery(Route.QUERY_FIND_BY_NAME);
		q.setParameter(1, name);
		return q.getResultList();
	}
	
	public Route findById(long id) {
		return entityManager.find(Route.class, id);
	}
	

}
