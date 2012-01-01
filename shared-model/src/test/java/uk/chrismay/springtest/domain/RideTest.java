package uk.chrismay.springtest.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

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
	
	
	@Test
	public void canGetAndSetId(){
		Ride r = new Ride(null);
		r.setId(12);
		assertEquals(12, r.getId());
	}

	@Test
	public void canGetAndSetComments(){
		Ride r = new Ride(null);
		r.setComments("Some comments");
		assertEquals("Some comments", r.getComments());
	}
	
	@Test
	public void acceptUpdateMergesFields(){
		Ride original = new Ride(null);
		original.setId(2);
		
		Ride newData = new Ride(new Route("test route"));
		newData.setComments("new comments");
		newData.setDate(new Date(0));
		newData.setId(9);
		
		original.updateFrom(newData);
		assertEquals(newData.getComments(), original.getComments());
		assertEquals(newData.getDate(), original.getDate());
		assertEquals(newData.getRoute(), original.getRoute());
		// ID is never changed 
		assertEquals(2, original.getId());
	}
	
	@Test
	public void hasNoArgConstructor(){
		Ride r = new Ride();
		assertNotNull(r);
		assertNull(r.getDate());
		assertNull(r.getRoute());
		assertNull(r.getComments());
	}
	
	
}
