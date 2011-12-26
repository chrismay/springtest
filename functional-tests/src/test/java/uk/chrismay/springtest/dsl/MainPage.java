package uk.chrismay.springtest.dsl;

import static junit.framework.Assert.*;

import org.xml.sax.SAXException;

import uk.chrismay.springtest.dsl.http.WebConversationWrapper;

import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;

public class MainPage {

	private final WebConversationWrapper conversation;
	private WebResponse mainPage;
	public MainPage(WebConversationWrapper conv){
		conversation = conv;
	    mainPage = conversation.getResponse(App.HOME_URL);

	}
	public void shouldLoad() {
	    assertNotNull(mainPage);
		
	}
	public LinkCollection shouldContainLink() {
		try {
			return new LinkCollection(mainPage.getLinks());
		} catch (SAXException e) {
			fail("Unable to parse links from main page");
			return new LinkCollection(new WebLink[]{});
		}
	}
}
