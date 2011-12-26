package uk.chrismay.springtest.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class MainPageController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView get(){
		ModelAndView mav = new ModelAndView("home");
		return mav;
	}
}
