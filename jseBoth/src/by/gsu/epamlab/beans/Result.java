package by.gsu.epamlab.beans;

import java.sql.Date;

import by.gsu.epamlab.constants.*;

public class Result {
    
    private String login;
    private String test;
    private Date date;
    private int mark;
	
    public Result(String csvLine) {
	String[] line = csvLine.split(Constants.DELIMETER);
	login = line[0];
	test = line[1];
	date = Date.valueOf(line[2]);
	mark = Integer.parseInt(line[3]);
    }
    
    public Result(String login, String test, String date, String mark) {
	this.login = login;
	this.test = test;
	this.date = Date.valueOf(date);
	this.mark = Integer.valueOf(mark);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
	
}