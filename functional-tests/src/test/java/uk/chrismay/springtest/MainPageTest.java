package uk.chrismay.springtest;

import static uk.chrismay.springtest.dsl.World.*;

import org.junit.Test;

public class MainPageTest {

	@Test
	public void canViewMainPage(){
		Given().theApplication().isRunning();
		When().theApplication().mainPage().shouldLoad();
		
	}
}
