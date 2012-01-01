package uk.chrismay.springtest.dsl;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Collection;

import junit.framework.Assert;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import uk.chrismay.springtest.domain.Ride;
import uk.chrismay.springtest.domain.Route;

import com.meterware.httpunit.WebResponse;

public class JsonAPIResponse {

	private final String json;

	public JsonAPIResponse(WebResponse resp) {
		try {
			this.json = resp.getText();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void shouldContainRouteNamed(String routeName) {
		
			Collection<Route> routes = getRoutesList();
			boolean matched = false;
			for (Route route : routes) {
				if (route.getName().equals(routeName)){
					matched = true;
				}
			}
			assertTrue("Couldn't find a route called " + routeName,matched);
	}
	
	public Collection<Route> getRoutesList(){
		ObjectMapper mapper = new ObjectMapper();
		try {
			 return mapper.readValue(json, new TypeReference<Collection<Route>>(){});
		} catch (JsonParseException e) {
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}		
		
	}

	
	public Collection<Ride> getRideList(){
		ObjectMapper mapper = new ObjectMapper();
		try {
			 return mapper.readValue(json, new TypeReference<Collection<Ride>>(){});
		} catch (JsonParseException e) {
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}		
		
	}

	public void shouldBeRideWithComments(String comments) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			 Ride r = mapper.readValue(json, Ride.class);
			 Assert.assertEquals(comments, r.getComments());
		} catch (JsonParseException e) {
			throw new RuntimeException(e);
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}		
		
	}

	public void shouldContainRide(String comments) {
		Collection<Ride> rides = getRideList();
		boolean matched = false;
		for (Ride ride : rides) {
			if (ride.getComments().equals(comments)) matched = true;
		}
		assertTrue("couldn't find a ride with comments " + comments, matched);
		
	}

}
