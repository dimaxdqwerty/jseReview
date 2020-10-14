package by.gsu.epamlab.beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.interfaces.DataDestination;

public class LoadToDB implements DataDestination {
	
    private final String INSERT_RESULTS = "INSERT INTO results (loginId, testId, date, mark) VALUES(?, ?, ?, ?)";
    private final String INSERT_INTO_LOGINS = "INSERT INTO logins(name) VALUE (?)";
    private final String INSERT_INTO_TESTS = "INSERT INTO tests(name) VALUE (?)";
    private final String SELECT_LOGIN_ID = "SELECT * FROM logins";
    private final String SELECT_TEST_ID = "SELECT * FROM tests";
    private boolean isLastLoginId = false;
    private boolean isLastTestId = false;
    int loginId = 0, testId = 0;
    private final int NAME_INDEX = 1;
    private final int LOGIN_INDEX = 1;
    private final int TEST_INDEX = 2;
    private final int DATA_INDEX = 3;
    private final int MARK_INDEX = 4;
    private Connection connection;
    
    
	
    public LoadToDB(Connection connection) {
	this.connection = connection;
    }

    @Override
    public void loadData(Result result) {
	try(Statement st = connection.createStatement(); PreparedStatement ps = connection.prepareStatement(INSERT_RESULTS)) {
	    try(PreparedStatement psLogins = connection.prepareStatement(INSERT_INTO_LOGINS)) {
		psLogins.setString(NAME_INDEX, result.getLogin());
		psLogins.addBatch();
		psLogins.executeBatch();
	    }
	    try(PreparedStatement psTests = connection.prepareStatement(INSERT_INTO_TESTS)) {
		psTests.setString(NAME_INDEX, result.getTest());
		psTests.addBatch();
		psTests.executeBatch();
	    }
	    if(!isLastLoginId) {
		try(ResultSet rs = st.executeQuery(SELECT_LOGIN_ID)) {
		    while(rs.next()) {
			if(rs.isLast()) {
			    loginId = rs.getInt(1);
			}
		    }
		}
		isLastLoginId = true;
	    }
	    else {
		loginId++;
	    }
	    if(!isLastTestId) {
		try(ResultSet rs = st.executeQuery(SELECT_TEST_ID)) {
		    while(rs.next()) {
			if(rs.isLast()) {
			    testId = rs.getInt(1);
			}
		    }
		}
		isLastLoginId = true;
	    }
	    else {
		testId++;
	    }
	    ps.setInt(LOGIN_INDEX, loginId);
	    ps.setInt(TEST_INDEX, testId);
	    ps.setDate(DATA_INDEX, result.getDate());
	    ps.setInt(MARK_INDEX, result.getMark());
	    ps.executeUpdate();
	} catch(SQLException e) {
	    System.err.print(Constants.DB_ERROR);
	}	
    }

}