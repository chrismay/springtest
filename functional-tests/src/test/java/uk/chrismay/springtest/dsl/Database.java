package uk.chrismay.springtest.dsl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class Database {

	private String dbUrl;
	private String dbUser;
	private String dbPassword;
	private static final Logger LOG = Logger.getLogger(Database.class);
	
	public Database(){
		String driverName = System.getProperty("test.datasource.driver", "com.mysql.jdbc.Driver");
		dbUrl = System.getProperty("test.datasource.url","jdbc:mysql://localhost:3306/rides");
		dbUser= System.getProperty("test.datasource.user","rides");
		dbPassword = System.getProperty("test.datasource.password","");
	   try {
		Class.forName(driverName);
	} catch (ClassNotFoundException e) {
		throw new RuntimeException(e);
	}
	
	}
	public void isEmptied() {
		try {
			Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		    Statement deleter = conn.createStatement();
		    deleter.execute("delete from Route");
		    deleter.execute("delete from Ride");
		    // conn.commit();
			LOG.info("Cleared all data from database");
		    conn.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
