package uk.chrismay.springtest.web.controller;

import static junit.framework.Assert.*;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

public class StatusControllerTest {

	@Test
	public void shouldReturnOK(){
		StatusController controller = new StatusController();
		ModelAndView model = controller.get();
		assertEquals("status", model.getViewName());
		String status = (String) model.getModelMap().get("status");
		assertNotNull(status);
		assertEquals("ok", status);
	}
}
