package uk.chrismay.springtest;

import org.junit.Test;

import static uk.chrismay.springtest.dsl.World.*;

public class RoutesTest {

	@Test
	public void canCreateRoute(){
		Given().theApplication().isRunning();
		And().theDatabase().isEmptied();
		When().theUser().loadsCreateNewRoutePage();
		And().theUser().createsRoute("test route");
		Then().theCurrentPage().shouldBe().routeCreatedPage();
	}
	
	@Test
	public void cantCreateRoutesWithDuplicateNames(){
		Given().theApplication().isRunning();
		And().theDatabase().isEmptied();
		When().theUser().loadsCreateNewRoutePage();
		And().theUser().createsRoute("test route");
		And().theUser().loadsCreateNewRoutePage();
		And().theUser().createsRoute("test route");
		Then().theCurrentPage().shouldBe().routesPage();
		And().theCurrentPage().should().containText("Route with name test route already exists");		
	}
	
	@Test
	public void listsAllRoutes(){
		Given().theApplication().isRunning();
		And().theDatabase().isEmptied();
		When().theUser().loadsCreateNewRoutePage();
		And().theUser().createsRoute("test route");
		And().theUser().loadsShowRoutesPage();
		Then().theCurrentPage().hasRoutesList();
		And().theRoutesList().includes("test route");
	}
}
