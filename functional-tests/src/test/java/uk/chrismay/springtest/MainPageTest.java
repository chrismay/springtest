package uk.chrismay.springtest;

import static uk.chrismay.springtest.dsl.World.*;

import org.junit.Test;

public class MainPageTest {

	private static final String CREATE_ROUTE_LINK = "Create Route";

	@Test
	public void canViewMainPage(){
		Given().theApplication().isRunning();
		Then().theApplication().mainPage().should().load();		
	}
	
	@Test
	public void mainPageHasLinkToCreateRoutes(){
		Given().theApplication().isRunning();
		Then().theApplication().mainPage().should().containLink().WithText(CREATE_ROUTE_LINK);
	}
	
	@Test
	public void canLoadCreateRoutesPageFromMainPage(){
		Given().theApplication().isRunning();
		When().theUser().viewsMainPage().and().clicksCreateRoutesLink();
		Then().theRoutesPage().should().beLoaded();
	}

	
}
