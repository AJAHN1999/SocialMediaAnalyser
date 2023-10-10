package Database;
import customExceptions.UsernameExistsException;

import java.sql.*;

import org.sqlite.SQLiteException;


public class dbutility {
	// Database connection and query

	// Establish a database connection

	public static boolean authenticate(String username, String password) throws SQLException {
			Connection con = databaseConnection.getConnection();
			try{
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
			resultSet.close();
			if (usernameR.equals(username) && passwordR.equals(password)) {
				con.close();
				return true;
			}
			else {
				con.close();
				return false;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("nope");
			con.close();
			return false;
		} finally {
			con.close();}
		}
	
	public static boolean addUser(String username, String firstname, String lastname, String password) throws UsernameExistsException, SQLException {
		String insertQuery = "INSERT INTO users (username, firstname, lastname, password) VALUES (?, ?, ?, ?)";
		Connection con = databaseConnection.getConnection();
		try {
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
			con.close();
			return false;
		}
		else {
			con.close();
			return true;
		}
	}catch(SQLException e) {
		e.printStackTrace();
		con.close();
		return false;
		}finally {
			con.close();}
	}
	

		
	


	public static boolean updateUser(String loggedinName, String username, String firstname, String lastname, String password)throws UsernameExistsException,SQLException{
		//String usernameDB = username(username);
		String insertQuery = "UPDATE users SET username = ?, firstname = ?,lastname = ? , password =? WHERE username = ? ";
		String insertUserQuery = "UPDATE users SET  firstname = ?,lastname = ? , password =? WHERE username = ? ";
		Connection con = databaseConnection.getConnection();
		try {
		if(!checkUserExists(username)) {
		System.out.println("before insertquery inside check user exists");	
		PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
		System.out.println("reached inside if block of checkuserexists");
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, firstname);
		preparedStatement.setString(3, lastname);
		preparedStatement.setString(4, password);
		preparedStatement.setString(5, loggedinName);
		int rowsAffected = preparedStatement.executeUpdate();
		System.out.println(rowsAffected);
		con.close();
			return true;
		}
		else if(loggedinName.equals(username)) {
			System.out.println("reached elseif loggedin name == username");	
			PreparedStatement preparedStatement = con.prepareStatement(insertUserQuery);
			preparedStatement.setString(1, firstname);
			preparedStatement.setString(2, lastname);
			preparedStatement.setString(3, password);
			preparedStatement.setString(4, loggedinName);
			int rowsAffected = preparedStatement.executeUpdate();
			System.out.println(rowsAffected);
			con.close();
				return true;
		}
		else {
			con.close();
			return false;
		}
		}catch(SQLException e) {
			e.printStackTrace();
			con.close();
			return false;
			}finally {
				con.close();}
	}
	
	public static boolean checkUserExists(String username) throws SQLException {
		String insertQuery = "SELECT username FROM users WHERE username = ?";
		Connection con = databaseConnection.getConnection();
		try {
		PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
		preparedStatement.setString(1, username);
		System.out.println("before resultset");
		ResultSet resultSet1 = preparedStatement.executeQuery();
		if(resultSet1.next()) {
			con.close();
			return true;//user exists	
		}	
		else {
			System.out.println("user doesn't exist");
			con.close();
			return false;
			
		}
	}catch(SQLException e) {
		e.printStackTrace();
		con.close();
		return false;
		}finally {
			con.close();}


}
}









