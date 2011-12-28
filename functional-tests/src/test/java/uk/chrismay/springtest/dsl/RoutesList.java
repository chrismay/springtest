package uk.chrismay.springtest.dsl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLLIElement;
import org.xml.sax.SAXException;

import com.google.common.base.Joiner;
import com.meterware.httpunit.WebResponse;

public class RoutesList {

	private WebResponse routeListPage;
	@SuppressWarnings("unused")
	private Session session;

	public RoutesList(Session session, WebResponse currentPage) {
		this.routeListPage = currentPage;
		this.session = session;
		new PageMatcher(currentPage).verifyRoutesList();	}

	public void includes(String routeName) {
	    try {
			Document dom = routeListPage.getDOM();
			Node routeListElement = dom.getElementById("routeList");
			Node child = routeListElement.getFirstChild();
			List<String> routes = new ArrayList<String>();
			while (child != null){
				if (child instanceof HTMLLIElement){
					routes.add(((HTMLLIElement)child).getFirstChild().getNodeValue());					
				}
				child = child.getNextSibling();
			}
			String message = String.format("Couldn't find '%s' in %s",routeName, Joiner.on("\n").join(routes));
			Assert.assertTrue(message, routes.contains(routeName));
	    } catch (SAXException e) {
			throw new RuntimeException(e);
		}
		
	}

}
