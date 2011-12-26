package uk.chrismay.springtest.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/status")
public class StatusController {

	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView get(){
		ModelAndView model = new ModelAndView("status");
		model.addObject("status","ok");
		return model;
	}
}
