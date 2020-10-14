package by.gsu.epamlab.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.gsu.epamlab.beans.Result;
import by.gsu.epamlab.constants.Constants;

public class ResultHandler extends DefaultHandler {
    
    private enum ResultEnum {
	RESULTS, STUDENT, LOGIN, TESTS, TEST;
    }
    private List<Result> results = new ArrayList<>();
    private ResultEnum currentEnum;
    private String login;
    private Iterator<Result> it = results.iterator();
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
	currentEnum = ResultEnum.valueOf(localName.toUpperCase());
	if(currentEnum == ResultEnum.TEST) {
	    results.add(new Result(login, attributes.getValue(Constants.NAME), attributes.getValue(Constants.DATE), attributes.getValue(Constants.MARK)));
	}
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
	if(currentEnum == ResultEnum.LOGIN) {
	    String str = new String(ch, start, length).trim();
	    if(!str.isEmpty()) {
		login = str;
	    }
	}
    }
    
    public Result getResults() {
	return it.next();
    }
    
}
