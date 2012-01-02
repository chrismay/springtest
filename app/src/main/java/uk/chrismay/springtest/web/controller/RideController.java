package uk.chrismay.springtest.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import uk.chrismay.springtest.domain.Ride;
import uk.chrismay.springtest.service.RideService;

@Controller
@RequestMapping("/ride/*")
public class RideController {

	private RideService rideService;

	@Autowired
	public RideController(RideService rideService) {
		this.rideService = rideService;
	}

	@RequestMapping(value = "new", method = RequestMethod.POST)
	@ResponseBody
	public Ride create(@RequestBody Ride createMe) {
		long rideId = rideService.createRide(createMe);
		return rideService.getRide(rideId);
	}
	
	@RequestMapping(value="/list",  produces="application/json")
	@ResponseBody public  Iterable<Ride> getAllRides(){
		return rideService.getAllRides();
	}

	@RequestMapping(value="/list",  produces="text/html")
	public  void list(){
	}

}
