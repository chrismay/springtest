package uk.chrismay.springtest.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

import uk.chrismay.springtest.domain.Ride;
import uk.chrismay.springtest.domain.Route;

public class RideTest {

	@Test
	public void constructWithRouteSetsRouteAndDefaultDate(){
		Route r = new Route("test");
		Date beforeConstructor = new Date();
		Ride ride = new Ride(r);
		Date afterConstructor = new Date();
		
		assertEquals(r, ride.getRoute());
		assertFalse(beforeConstructor.after(ride.getDate()));
		assertFalse(afterConstructor.before(ride.getDate()));		
	}
	
	@Test
	public void canSetDate(){
		Route r = new Route("test");
		Ride ride = new Ride(r);
		Calendar cal = Calendar.getInstance();
		cal.set(1999,12,31);
		Date longAgo = cal.getTime(); 		
		ride.setDate(longAgo);
		Assert.assertEquals(longAgo, ride.getDate());
	}
	
	@Test
	public void canSetRoute(){
		Route r = new Route("test");
		Ride ride = new Ride(r);
		Route r2 = new Route("new route");
		ride.setRoute(r2);
		Assert.assertEquals(r2, ride.getRoute());
	}
	
	
}
