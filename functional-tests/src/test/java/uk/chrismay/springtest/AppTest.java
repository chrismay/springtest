package uk.chrismay.springtest;

import org.junit.Test;

import static uk.chrismay.springtest.dsl.World.Given;

public class AppTest 
{
	@Test
	public void smoketest(){
		Given().theApplication().isRunning();
		
	}
}
