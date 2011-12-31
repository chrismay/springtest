package uk.chrismay.springtest.web.controller;

import org.junit.Test;

public class MainPageControllerTest {

    //All of the Main Page controller behaviour is configured by annotations
	@Test
	public void testGetDoesNothing(){
		MainPageController mpc = new MainPageController();
		mpc.get();
	}
}
