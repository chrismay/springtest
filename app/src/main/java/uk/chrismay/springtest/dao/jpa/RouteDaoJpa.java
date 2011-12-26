package uk.chrismay.springtest.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	

}
