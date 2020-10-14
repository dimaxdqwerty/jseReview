package by.gsu.epamlab.factories;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.interfaces.DataSource;

public class ResultsFromCSV implements DataSource {
	
    private Scanner scanner;
	
    public ResultsFromCSV(String fileName) {
	try {
	    scanner = new Scanner(new FileReader(fileName));
	}catch (FileNotFoundException e) {
	    System.err.print(Constants.FILE_ERROR);
	}
    }

    @Override
    public Result getResult() {
	return new Result(scanner.nextLine());
    }

    @Override
    public boolean hasResult() {
	return scanner.hasNext();
    }
    
}