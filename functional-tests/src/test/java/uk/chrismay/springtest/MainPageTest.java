package uk.chrismay.springtest;

import static uk.chrismay.springtest.dsl.World.*;

import org.junit.Test;

public class MainPageTest {

	@Test
	public void canViewMainPage(){
		Given().theApplication().isRunning();
		Then().theApplication().mainPage().shouldLoad();		
	}
	
	@Test
	public void mainPageHasLinkToCreateRoutes(){
		Given().theApplication().isRunning();
		Then().theApplication().mainPage().shouldContainLink().WithText("Create Route");
	}
}
