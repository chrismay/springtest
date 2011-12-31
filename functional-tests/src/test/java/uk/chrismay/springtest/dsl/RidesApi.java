package uk.chrismay.springtest.dsl;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collection;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.meterware.httpunit.PostMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

import uk.chrismay.springtest.domain.Ride;
import uk.chrismay.springtest.domain.Route;
import uk.chrismay.springtest.dsl.http.WebConversationWrapper;

public class RidesApi {

	public void create(String rideName, final String routeName) {
		Collection<Route> routes = new RoutesListAPI().getAllRoutes();
		Route r = Iterables.find(routes, new Predicate<Route>() {
			public boolean apply(Route input) {
				return input.getName().equals(routeName);
			}
		});
		
		Ride ride = new Ride(r);
		ride.setComments("Test ride");
		try {
			String payload = new ObjectMapper().writeValueAsString(ride);
			ByteArrayInputStream bais = new ByteArrayInputStream(payload.getBytes("UTF-8"));
			WebConversationWrapper conv = new WebConversationWrapper(new WebConversation());
			PostMethodWebRequest req = new PostMethodWebRequest(App.APP_BASE_URL + "/ride/new.json", bais,"application/json");
			WebResponse resp = conv.getResponse(req);
			assertEquals(200, resp.getResponseCode());
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
