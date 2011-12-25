package uk.chrismay.springtest.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Date;

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
	
}
