package uk.chrismay.springtest.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView newRouteForm(){
		return new ModelAndView("new_route");
	}
	
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public ModelAndView submitRouteForm(@Valid Route r){
		Route created = rideService.createRoute(r.getName());
		return new ModelAndView("route_created","route",created);
	}

}
