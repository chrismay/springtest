package uk.chrismay.springtest.dsl;

import java.io.IOException;
import java.net.MalformedURLException;

import org.xml.sax.SAXException;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;

public class RoutesListAPI {

	public JsonAPIResponse response() {
		WebConversation conv = new WebConversation();
		WebRequest req = new GetMethodWebRequest(App.APP_BASE_URL + "/route/list.json");
		req.setHeaderField("Accept", "application/json");
		try {
			WebResponse resp = conv.getResponse(req);
			return new JsonAPIResponse(resp.getText());
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		}
	}

}
