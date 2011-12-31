package uk.chrismay.springtest.dsl;

import java.util.Collection;

import uk.chrismay.springtest.domain.Route;
import uk.chrismay.springtest.dsl.http.WebConversationWrapper;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class RoutesListAPI {

	public JsonAPIResponse response() {
		WebConversationWrapper conv = new WebConversationWrapper(new WebConversation());
		WebRequest req = new GetMethodWebRequest(App.APP_BASE_URL + "/route/list.json");
		req.setHeaderField("Accept", "application/json");
		WebResponse resp = conv.getResponse(req);
		return new JsonAPIResponse(resp);
	}

	Collection<Route> getAllRoutes() {
		return response().getRoutesList();
	}

}
