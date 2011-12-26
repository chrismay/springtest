package uk.chrismay.springtest.web.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

public class RoutesControllerTest {

	@Test
	public void testNewRouteForm() {
		RoutesController controller = new RoutesController();
		ModelAndView mav = controller.newRouteForm();
		assertEquals("new_route", mav.getViewName());	
	}

	@Test(expected=RuntimeException.class)
	public void testSubmitNewRouteForm() {
		RoutesController controller = new RoutesController();
		ModelAndView mav = controller.submitRouteForm();
		assertEquals("new_route", mav.getViewName());	
	}

}
