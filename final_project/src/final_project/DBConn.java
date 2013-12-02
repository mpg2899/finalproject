package final_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConn {
	Connection connection;
	public DBConn() throws ClassNotFoundException, SQLException {
		 Class.forName("org.sqlite.JDBC");
		 connection = null;
		 connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
	}
	
	public String[][] getUsers() {
		String[] users = {"Bob", "Mike"};
		String[] userids = {"0", "1"};
		String[][] results = {userids, users};
		return results;
	}
	

}
