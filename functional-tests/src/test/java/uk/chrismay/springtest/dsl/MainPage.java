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
	
	public void load() {
	    assertNotNull(mainPage);
		
	}
	private LinkCollection getLinkCollection(){
		try {
			return new LinkCollection(mainPage.getLinks());
		} catch (SAXException e) {
			fail("Unable to parse links from main page");
			return new LinkCollection(new WebLink[]{});
		}
		
	}
	public LinkCollection containLink() {
		return getLinkCollection();
	}
	
	public MainPage and() {
		return this;
	}
	public RoutesPage clicksCreateRoutesLink(){
		return World.loadRoutesPage(conversation, clicksLink("Create Route"));
	}
	public WebResponse clicksLink(String linkText) {
		LinkCollection links = getLinkCollection();
		return links.clickLink(linkText, conversation);
	}
	public MainPage should() {
		return this;
	}
}
