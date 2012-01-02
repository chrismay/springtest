package uk.chrismay.springtest.dao.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.google.common.collect.Lists;

import uk.chrismay.springtest.dao.RouteDao;
import uk.chrismay.springtest.domain.Route;

@ContextConfiguration(locations = { "/hsql-tests.xml","/spring-persistence.xml" })
public class RouteDaoJpaTest extends AbstractTransactionalJUnit4SpringContextTests{

	private static final long NON_EXISTENT_ROUTE_ID = 99;
	private static final String TEST_ROUTE_NAME = "test1";
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
		routeDao.save(new Route(TEST_ROUTE_NAME));
		routeDao.save(new Route("test2"));
		
		Collection<Route> matches = routeDao.findByName("nothing");
		assertEquals(0, matches.size());
	}
	
	@Test
	public void findByNameReturnsMatchingRoute(){
		routeDao.save(new Route(TEST_ROUTE_NAME));
		routeDao.save(new Route("test2"));
		
		Collection<Route> matches = routeDao.findByName(TEST_ROUTE_NAME);
		assertEquals(1, matches.size());
		assertEquals(TEST_ROUTE_NAME,matches.iterator().next().getName());
	}
	
	@Test
	public void findByIdWorks(){
		Route route = routeDao.save(new Route(TEST_ROUTE_NAME));
		Route sameRoute = routeDao.findOne(route.getId());
		assertEquals(route, sameRoute);
	}
	
	@Test
	public void findByIDWhenMissing(){
		Route r = routeDao.findOne(NON_EXISTENT_ROUTE_ID);
		assertNull(r);
	}
	
	@Test
	public void findAll(){
		assertTrue(!routeDao.findAll().iterator().hasNext());
		routeDao.save(new Route(TEST_ROUTE_NAME));
		routeDao.save(new Route("test2"));
		Collection<Route> all = Lists.newArrayList(routeDao.findAll());
		assertEquals(2, all.size());
	}

}
