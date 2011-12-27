package uk.chrismay.springtest.web.controller;


import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import uk.chrismay.springtest.domain.Route;
import uk.chrismay.springtest.service.RideService;
import static org.mockito.Mockito.*;

public class RoutesControllerTest {;

	private RideService rs = mock(RideService.class);

	@Test
	public void testNewRouteForm() {
		RoutesController controller = new RoutesController(rs);
		ModelAndView mav = controller.newRouteForm();
		assertEquals("new_route", mav.getViewName());
		assertNotNull(mav.getModelMap().get("route"));
		assertTrue(mav.getModelMap().get("route") instanceof Route);
	}

	@Test
	public void testSubmitNewRouteForm() {
		final String testRouteName = "test route";
		Route route = new Route(testRouteName);
		
		when(rs.createRoute(testRouteName)).thenReturn(route);
		
		RoutesController controller = new RoutesController(rs);

		ModelAndView mav = controller.submitRouteForm(route);
		
		assertEquals("route_created", mav.getViewName());	
		assertNotNull(mav.getModelMap().get("route"));
		assertEquals(testRouteName, ((Route)mav.getModelMap().get("route")).getName());
	}

}
