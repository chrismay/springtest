package uk.chrismay.springtest.dsl;


public class World {
	
	private static World _world = new World();
	private final App _app = new App();
	
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
	

	public MainPage theMainPage() {
		return Session.current().MainPage();
	}
	public Database theDatabase() {
		// TODO Auto-generated method stub
		return new Database();
	}
	public RoutesList theRoutesList() {
		return Session.current().RoutesList();
	}
	public API TheAPI() {
		return new API();
	}
	
	

}
