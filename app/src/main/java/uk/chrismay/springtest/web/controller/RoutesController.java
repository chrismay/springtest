package uk.chrismay.springtest.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String submitRouteForm(@Valid Route r, Model model, BindingResult errors,RedirectAttributes flashMap) {
		long routeId = rideService.createRoute(r.getName());
		if (routeId != RideService.NON_EXISTENT_ENTITY_ID) {
			Route created = rideService.getRoute(routeId);
			flashMap.addFlashAttribute("message",String.format("Created new route with name '%s'",created.getName()));
			return "redirect:/route/list.htm";
		} else {
			model.addAttribute("route", r);
			errors.addError(new FieldError("route", "name", "Route with name "
					+ r.getName() + " already exists"));
			return "new_route";
		}

	}

	@RequestMapping(value="/list")
	public ModelAndView listRoutes() {
		return new ModelAndView("list_routes","routes",rideService.getAllRoutes());
	}

}
