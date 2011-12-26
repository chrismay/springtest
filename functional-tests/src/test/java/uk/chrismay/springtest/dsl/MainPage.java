package uk.chrismay.springtest.dsl;

import static junit.framework.Assert.*;
import uk.chrismay.springtest.dsl.http.WebConversationWrapper;

import com.meterware.httpunit.WebResponse;

public class MainPage {

	private static final String MAIN_PAGE_URL="http://localhost:8123/springtest/home.htm";
	private final WebConversationWrapper conversation;
	private WebResponse mainPage;
	public MainPage(WebConversationWrapper conv){
		conversation = conv;
	}
	public void shouldLoad() {
	    mainPage = conversation.getResponseOrNull(MAIN_PAGE_URL);
	    assertNotNull(mainPage);
		
	}
}
