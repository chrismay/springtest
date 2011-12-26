package uk.chrismay.springtest.dsl;

import java.io.IOException;

import org.xml.sax.SAXException;

import junit.framework.Assert;
import uk.chrismay.springtest.dsl.http.WebConversationWrapper;

import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebResponse;

public class RoutesPage {
	
	private WebConversationWrapper conversation;
	private WebResponse thePage;

	public RoutesPage(WebConversationWrapper conversation, WebResponse thePage){
		this.conversation = conversation;
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
			WebResponse resp = createForm.submit();
			Assert.assertNotNull(resp);
			
		} catch (SAXException e) {
			throw new RuntimeException();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

}
