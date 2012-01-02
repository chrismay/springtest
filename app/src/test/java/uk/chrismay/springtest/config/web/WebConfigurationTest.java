package uk.chrismay.springtest.config.web;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.web.servlet.ViewResolver;

public class WebConfigurationTest {

	@Test
	public void canGetViewResolver(){
		WebConfiguration config = new WebConfiguration();
		ViewResolver vr = config.viewResolver();
		assertNotNull(vr);
	}
}
