package uk.chrismay.springtest.dsl;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

import org.xml.sax.SAXException;

import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;

public class MainPage {

	private final Session session;
	private WebResponse mainPage;
	
	public MainPage(Session session){
		this.session = session;
	    mainPage = session.getResponse(App.HOME_URL);
	}
	
	public MainPage(Session session, WebResponse currentPage) {
		this.mainPage = currentPage;
		this.session = session;
		new PageMatcher(currentPage).verifyMainPage();
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
		return new RoutesPage(session, clicksLink("Create Route"));
	}
	public WebResponse clicksLink(String linkText) {
		LinkCollection links = getLinkCollection();
		return links.clickLink(linkText, session);
	}
	public MainPage should() {
		return this;
	}

	public RoutesList clicksShowRoutesLink() {
		return new RoutesList(session, clicksLink("Show Routes"));
	}
}
