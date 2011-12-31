package uk.chrismay.springtest.dsl;

import junit.framework.Assert;

public class JsonAPIResponse {

	private final String json;

	public JsonAPIResponse(String text) {
		this.json = text;
	}

	public void shouldContainRouteNamed(String routeName) {
		String search = String.format("\"name\":\"%s\"", routeName );
		String message = String.format("Couldn't find %s in %s", routeName,json);
		Assert.assertTrue(message,json.contains(search));
		
	}

}
