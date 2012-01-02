package uk.chrismay.springtest.web.controller;

import java.util.Collection;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

import static junit.framework.Assert.*;
import uk.chrismay.springtest.domain.Ride;
import uk.chrismay.springtest.domain.Route;
import uk.chrismay.springtest.service.RideService;
import static org.mockito.Mockito.*;

public class RideControllerTest {

	@Test
	public void createRideCallsService(){
		Ride createMe = new Ride(new Route("test route"));
		Ride created = new Ride(new Route("foo"));
		created.setId(1);
		RideService rideService = mock(RideService.class);
		when(rideService.createRide(createMe)).thenReturn(created.getId());
		when(rideService.getRide(created.getId())).thenReturn(created);
		RideController rc = new RideController(rideService);
		Ride fromController = rc.create(createMe);
		assertEquals(created,fromController);
	}
	
	@Test
	public void getRidesGetsRides(){
		Ride r = new Ride(new Route("foo"));
		RideService rideService = mock(RideService.class);
		when(rideService.getAllRides()).thenReturn(ImmutableList.of(r));
		RideController rc = new RideController(rideService);
		Collection<Ride> rides = rc.getAllRides();
		assertEquals(1, rides.size());
		assertEquals(r, rides.iterator().next());
	}
	
	@Test
	public void testListDoesNothing(){
		RideController rc= new RideController(null);
		rc.list();
	}
}
