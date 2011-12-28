package uk.chrismay.springtest.dsl;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.common.base.Joiner;
import com.meterware.httpunit.HTMLElement;
import com.meterware.httpunit.WebResponse;

import junit.framework.Assert;

public class RoutesList {

	private WebResponse routeListPage;
	private Session session;

	public RoutesList(Session session, WebResponse currentPage) {
		this.routeListPage = currentPage;
		this.session = session;
		new PageMatcher(currentPage).verifyMainPage();	}

	public void includes(String routeName) {
	    try {
			Document dom = routeListPage.getDOM();
			NodeList routeList = dom.getElementById("routeList").getChildNodes();
			List<String> routes = new ArrayList<String>();
			for (int i = 0; i < routeList.getLength(); i++) {
				Node route = routeList.item(i);
				routes.add(route.getNodeValue());
			}
			String message = String.format("Couldn't find '%s' in %s",routeName, Joiner.on("\n").join(routes));
			Assert.assertTrue(message, routes.contains(routeName));
	    } catch (SAXException e) {
			throw new RuntimeException(e);
		}
		
	}

}
