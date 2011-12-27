package uk.chrismay.springtest.dsl;

import java.io.IOException;

import com.meterware.httpunit.WebResponse;

public class App {

	private static final String SERVLET_PORT=System.getProperty("servlet.port","8080");
	private static final String SERVER_URL = "http://localhost:" + SERVLET_PORT + "/";
	private static final String CONTEXT="springtest";
	private static final String APP_BASE_URL = SERVER_URL + CONTEXT;
	public static final String STATUS_URL = APP_BASE_URL + "/status.htm";
	public static final String HOME_URL = APP_BASE_URL + "/home.htm";
	private final Session session;
	
	private MainPage mainPage;
	private RoutesPage routesPage;
	
	public App() {
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
		if (mainPage == null){
			mainPage = new MainPage(session);
		}
		return mainPage;
	}
	public MainPage mainPage() {
		return loadMainPage();
	}

	public MainPage viewsMainPage() {
		return new MainPage(session);
	}

	public RoutesPage loadsRoutesPage() {
		if (routesPage == null){
			routesPage = loadMainPage().clicksCreateRoutesLink();			
		}
		return routesPage;
	}

	public void createsRoute(String name) {
		 loadsRoutesPage().createRoute(name);
	}
}
