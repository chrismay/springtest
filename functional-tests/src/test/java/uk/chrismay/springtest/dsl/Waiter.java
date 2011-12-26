package uk.chrismay.springtest.dsl;

import junit.framework.Assert;

public class Waiter {

	
	static interface Exec {
		public boolean run();
	}

	public static void waitForCondition(Exec exec, String message){
		waitForCondition(exec, message, 5000);
	}
	public static void waitForCondition(Exec exec, String message, int timeout) {
		boolean pass = exec.run();
		long endTime = System.currentTimeMillis() + timeout;
		while ((pass == false) && (System.currentTimeMillis() < endTime)) {
			System.out.println("Waiting for condition: " + message);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			pass = exec.run();
		}
		Assert.assertTrue("Timeout waiting for '" + message + "' to complete", pass);

	}
}
