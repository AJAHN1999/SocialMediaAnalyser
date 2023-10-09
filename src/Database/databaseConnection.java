package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnection {
	private static final String DB_URL = "jdbc:sqlite:src/SMA.db";
//	CREATE TABLE "users" (
//			"username"	TEXT,
//			"password"	TEXT,
//			"firstname"	TEXT,
//			"lastname"	TEXT,
//			PRIMARY KEY("username")
//		)
//	INSERT INTO users VALUES("ajahn","ajahn","ajahn","tundla");


	public static Connection getConnection() throws SQLException {	
		return DriverManager.getConnection(DB_URL);
		}

	}

