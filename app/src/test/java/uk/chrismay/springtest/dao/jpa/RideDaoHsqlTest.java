package uk.chrismay.springtest.dao.jpa;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

import uk.chrismay.springtest.dao.RideDao;
import uk.chrismay.springtest.dao.RouteDao;
import uk.chrismay.springtest.domain.Ride;
import uk.chrismay.springtest.domain.Route;

import com.google.common.collect.Lists;

@ContextConfiguration(locations = { "/hsql-tests.xml","/spring-persistence.xml" })
@Transactional
public class RideDaoHsqlTest extends
		AbstractTransactionalJUnit4SpringContextTests {

	@Autowired
	private RideDao rideDao;

	@Autowired
	private RouteDao routeDao;
	
	@PersistenceContext
	private EntityManager entityManager;

	private final Route route = new Route("test route");

	@Test
	public void saveAndLoad() {

		Ride r = new Ride(route);
		r.setComments("test");
		Ride saved = rideDao.save(r);
		entityManager.flush();
		Ride r2 = rideDao.findOne(saved.getId());
		assertEquals(r2.getId(), saved.getId());
		assertEquals("test", r2.getComments());
	}
	
	@Test
	public void testSaveSetsId(){
		Route otherRoute = new Route("not used elsewhere");
		Ride r = new Ride(otherRoute);
		Ride saved = rideDao.save(r);
		assertTrue(saved.getId() > 0);
		assertTrue(saved.getRoute().getId() > 0);	
	}
	

	@Test
	public void persistRoutes() {
		Ride r = new Ride(route);
		r.setComments("test");
		Ride saved = rideDao.save(r);
		entityManager.flush();
		Ride r2 = rideDao.findOne(saved.getId());
		assertEquals(r2.getId(), saved.getId());
		assertNotNull(r2.getRoute());
		assertEquals("test route", r2.getRoute().getName());
	}

	@Test
	public void findByRoute() {

		Route savedRoute = routeDao.save(route);
		Route savedRoute2 = routeDao.save(new Route("test 2"));
		Ride r1 = new Ride(savedRoute);
		Ride r2 = new Ride(savedRoute);
		Ride r3 = new Ride(savedRoute2);
		rideDao.save(r1);
		rideDao.save(r2);
		rideDao.save(r3);

		Collection<Ride> rides = Lists.newArrayList(rideDao.findByRoute(savedRoute));
		assertEquals(2, rides.size());
		assertThat(rides, hasItem(new RideMatcher(r1)));
		assertThat(rides, hasItem(new RideMatcher(r2)));

		assertThat(rides, not( hasItem(new RideMatcher(r3))));
	}
	
	@Test
	public void findAll(){
		Route savedRoute = routeDao.save(route);

		Ride r1 = new Ride(savedRoute);
		Ride r2 = new Ride(savedRoute);

		rideDao.save(r1);
		rideDao.save(r2);

		Collection<Ride> rides = Lists.newArrayList(rideDao.findAll());
		assertEquals(2, rides.size());
		assertEquals(2, rides.size());
		assertThat(rides, hasItem(new RideMatcher(r1)));
		assertThat(rides, hasItem(new RideMatcher(r2)));

		
	}

	@Test
	public void updateRide() {
		Ride r = new Ride(route);
		Ride saved = rideDao.save(r);
		entityManager.flush();

		Ride loaded = rideDao.findOne(saved.getId());
		loaded.setComments("new comments");
		rideDao.save(loaded);

		entityManager.flush();

		Ride updated = rideDao.findOne(saved.getId());
		assertEquals("new comments", updated.getComments());
	}
}
