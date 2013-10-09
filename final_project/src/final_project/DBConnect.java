package final_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnect {

	public static void createDB(String fileName) throws ClassNotFoundException
	  {
		
	  }
	
	public static void insertDB(CalendarActivity record, Object mydate) {
		System.out.println(record.name);
		System.out.println(record.userid);
		System.out.println(record.dateid);
		System.out.println(record.starttime);
		System.out.println(record.stoptime);
		System.out.println(record.summary);
	}
	
	public static CalendarActivity readDB() {
		
		CalendarActivity myActivity = new CalendarActivity();
		myActivity.setName("Mike");
		myActivity.setSummary("A test");
		return myActivity;
		
	}
	
	
}
