package final_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBConn {
	
	// Create some lists as an attribute so we don't have to return lists of lists.
	public List<String> usersList = new ArrayList<String>();
	public List<String> useridsList = new ArrayList<String>();
	
	// List of active dates
	public List<Integer> activeDatesList = new ArrayList<Integer>();
	
	// Lists for containing info for all events on a specific date.
	public List<Integer> DaysEventsListIDs = new ArrayList<Integer>();
	public List<Integer> DaysEventsListStartHour = new ArrayList<Integer>();
	public List<Integer> DaysEventsListStopHour = new ArrayList<Integer>();
	public List<Integer> DaysEventsListStartMin = new ArrayList<Integer>();
	public List<Integer> DaysEventsListStopMin = new ArrayList<Integer>();
	public List<String> DaysEventsListTitles = new ArrayList<String>();
	
	// Attributes for an event.
    public int EDAY, ESTART_HOUR, ESTART_MIN, ESTOP_HOUR, ESTOP_MIN;
    public String ETITLE, ETEXT;
	
	
	
	Connection connection;
	public DBConn() throws ClassNotFoundException, SQLException {
		 Class.forName("org.sqlite.JDBC");
		 connection = null;
		 connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
		 
		 // Testing

	}
	
	// Save new user to DB.
	public void saveNewUser() {

	
	}
	
	// Populate list of all users and userIDs found in DB.
	public void findUsers() {
		// Query to get all users and ids.
		
		// Clear existing list
		usersList.clear();
		useridsList.clear();
		
		// For loop to add each result to list.
		 usersList.add("Bob");
		 useridsList.add("0");
		 
		 usersList.add("Mike");
		 useridsList.add("1");
	}
	
	// Find all dates in current month which have an event for current user.
	public void activeDates(int userid) {
		// Populate activeDatesList
		
		activeDatesList.add(1);
	}
	
	// Find all events on given date for current user.
	public void getDaysEvents() {
		// Populate DaysEventsListIDs, DaysEventsListStartHour, DaysEventsListStopHour, DaysEventsListStartMin, DaysEventsListStopMin, DaysEventsListTitles
		
		//Query DB to get those details.
	}
	
	// Get details for given event.
	public void getEventDetails() {
		// Query DB to get specific event information based on EventID.
	}
	

}
