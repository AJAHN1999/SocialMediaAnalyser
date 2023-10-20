package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class databaseConnection {

	private static final String DB_URL = "jdbc:sqlite:src/SMAnew.db";

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL);
	}
}

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;

//public class databaseConnection {
//    private static databaseConnection instance;
//    private static final String DB_URL = "jdbc:sqlite:src/SMAnew.db";
//    private Connection conn;
//
//    private databaseConnection() {
//        try {
//            conn = DriverManager.getConnection(DB_URL);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static databaseConnection getInstance() {
//        if (instance == null) {
//            instance = new databaseConnection();
//        }
//        return instance;
//    }
//
//    public Connection getConnection() {
//        return conn;
//    }
//
//    public void closeConnection() {
//        try {
//            if (conn != null && !conn.isClosed()) {
//                conn.close();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Something went wrong while closing the connection.");
//        }
//    }
//}
