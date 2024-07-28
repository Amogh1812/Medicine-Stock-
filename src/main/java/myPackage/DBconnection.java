package myPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import org.apache.logging.log4j.LogManager;

/**
 * Utility class for managing database connections.
 */
public class DBconnection {
	static Connection conn = null;
//	private static Logger demologger = LogManager.getLogger(DBconnection.class.getName());

	/**
	 * Establishes a connection to the database.
	 *
	 * @return a Connection object to the database
	 * @throws ClassNotFoundException 
	 */
	public static Connection connect() throws ClassNotFoundException  {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/medical_stock";
			String username = "root";
			String password = "amogh1812";

			conn = DriverManager.getConnection(url, username, password);
//            System.out.println("Connected Successfully");
//			demologger.info("Connected Successfully to the database");
		} catch (SQLException e) {
			e.printStackTrace();
//			demologger.error("Connection Failed" + e);
		}
		return conn;
	}
	public static void disconnect() {
        if (conn != null) {
            try {
                conn.close();
//                logger.info("Database connection closed");
            } catch (SQLException e) {
//                logger.error("Error closing database connection: " + e.getMessage());
            	e.printStackTrace();
            }
        }
    }
}

