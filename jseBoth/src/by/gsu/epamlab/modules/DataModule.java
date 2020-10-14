package by.gsu.epamlab.modules;

import java.sql.SQLException;

import by.gsu.epamlab.interfaces.DataSource;
import by.gsu.epamlab.interfaces.DataDestination;

public class DataModule {
	
    private DataSource dataSourse;
    private DataDestination dataDestination;
	
    public DataModule(DataSource dataSourse, DataDestination dataDestination) throws SQLException {
	this.dataSourse = dataSourse;
	this.dataDestination = dataDestination;
	loadData();
    }
	
    private void loadData() throws SQLException {
	while(dataSourse.hasResult()) {
	    dataDestination.loadData(dataSourse.getResult());
	}
    }
    
}