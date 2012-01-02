package uk.chrismay.springtest.config.root;

import static org.junit.Assert.assertNotNull;

import javax.validation.ValidatorFactory;

import org.junit.Test;

public class ApplicationConfigurationTest {
	@Test
	public void testGetValidator(){
		ValidatorFactory vf = new ApplicationConfiguration().validator();
		assertNotNull(vf);		
	}
}
