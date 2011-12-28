package uk.chrismay.springtest.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import uk.chrismay.springtest.domain.Route;
import uk.chrismay.springtest.service.RideService;

@Controller
@RequestMapping("/route")
public class RoutesController {

	private final RideService rideService;

	@Autowired
	public RoutesController(RideService rs) {
		this.rideService = rs;

	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newRouteForm() {
		return new ModelAndView("new_route", "route", new Route());
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public ModelAndView submitRouteForm(@Valid Route r, BindingResult errors) {
		long routeId = rideService.createRoute(r.getName());
		if (routeId != RideService.NON_EXISTENT_ENTITY_ID) {
			Route created = rideService.getRoute(routeId);

			return new ModelAndView("route_created", "route", created);
		} else {
			ModelAndView mav = new ModelAndView("new_route", "route", r);
			errors.addError(new FieldError("route", "name", "Route with name "
					+ r.getName() + " already exists"));
			return mav;
		}

	}

	@RequestMapping(value="/list")
	public ModelAndView listRoutes() {
		return new ModelAndView("list_routes","routes",rideService.getAllRoutes());
	}

}
