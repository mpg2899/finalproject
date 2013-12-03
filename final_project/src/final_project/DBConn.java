package final_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConn {
	
	// testing
	public String[] users = {"Bob", "Mike"};
	public String[] userids = {"0", "1"};
	// end testing
	
	Connection connection;
	public DBConn() throws ClassNotFoundException, SQLException {
		 Class.forName("org.sqlite.JDBC");
		 connection = null;
		 connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
	}
	
	public String[][] getUsers() {

		String[][] results = {userids, users};
		return results;
	}
	

}
