package uk.chrismay.springtest.dsl;

import java.io.IOException;
import java.util.LinkedList;

import org.xml.sax.SAXException;

import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebForm;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebResponse;

public class Session {

	private static Session instance = new Session();
	private WebConversation conversation = new WebConversation();
	private LinkedList<WebResponse> history = new LinkedList<WebResponse>();

	public static Session current() {
		return instance;
	}

	public static Session clear() {
		instance = new Session();
		return instance;
	}

	public WebResponse getResponse(String url) {
		try {
			WebResponse resp = conversation.getResponse(url);
			addHistory(resp);
			return resp;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		}
	}

	private void addHistory(WebResponse resp) {
		System.out.println("**Added " + resp.getURL()  +" to history");
		history.add(resp);
	}

	public WebResponse submitForm(WebForm form) {
		WebResponse resp;
		try {
			resp = form.submit();
			addHistory(resp);
			return resp;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		}
	}

	public WebResponse click(WebLink link) {
		WebResponse resp;
		try {
			resp = link.click();
			addHistory(resp);
			return resp;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		}
	}
	
	public PageMatcher currentPage(){
		return new PageMatcher(history.getLast());
	}
	
	public MainPage MainPage(){
		return new MainPage(this,history.getLast());
	}

}
