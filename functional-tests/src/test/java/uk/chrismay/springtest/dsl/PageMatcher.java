package uk.chrismay.springtest.dsl;

import java.io.IOException;

import junit.framework.Assert;

import com.meterware.httpunit.WebResponse;

public class PageMatcher {

	private final WebResponse page;
	public PageMatcher(WebResponse page){
		this.page = page;
		
	}
	
	public void verifyMainPage(){
		mainPage();
	}
	public void mainPage(){
		Assert.assertTrue(pageText().contains("Welcome"));
	}
	
	public PageMatcher is(){
		return this;
	}

	public PageMatcher shouldBe(){
		return this;
	}

	private String pageText(){
		try {
			return page.getText();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void routesPage() {
		Assert.assertTrue(pageText().contains("Add Route"));
	}

	public void routeCreatedPage() {
		Assert.assertTrue(pageText().contains("Well done"));
		
	}
}
