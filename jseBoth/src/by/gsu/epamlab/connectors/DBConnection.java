package by.gsu.epamlab.connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    private static DBConnection instance;
    private String dbUrl;
    private String user;
    private String password;
	
    private DBConnection(String dbUrl, String user, String password) {
	this.dbUrl = dbUrl;
	this.user = user;
	this.password = password;
    }

    public Connection getConnection() {
	try {
	    return DriverManager.getConnection(dbUrl, user, password);
	} catch(SQLException e) {
	    e.printStackTrace();
	    return null;
	}
    }
	
    public static DBConnection getInstance(String dbUrl, String user, String password) {
	instance = new DBConnection(dbUrl, user, password);
	return instance;
    }
    
}