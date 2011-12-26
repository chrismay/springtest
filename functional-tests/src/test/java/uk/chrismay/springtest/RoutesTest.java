package uk.chrismay.springtest;

import org.junit.Test;

import static uk.chrismay.springtest.dsl.World.*;

public class RoutesTest {

	@Test
	public void canCreateRoute(){
		Given().theApplication().isRunning();
		When().theUser().loadsRoutesPage();
		And().theUser().createsRoute("test route");
	}
}
