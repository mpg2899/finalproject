package final_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Run this class to initialize a new DB.
public class createDB
{
  public static void main(String[] args) throws ClassNotFoundException
  {
    // load the sqlite-JDBC driver using the current class loader
    Class.forName("org.sqlite.JDBC");
    
    Connection connection = null;
    try
    {
      // create a database connection
    
      connection = DriverManager.getConnection("jdbc:sqlite:calendar.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.

      statement.executeUpdate("drop table if exists users");
      statement.executeUpdate("create table users (id integer primary key autoincrement, name string)");
      statement.executeUpdate("insert into users values(null, 'Mike')");
      
      statement.executeUpdate("drop table if exists events");
      statement.executeUpdate("create table events ("
      		+ "id integer primary key autoincrement, "
      		+ "userid integer, "
      		+ "day integer, "
      		+ "month integer, "
      		+ "year integer, "
      		+ "starthour integer, "
      		+ "startmin integer, "
      		+ "stophour integer, "
      		+ "stopmin integer, "
      		+ "title string)");
      statement.executeUpdate("insert into events values(null, 1, 28, 11, 2013, 8, 0, 8, 30, 'Event 1 title')");
      statement.executeUpdate("insert into events values(null, 1, 3, 11, 2013, 10, 0, 10, 30, 'Event 2 title')");
      statement.executeUpdate("insert into events values(null, 1, 2, 0, 2014, 8, 0, 8, 30, 'Event 3 title')");
      statement.executeUpdate("insert into events values(null, 2, 4, 11, 2013, 8, 0, 8, 30, 'Event 4 title')");
      statement.executeUpdate("insert into events values(null, 1, 3, 11, 2013, 9, 0, 9, 30, 'Event 5 title')");
      //statement.executeUpdate("insert into users values(null, 'Joe')");
      ResultSet rs = statement.executeQuery("select * from events");
      while(rs.next())
      {
        // read the result set
        System.out.println("id = " + rs.getInt("id"));
        System.out.println("id = " + rs.getInt("userid"));
        System.out.println("day = " + rs.getInt("day"));
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
      try
      {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {
        // connection close failed.
        System.err.println(e);
      }
    }
  }
  
}