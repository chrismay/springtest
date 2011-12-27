package uk.chrismay.springtest.service;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Collection;
import java.util.List;

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

	private final Route testRoute = new Route("test");
	private final Ride r = new Ride(testRoute);
	private final Ride r2 = new Ride(testRoute);
	private final RideDao rideDao = mock(RideDao.class);
	private final RouteDao routeDao = mock(RouteDao.class);
	private final RideService service = new RideServiceImpl(rideDao, routeDao);

	@Test
	public void createRideUsesSuppliedRoute(){

		when(rideDao.save(argThat(isRideWithTestRoute()))).thenReturn(r2);
		Ride newRide = service.createRide(testRoute);
		assertEquals(r2, newRide);
		verify(rideDao).save(argThat(isRideWithTestRoute()));
	}
	
	@Test
	public void createRouteUsesSuppliedName(){
		Route r = new Route("test");
		when(routeDao.save(argThat(isTestRoute()))).thenReturn(r);
		Route newRoute = service.createRoute("test");
		assertEquals(r, newRoute);
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
	
	private IsRideWithTestRoute isRideWithTestRoute() {
		return new IsRideWithTestRoute();
	}
	private IsTestRoute isTestRoute(){
		return new IsTestRoute();
	}

	class IsTestRoute extends ArgumentMatcher<Route>{
		@Override
		public boolean matches(Object argument) {
			return ((Route)argument).getName().equals("test");
		}
		
	}
	class IsRideWithTestRoute extends ArgumentMatcher<Ride>{
		@Override
		public boolean matches(Object argument) {
			return ((Ride) argument).getRoute().getName().equals("test");	
		}
		
	}
}
