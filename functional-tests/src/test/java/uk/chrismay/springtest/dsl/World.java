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
	public App theApplication(){
		return _app;
	}
	

}
