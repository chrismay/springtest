package uk.chrismay.springtest.dao.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import uk.chrismay.springtest.dao.RouteDao;
import uk.chrismay.springtest.domain.Route;

@ContextConfiguration(locations = { "/hsql-tests.xml","/spring-persistence.xml" })
public class RouteDaoJpaTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private RouteDao routeDao;

	@Test
	public void saveAllocatesAnId(){
		Route r = new Route("testy");
		Route saved = routeDao.save(r);
		assertTrue(saved.getId() > 0);
	}
	
	@Test
	public void findByNameReturnsEmptySetWhenNoMatch(){
		routeDao.save(new Route("test1"));
		routeDao.save(new Route("test2"));
		
		Collection<Route> matches = routeDao.findByName("nothing");
		assertEquals(0, matches.size());
	}
	
	@Test
	public void findByNameReturnsMatchingRoute(){
		routeDao.save(new Route("test1"));
		routeDao.save(new Route("test2"));
		
		Collection<Route> matches = routeDao.findByName("test1");
		assertEquals(1, matches.size());
		assertEquals("test1",matches.iterator().next().getName());
	}
	
	@Test
	public void findByIdWorks(){
		Route route = routeDao.save(new Route("test1"));
		Route sameRoute = routeDao.findById(route.getId());
		assertEquals(route, sameRoute);
	}
	
	@Test
	public void findByIDWhenMissing(){
		Route r = routeDao.findById(99);
		assertNull(r);
	}
	
	@Test
	public void findAll(){
		assertTrue(routeDao.findAll().isEmpty());
		routeDao.save(new Route("test1"));
		routeDao.save(new Route("test2"));
		Collection<Route> all = routeDao.findAll();
		assertEquals(2, all.size());
	}

}
