package uk.chrismay.springtest.dsl;

import org.apache.log4j.Logger;

import junit.framework.Assert;

public class Waiter {

	private static final Logger LOG = Logger.getLogger(Waiter.class);
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
			LOG.info("Waiting for condition: " + message);
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
