package Database;
import customExceptions.UsernameExistsException;

import java.sql.*;

import org.sqlite.SQLiteException;


public class dbutility {
	// Database connection and query

	// Establish a database connection

	public static boolean authenticate(String username, String password) {
		try(Connection con = databaseConnection.getConnection()){
			System.out.println("accessed try block");
			System.out.println(con);
			String query = "SELECT username,password FROM users WHERE username = ?";
			System.out.println("accessed past query st");
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1,username);

			// Execute the query and retrieve results
			ResultSet resultSet = preparedStatement.executeQuery();
			String usernameR = resultSet.getString("username");
			String passwordR = resultSet.getString("password");
			System.out.println(usernameR);
			System.out.println(passwordR);
			if (usernameR.equals(username) && passwordR.equals(password)) {
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("nope");
			return false;
		} 
	}
	public static boolean addUser(String username, String firstname, String lastname, String password) throws SQLException,UsernameExistsException {
		String insertQuery = "INSERT INTO users (username, firstname, lastname, password) VALUES (?, ?, ?, ?)";
		Connection con = databaseConnection.getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, firstname);
		preparedStatement.setString(3, lastname);
		preparedStatement.setString(4, password);
		System.out.println("reached before execute");
		int rowsAffected = preparedStatement.executeUpdate();
		System.out.println(rowsAffected);
		if (rowsAffected !=1) {
			//throw new UsernameExistsException();
			return false;
		}
		else {
			return true;
		}
	}
		


	public static boolean updateUser(String loggedinName, String username, String firstname, String lastname, String password)throws UsernameExistsException,SQLException{
		//String usernameDB = username(username);
		if(checkUserExists(username)) {
		System.out.println("before insertquery inside check user exists");
		String insertQuery = "UPDATE users SET username = ?, firstname = ?,lastname = ? , password =? WHERE username = ? ";
		Connection con = databaseConnection.getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
		System.out.println("reached inside if block of checkuserexists");
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, firstname);
		preparedStatement.setString(3, lastname);
		preparedStatement.setString(4, password);
		preparedStatement.setString(5, loggedinName);
		int rowsAffected = preparedStatement.executeUpdate();
		System.out.println(rowsAffected);
			return true;
		}
		else if(loggedinName.equals(username)) {
			System.out.println("reached elseif loggedin name == username");
			String insertQuery = "UPDATE users SET  firstname = ?,lastname = ? , password =? WHERE username = ? ";
			Connection con = databaseConnection.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
			preparedStatement.setString(1, firstname);
			preparedStatement.setString(2, lastname);
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, loggedinName);
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected);
				return true;
		}
		else {
			return false;
		}

	}//method end
	//	public static String username(String username) throws SQLException {
	//		String retrieveQuery = "SELECT username FROM users WHERE username = ? ";
	//		Connection con = databaseConnection.getConnection();
	//		PreparedStatement preparedStatement = con.prepareStatement(retrieveQuery);
	//		preparedStatement.setString(1, username);
	//		ResultSet resultSet = preparedStatement.executeQuery();
	//		String usernameR = resultSet.getString("username");
	//		return usernameR;
	//	}
	public static boolean checkUserExists(String username) throws SQLException {
		String insertQuery = "SELECT username FROM users WHERE username = ?";
		Connection con = databaseConnection.getConnection();
		PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
		preparedStatement.setString(1, username);
		ResultSet resultSet1 = preparedStatement.executeQuery();
		String usernameR = resultSet1.getString("username");
		System.out.println("username from checkUserExists username:"+usernameR);
		if (usernameR.isEmpty()){
			System.out.println("good to go");
			return true;//good to go
		}	
		else {
			System.out.println("a username exists reached false");
			return false;
		}
	}


}









