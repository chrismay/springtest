package uk.chrismay.springtest.dsl;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.meterware.httpunit.WebResponse;

public class App {

	private static final Logger LOG = Logger.getLogger(App.class);
	private static final String SERVLET_PORT=System.getProperty("servlet.port","8080");
	private static final String SERVER_URL = "http://localhost:" + SERVLET_PORT + "/";
	private static final String CONTEXT="springtest";
	private static final String APP_BASE_URL = SERVER_URL + CONTEXT;
	public static final String STATUS_URL = APP_BASE_URL + "/status.htm";
	public static final String HOME_URL = APP_BASE_URL + "/home.htm";
	private final Session session;
	
	
	public App() {
		LOG.info(String.format("Creating app with URL base %s ", APP_BASE_URL));
		session = Session.current();
	}

	public App isRunning() {
		Waiter.Exec ping = new Waiter.Exec() {

			@Override
			public boolean run() {
				WebResponse res = session
						.getResponse(STATUS_URL);
				try {
					return (res.getText().startsWith("\nok"));
				} catch (IOException e) {
					throw new RuntimeException(e);
				}

			}
		};
		Waiter.waitForCondition(ping, "Fetch status page");
		return this;
	}

	private MainPage loadMainPage(){
			return new MainPage(session);
	}
	public MainPage mainPage() {
		return loadMainPage();
	}

	public MainPage viewsMainPage() {
		return new MainPage(session);
	}

	public RoutesList loadsShowRoutesPage(){
		return loadMainPage().clicksShowRoutesLink();
	}
	public RoutesPage loadsCreateNewRoutePage() {
			return loadMainPage().clicksCreateRoutesLink();			
	}

	public void createsRoute(String name) {
		 loadsCreateNewRoutePage().createRoute(name);
	}
}
