package uk.chrismay.springtest.dsl;

import com.meterware.httpunit.WebResponse;

public class World {
	
	private static World _world = new World();
	private final App _app = new App();
	private RoutesPage _routesPage;
	
	public static World When(){
		return _world ;
	}
	public static World Given(){
		return _world;
	}
	public static World Then(){
		return _world;
	}
	public static World And(){
		return _world;
	}
	public App theApplication(){
		return _app;
	}
	public App theUser() {
		return _app;
	}
	public PageMatcher theCurrentPage(){
		return Session.current().currentPage();
	}
	
	public RoutesPage theRoutesPage() {
		if (_routesPage == null){
			_routesPage = new RoutesPage();
		}
		return _routesPage; 
	}
	public static RoutesPage loadRoutesPage(
			Session session, WebResponse page) {
		// TODO Auto-generated method stub
		_world._routesPage = new RoutesPage(session, page);
		return _world._routesPage;
	}
	public MainPage theMainPage() {
		return Session.current().MainPage();
	}
	
	

}
