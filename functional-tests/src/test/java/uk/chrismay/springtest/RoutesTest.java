package uk.chrismay.springtest;

import org.junit.Test;

import static uk.chrismay.springtest.dsl.World.*;

public class RoutesTest {

	@Test
	public void canCreateRoute(){
		Given().theApplication().isRunning();
		When().theUser().loadsRoutesPage();
		And().theUser().createsRoute("test route");
		Then().theCurrentPage().shouldBe().routeCreatedPage();
	}
	
	@Test
	public void cantCreateRoutesWithDuplicateNames(){
		Given().theApplication().isRunning();
		And().theDatabase().isEmptied();
		When().theUser().loadsRoutesPage();
		And().theUser().createsRoute("test route");
		And().theUser().loadsRoutesPage();
		Then().theCurrentPage().shouldBe().routesPage();
	}
}
