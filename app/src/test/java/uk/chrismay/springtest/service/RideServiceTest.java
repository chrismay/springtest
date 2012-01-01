package uk.chrismay.springtest.service;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Collection;
import java.util.List;

import junit.framework.Assert;

import static junit.framework.Assert.*;

import org.junit.Test;
import org.mockito.ArgumentMatcher;
import static org.mockito.Matchers.*;

import uk.chrismay.springtest.dao.RideDao;
import uk.chrismay.springtest.dao.RouteDao;
import uk.chrismay.springtest.domain.Ride;
import uk.chrismay.springtest.domain.Route;

import com.google.common.collect.ImmutableList;

public class RideServiceTest {

	private static final int TEST_ROUTE_ID = 99;
	private static final String TEST_ROUTE_NAME = "test";
	private final Route testRoute = new Route(TEST_ROUTE_NAME);
	private final Ride r = new Ride(testRoute);
	private final Ride r2 = new Ride(testRoute);
	private final RideDao rideDao = mock(RideDao.class);
	private final RouteDao routeDao = mock(RouteDao.class);
	private final RideService service = new RideServiceImpl(rideDao, routeDao);
	

	@Test
	public void createRideUsesSuppliedRoute(){

		r2.setId(TEST_ROUTE_ID);
		when(routeDao.findById(TEST_ROUTE_ID)).thenReturn(testRoute);
		when(rideDao.save(argThat(isRideWithTestRoute()))).thenReturn(r2);
		Route transientRoute = new Route(testRoute.getName());
		transientRoute.setId(TEST_ROUTE_ID);
		long id = service.createRide(new Ride(transientRoute));
		assertEquals(r2.getId(), id);
		verify(rideDao).save(argThat(isRideWithTestRoute()));
	}
	
	@Test
	public void createRouteUsesSuppliedName(){
		Route newRoute = new Route(TEST_ROUTE_NAME);
		newRoute.setId(TEST_ROUTE_ID);
		when(routeDao.save(argThat(isTestRoute()))).thenReturn(newRoute);
		long newRouteId = service.createRoute(TEST_ROUTE_NAME);
		assertEquals(newRoute.getId(), newRouteId);
	}
	
	@Test
	public void cantCreateDuplicateNamedRoutes(){
		Route newRoute = new Route(TEST_ROUTE_NAME);
		when(routeDao.findByName(TEST_ROUTE_NAME)).thenReturn(ImmutableList.of(newRoute));
		long id = service.createRoute(TEST_ROUTE_NAME);
		Assert.assertEquals(RideService.NON_EXISTENT_ENTITY_ID, id);
	}	
	
	@Test
	public void getAllGetsAll(){
		List<Ride> allRides = ImmutableList.of(r,r2);
		when(rideDao.findAll()).thenReturn(allRides);
		
		Collection<Ride> all =  service.getAllRides();
		assertEquals(2, all.size());
		assertTrue(all.contains(r));
		assertTrue(all.contains(r2));
	}
	
	@Test
	public void getAllRoutesGetsAll(){
		List<Route> allRoutes = ImmutableList.of(testRoute);
		when(routeDao.findAll()).thenReturn(allRoutes);
		Collection<Route> all = service.getAllRoutes();
		assertEquals(1, all.size());
		assertTrue(all.contains(testRoute));
	}
	
	@Test
	public void getRideThatExists(){
		r2.setId(TEST_ROUTE_ID);
		when(rideDao.findById(r2.getId())).thenReturn(r2);
		Ride found = service.getRide(r2.getId());
		assertEquals(r2,found);
	}
	
	@Test
	public void getRideThatDoesNotExist(){
		when(rideDao.findById(TEST_ROUTE_ID)).thenReturn(null);
		Ride notFound = service.getRide(TEST_ROUTE_ID);
		assertNull(notFound);
	}
	
	@Test
	public void getRouteThatExists(){
		testRoute.setId(TEST_ROUTE_ID);
		when(routeDao.findById(testRoute.getId())).thenReturn(testRoute);
		Route found = service.getRoute(testRoute.getId());
		assertEquals(testRoute,found);
	}
	
	@Test
	public void getRouteThatDoesNotExist(){
		when(routeDao.findById(TEST_ROUTE_ID)).thenReturn(null);
		Route notFound = service.getRoute(TEST_ROUTE_ID);
		assertNull(notFound);
	}
	
	
	private IsRideWithTestRoute isRideWithTestRoute() {
		return new IsRideWithTestRoute();
	}
	private IsTestRoute isTestRoute(){
		return new IsTestRoute();
	}

	class IsTestRoute extends ArgumentMatcher<Route>{
		@Override
		public boolean matches(Object argument) {
			return ((Route)argument).getName().equals(TEST_ROUTE_NAME);
		}
		
	}
	class IsRideWithTestRoute extends ArgumentMatcher<Ride>{
		@Override
		public boolean matches(Object argument) {
			return ((Ride) argument).getRoute().getName().equals(TEST_ROUTE_NAME);	
		}
		
	}
}
