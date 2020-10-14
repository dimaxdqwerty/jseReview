import java.sql.SQLException;

import by.gsu.epamlab.factories.ResultsFromXML;
import by.gsu.epamlab.beans.LoadToDB;
import by.gsu.epamlab.connectors.DBConnection;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.modules.DataModule;

public class Runner2 {

    public static void main(String[] args) {
	
	final String DB_URL= "jdbc:mysql://127.0.0.1:3306/results2?serverTimezone=Europe/Minsk";
	final String FILENAME = "src/results.xml";
	final String USER = "root";
	final String PASSWORD = "root";
	
	try {
	    new DataModule(new ResultsFromXML(FILENAME), new LoadToDB(DBConnection.getInstance(DB_URL, USER, PASSWORD).getConnection()));
	}catch(SQLException e) {
	    System.err.print(Constants.DB_ERROR);
	}

    }

}
