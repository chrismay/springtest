package uk.chrismay.springtest.web.controller;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;

public class StatusControllerTest {

	@Test
	public void shouldReturnOK(){
		StatusController controller = new StatusController();
		String status = controller.get();
		assertEquals("ok", status);
	}
}
