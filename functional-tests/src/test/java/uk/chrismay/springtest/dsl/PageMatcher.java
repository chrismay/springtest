package uk.chrismay.springtest.dsl;

import java.io.IOException;

import junit.framework.Assert;

import com.meterware.httpunit.WebResponse;

public class PageMatcher {

	private final WebResponse page;

	public PageMatcher(WebResponse page) {
		this.page = page;

	}

	public void verifyMainPage() {
		mainPage();
	}

	public void mainPage() {
		Assert.assertTrue(pageText().contains("Welcome"));
	}

	public PageMatcher is() {
		return this;
	}

	public PageMatcher shouldBe() {
		return this;
	}

	private String pageText() {
		try {
			return page.getText();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void createRouteForm() {
		Assert.assertTrue("Page doesn't look like the 'Add Route' page:\n" + pageText(),
				pageText().contains("Add Route"));
	}

	public void routeCreatedPage() {
		hasRoutesList();
		Assert.assertTrue("Page doesn't look like the 'Route added' page:\n" + pageText(),
				pageText().contains("Created new route"));

	}

	public PageMatcher should() {
		return this;
	}

	public void containText(String text) {
			Assert.assertTrue("Couldn't find '" + text + "' in the text:\n"+ pageText(), pageText().contains(text));
	}

	public void hasRoutesList() {
		Assert.assertTrue("Page doesn't seem to contain the Routes List\n:" + pageText(), pageText().contains("ul id='routeList'"));
	}
	
	public void routesListPage() {
		hasRoutesList();
	}
	

	public String getUrl() {
		return page.getURL().toExternalForm();
	}

	public void verifyRoutesList() {
		hasRoutesList();
	}
}
