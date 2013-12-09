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
	public List<Integer> useridsList = new ArrayList<Integer>();
	
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
		 connection = DriverManager.getConnection("jdbc:sqlite:calendar.db");
	}
	
	// Save new user to DB.
	public void saveNewUser(String username) {

		try {
	      Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.
	      statement.executeUpdate("insert into users values(null, '" + username + "')");
		}
	    catch(SQLException e)
	    {
	      // if the error message is "out of memory", 
	      // it probably means no database file is found
	      System.err.println(e.getMessage());
	      
	    }
	    finally
	    {
	        System.out.println("New user saved!");
	    }
	}
	
	// Populate list of all users and userIDs found in DB.
	public void findUsers() throws SQLException {
		// Query to get all users and ids.
		
		// Clear existing list
		usersList.clear();
		useridsList.clear();
		
		  Statement statement = connection.createStatement();
	      statement.setQueryTimeout(30);  // set timeout to 30 sec.
	      ResultSet rs = statement.executeQuery("select * from users");
	      while(rs.next())
	      {
	        // read the result set
	        System.out.println("name = " + rs.getString("name"));
	        System.out.println("id = " + rs.getInt("id"));
			 usersList.add(rs.getString("name"));
			 useridsList.add(rs.getInt("id"));
	      }
		// For loop to add each result to list.

	}
	
	// Find all dates in current month which have an event for current user.
	public void activeDates(int userid, int month, int year) {
		// Populate activeDatesList
		try {
		      Statement statement = connection.createStatement();
		      statement.setQueryTimeout(30);  // set timeout to 30 sec.
		      ResultSet rs = statement.executeQuery(
		    		  "select day from events where userid = " + userid
		    		  + " and month = " + month
		    		  + " and year = " + year);
		      while(rs.next())
		      {
		        // read the result set
		    	activeDatesList.add(rs.getInt("day"));
		      }
			}
		    catch(SQLException e)
		    {
		      // if the error message is "out of memory", 
		      // it probably means no database file is found
		      System.err.println(e.getMessage());
		      
		    }
		    finally
		    {
		        System.out.println("New user saved!");
		    }
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
