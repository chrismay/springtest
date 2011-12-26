package uk.chrismay.springtest.web.controller;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

public class MainPageControllerTest {

	@Test
	public void testViewName(){
		MainPageController mpc = new MainPageController();
		ModelAndView mav = mpc.get();
		Assert.assertEquals("home",mav.getViewName());
	}
}
