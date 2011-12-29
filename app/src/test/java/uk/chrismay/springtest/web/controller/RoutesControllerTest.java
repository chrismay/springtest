package uk.chrismay.springtest.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import uk.chrismay.springtest.domain.Route;
import uk.chrismay.springtest.service.RideService;

import com.google.common.collect.ImmutableList;

public class RoutesControllerTest {
	;

	private RideService rs = mock(RideService.class);

	@Test
	public void testListRoutes() {
		when(rs.getAllRoutes()).thenReturn(
				ImmutableList.of(new Route("test1"), new Route("test2")));
		RoutesController controller = new RoutesController(rs);
		ModelAndView mav = controller.listRoutes();
		assertEquals("list_routes", mav.getViewName());
		assertNotNull(mav.getModelMap().get("routes"));
		@SuppressWarnings("unchecked")
		Collection<Route> routes = (Collection<Route>) mav.getModelMap().get(
				"routes");
		assertEquals(2, routes.size());

	}

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
		route.setId(99);

		when(rs.createRoute(testRouteName)).thenReturn(route.getId());
		when(rs.getRoute(route.getId())).thenReturn(route);
		RoutesController controller = new RoutesController(rs);

		RedirectAttributes flashMap = new RedirectAttributesModelMap();
		String view = controller.submitRouteForm(route, new BindException(
				route, "route"), flashMap);

		assertEquals("redirect:/route/list.htm", view);
		assertEquals("Created new route with name 'test route'", flashMap
				.getFlashAttributes().get("message"));
	}

	@Test
	public void testSubmitDuplicateNamedRoute() {
		final String testRouteName = "test route";
		Route route = new Route(testRouteName);

		when(rs.createRoute(testRouteName)).thenReturn(
				RideService.NON_EXISTENT_ENTITY_ID);
		RoutesController controller = new RoutesController(rs);
		BindingResult errors = new BindException(route, "route");
		String view = controller.submitRouteForm(route, errors,
				new RedirectAttributesModelMap());
		assertEquals("new_route", view);
		assertEquals(1, errors.getAllErrors().size());
		assertEquals("Route with name test route already exists", errors
				.getFieldError("name").getDefaultMessage());
	}

	@Test
	public void testSubmitWithBindingErrors() {

		RoutesController controller = new RoutesController(rs);
		Route route = new Route("");
		BindingResult errors = new BindException(route, "route");
		errors.rejectValue("name", "someErrorCode",  "some message");
		String view = controller.submitRouteForm(route, errors, null);
		assertEquals("new_route", view);

	}

}
