package by.gsu.epamlab.interfaces;

import java.sql.SQLException;

import by.gsu.epamlab.beans.Result;

public interface DataDestination {
	
    public void loadData(Result result) throws SQLException;
	
}