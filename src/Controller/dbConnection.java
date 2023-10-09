package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {

	private static final String DB_URL_Users = "jdbc:sqlite:users.db";
	private static final String DB_URL_Posts = "jdbc:sqlite:posts.db";

	public static Connection getConnectionUsers() throws SQLException {
		return DriverManager.getConnection(DB_URL_Users);
	}
	
	public static Connection getConnectionPosts() throws SQLException {
		return DriverManager.getConnection(DB_URL_Posts);
	}
}