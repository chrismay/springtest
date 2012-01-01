package uk.chrismay.springtest.web.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import uk.chrismay.springtest.domain.Route;
import uk.chrismay.springtest.service.RideService;

@Controller
@RequestMapping("/route")
public class RoutesController {

	private final RideService rideService;
	public static final String ROUTE_KEY = "route";

	@Autowired
	public RoutesController(RideService rs) {
		this.rideService = rs;
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public void newRouteForm(Model model) {
		model.addAttribute(ROUTE_KEY, new Route());
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String submitRouteForm(@Valid Route r, BindingResult errors, RedirectAttributes flashMap) {
		if (errors.hasErrors()) {
			return null;
		} else {
			long routeId = rideService.createRoute(r.getName());
			if (routeId != RideService.NON_EXISTENT_ENTITY_ID) {
				Route created = rideService.getRoute(routeId);
				flashMap.addFlashAttribute("message",
						String.format("Created new route with name '%s'", created.getName()));
				return "redirect:/route/list.htm";
			} else {
				errors.rejectValue("name", "route.name.duplicate",
						String.format("Route with name %s already exists", r.getName()));
				return null;
			}
		}
	}

	/**
	 * HTML request handler
	 */
	@RequestMapping(value = "/list",  produces="text/html")
	public void listRoutes(Model model) {
		model.addAttribute("routes", getAllRoutes());
	}
	
	/**
	 * JSON Request handler
	 */
	@RequestMapping(value="/list",  produces="application/json")
	@ResponseBody public  Collection<Route> getAllRoutes(){
		return rideService.getAllRoutes();
	}

}
