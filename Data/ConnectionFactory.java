package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String URL = "jdbc:oracle:thin:@calvin.humber.ca:1521:GROK";
	private static final String USER = "n01368055";
	private static final String PASS = "oracle";

	//To make sure that we're creating only one connection
	private static Connection connection = null;


	public static Connection getConnection() {
		if (connection == null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = DriverManager.getConnection(URL,USER,PASS);
				System.out.println("Connected to db");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connection;
	}

}
