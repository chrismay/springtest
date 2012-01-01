package uk.chrismay.springtest.dsl;

public class API {

	public RoutesListAPI routesList() {
		return new RoutesListAPI();
		
	}

	public void createsRide(String rideName, String routeName) {
		 new RidesApi().create(rideName,routeName);
		
	}

	public JsonAPIResponse listRides() {
		// TODO Auto-generated method stub
		return new RidesApi().list();
	}

}
