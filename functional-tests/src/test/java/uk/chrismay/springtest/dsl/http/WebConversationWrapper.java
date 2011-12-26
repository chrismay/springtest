package uk.chrismay.springtest.dsl.http;

import java.io.IOException;

import org.xml.sax.SAXException;

import com.meterware.httpunit.WebConversation;
import com.meterware.httpunit.WebResponse;

public class WebConversationWrapper {
	private final WebConversation _conversation;
	public WebConversationWrapper(WebConversation conversation){
		_conversation = conversation;
	}
	
	public WebResponse getResponse(String url){
		try {
			return _conversation.getResponse(url);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		}
	}

	public WebResponse getResponseOrNull(String url) {
		try{
			return getResponse(url);
		}catch (RuntimeException e){
			return null;
		}
	}
}
