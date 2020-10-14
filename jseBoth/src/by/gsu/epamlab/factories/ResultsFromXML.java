package by.gsu.epamlab.factories;

import java.io.IOException;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.handlers.ResultHandler;
import by.gsu.epamlab.interfaces.DataSource;

public class ResultsFromXML implements DataSource {
    
    private ResultHandler handler;
    
    public ResultsFromXML(String filename) { 
	try{
	    XMLReader reader = XMLReaderFactory.createXMLReader();
	    handler = new ResultHandler();
	    reader.setContentHandler(handler);
	    reader.parse(filename);
    	} catch (IOException e) {
	    System.err.println(Constants.FILE_ERROR);
	} catch (SAXException e) {
	    System.err.println(Constants.PARSING_ERROR);
	}
    }
    
    @Override
    public Result getResult() {
	return handler.getResults();
    }

    @Override
    public boolean hasResult() {
	// TODO Auto-generated method stub
	return false;
    }

}
