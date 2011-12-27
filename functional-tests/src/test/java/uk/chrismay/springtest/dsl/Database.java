package uk.chrismay.springtest.dsl;

public class Database {

	private String driverName;
	private String dbUrl;
	private String dbUser;
	private String dbPassword;
	
	public Database(){
		driverName = System.getProperty("test.datasource.driver", "com.mysql.jdbc.driver");
		dbUrl = System.getProperty("test.datasource.url","jdbc:mysql://localhost:3306/rides");
		dbUser= System.getProperty("test.datasource.user","rides");
		dbPassword = System.getProperty("test.datasource.password","");
	}
	public void isEmptied() {
		// TODO Auto-generated method stub
		
	}

}
