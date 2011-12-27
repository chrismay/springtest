package uk.chrismay.springtest.dsl;

import junit.framework.Assert;

import org.xml.sax.SAXException;

import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebResponse;

public class RoutesPage {
	
	private Session session;
	private WebResponse thePage;

	public RoutesPage(Session session, WebResponse thePage){
		this.session= session;
		this.thePage = thePage;		
	}

	public RoutesPage() {
	
	}
	
	public RoutesPage should(){
		return this;
	}
	
	public void beLoaded(){
		Assert.assertNotNull(thePage);
	}

	public void createRoute(String name) {
		try {
			WebForm createForm = thePage.getFormWithID("createroute");
			createForm.setParameter("routename", name);
			WebResponse resp = session.submitForm(createForm);
			Assert.assertNotNull(resp);
			
		} catch (SAXException e) {
			throw new RuntimeException();
		}
	}

}
