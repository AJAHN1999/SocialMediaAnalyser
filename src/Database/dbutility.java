package Database;

import customExceptions.UsernameExistsException;
import java.sql.*;
import Model.users;
import alerts.alerts;


public class dbutility {
	// Database connection and query

	// Establish a database connection

	public static users authenticate(String username, String password) throws SQLException {
		Connection con = databaseConnection.getConnection();
		try{
			System.out.println("accessed try block");
			System.out.println(con);
			String query = "SELECT username,firstname,lastname,password FROM users WHERE username = ?";
			System.out.println("accessed past query st");
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1,username);

			// Execute the query and retrieve results
			ResultSet resultSet = preparedStatement.executeQuery();
			String usernameR = resultSet.getString("username");
			String firstnameR = resultSet.getString("username");
			String lastnameR = resultSet.getString("lastname");
			String passwordR = resultSet.getString("password");
			users user = new users(usernameR,firstnameR, lastnameR, passwordR);//retrieveing a user object
			System.out.println(usernameR);
			System.out.println(passwordR);
			resultSet.close();
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				
				con.close();
				return user;
			}
			else {
				con.close();
				return null;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("nope");
			con.close();
			return null;
		} finally {
			con.close();}
	}

	public static boolean addUser(users newUser) throws UsernameExistsException, SQLException {
		String insertQuery = "INSERT INTO users (username, firstname, lastname, password) VALUES (?, ?, ?, ?)";
		Connection con = databaseConnection.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
			preparedStatement.setString(1, newUser.getUsername());
			preparedStatement.setString(2, newUser.getFirstname());
			preparedStatement.setString(3, newUser.getLastname());
			preparedStatement.setString(4, newUser.getPassword());
			System.out.println("reached before execute");
			if(checkUserExists(newUser.getUsername())) {
			con.close();	
			return false;
			}
			else {preparedStatement.executeUpdate();con.close();return true; }
//			if (rowsAffected !=1) {
//				//throw new UsernameExistsException();
//				con.close();
//				return false;
//			}
//			else {
//				con.close();
//				return true;
//			}
		}catch(SQLException e) {
			e.printStackTrace();
			con.close();
			return false;
		}finally {
			con.close();}
	}






	public static boolean updateUser(users user,String newname,String firstname, String lastname, String password)throws UsernameExistsException,SQLException{
		//String usernameDB = username(username);
		String insertQuery = "UPDATE users SET username = ?, firstname = ?,lastname = ? , password =? WHERE username = ? ";
		String insertUserQuery = "UPDATE users SET  firstname = ?,lastname = ? , password =? WHERE username = ? ";
		Connection con = databaseConnection.getConnection();
		try {
			if(!checkUserExists(newname)) {
				System.out.println("before insertquery inside check user exists");	
				PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
				System.out.println("reached inside if block of checkuserexists");
				preparedStatement.setString(1, newname);
				preparedStatement.setString(2, firstname);
				preparedStatement.setString(3, lastname);
				preparedStatement.setString(4, password);
				preparedStatement.setString(5, user.getUsername());
				int rowsAffected = preparedStatement.executeUpdate();
				System.out.println(rowsAffected);
				con.close();
				return true;
			}
			else if(newname.equals(user.getUsername())) {
				System.out.println("reached elseif loggedin name == username");	
				PreparedStatement preparedStatement = con.prepareStatement(insertUserQuery);
				preparedStatement.setString(1, firstname);
				preparedStatement.setString(2, lastname);
				preparedStatement.setString(3, password);
				preparedStatement.setString(4, user.getUsername());
				int rowsAffected = preparedStatement.executeUpdate();
				System.out.println(rowsAffected);
				con.close();
				return true;
			}
			else {
				con.close();
				throw new UsernameExistsException();	
				//return false;
				
			}
		}
		catch(UsernameExistsException e) {
			e.printStackTrace();
			alerts.UserExistsAlert();
			con.close();
			return false;
		}
		catch(SQLException e) {
			e.printStackTrace();
			con.close();
			return false;
		}finally {
			con.close();}
	}

	public static boolean checkUserExists(String newname) throws SQLException {
		String insertQuery = "SELECT username FROM users WHERE username = ?";
		Connection con = databaseConnection.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
			preparedStatement.setString(1, newname);
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









