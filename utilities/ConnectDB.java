package utilities;

import java.sql.*;

// https://www.geeksforgeeks.org/establishing-jdbc-connection-in-java/ (Resources to understand JDBC Connectivity)
public class ConnectDB {
 
	static final String jdbcURL = "jdbc:oracle:thin:@orca.csc.ncsu.edu:1521:orcl01";
	public static Connection conn = null;

    public void ConnectDB()
    {
    	try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch (Exception exp) {
			System.out.println("Sorry!!! Unable to load Oracle Driver");
			exp.printStackTrace();
		}
    }

    public static void connect(String user, String pwd) {
		try {			
			conn = DriverManager.getConnection(jdbcURL, user, pwd);
		}
		catch (Exception e) {
			System.out.println("Sorry!!! unable to connect to the oracle server");
		}
    }
    // Ask any OODD Student for the difference between a Query and a Command.
    // ResultSet in the Java abstraction of records recieved from the Database.
    public static ResultSet execQuery(String cmd) throws SQLException {
     Statement stmt = null;
     if (conn == null)
     {
         System.out.println("Please open a connection to execute a Query");
     }
     stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery(cmd);
     stmt.close();
     return rs;
     }

    public static void execCommand(String cmd) throws SQLException {
     Statement stmt = null;
     if (conn == null)
     {
         throw new SQLException("Please open a connection to execute a Query");
     }
     stmt = conn.createStatement();
     stmt.executeUpdate(cmd);
     stmt.close();
    }

    public static void terminate() {
        if (conn != null) {
            try {
                conn.close();
            }
            catch (Exception e) {
                System.out.println("DB: Unable to close the connection");
            }
        }
    }
}


