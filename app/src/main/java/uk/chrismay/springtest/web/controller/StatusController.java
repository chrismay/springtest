package uk.chrismay.springtest.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/status")
public class StatusController {

	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody public String get(){
		return "ok";
	}
}
