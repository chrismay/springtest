package uk.chrismay.springtest;

import static uk.chrismay.springtest.dsl.World.*;

import org.junit.Test;

public class RideApiTest {

	@Test
	public void canCreateARide() {
		Given().theApplication().isRunning();
		And().theDatabase().isEmptied();
		When().theUser().createsRoute("test route");
		And().TheAPI().createsRide("test ride","test route");
	}
}
