package uk.chrismay.springtest.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/route")
public class RoutesController {

	@RequestMapping(value="/new", method=RequestMethod.GET)
	public ModelAndView newRouteForm(){
		return new ModelAndView("new_route");
	}
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public ModelAndView submitRouteForm(){
		throw new RuntimeException();
	}

}
