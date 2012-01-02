package uk.chrismay.springtest.config.root;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class ContainerResourceConfigurationTest {

	@Test
	public void canConstructContainerResourceConfiguration(){
		assertNotNull(new ContainerResourceConfiguration());
	}
}
