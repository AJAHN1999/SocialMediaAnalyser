package Database;

import customExceptions.UsernameExistsException;
import java.sql.*;
import Model.users;
import alerts.alerts;


public class dbutility {


	public static users authenticate(String username, String password) throws SQLException {
		Connection con = databaseConnection.getConnection();
		try{
			System.out.println("accessed try block");
			System.out.println(con);
			String query = "SELECT userId, username,firstname,lastname,password,isVIP FROM users WHERE username = ?";
			System.out.println("accessed past query st");
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1,username);

			// Execute the query and retrieve results
			ResultSet resultSet = preparedStatement.executeQuery();
			int userId = resultSet.getInt("userId");
			String usernameR = resultSet.getString("username");
			String firstnameR = resultSet.getString("firstname");
			String lastnameR = resultSet.getString("lastname");
			String passwordR = resultSet.getString("password");
			int isVIP = resultSet.getInt("isVIP");
			users user = new users(userId,usernameR,firstnameR, lastnameR, passwordR,isVIP);//retrieveing a user object
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
		String insertQuery = "INSERT INTO users (username, firstname, lastname, password, isVIP) VALUES (?, ?, ?, ?, ?)";
		Connection con= databaseConnection.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
			preparedStatement.setString(1, newUser.getUsername());
			preparedStatement.setString(2, newUser.getFirstname());
			preparedStatement.setString(3, newUser.getLastname());
			preparedStatement.setString(4, newUser.getPassword());
			preparedStatement.setLong(5, newUser.getIsVIP());			
			System.out.println("reached before execute");
			if(UserExists(newUser.getUsername(),con)) {
			con.close();	
			return false;
			}
			else {preparedStatement.executeUpdate();con.close();return true; }

		}catch(SQLException e) {
			e.printStackTrace();
			con.close();
			return false;
		}finally {
			con.close();}
	}






	public static users updateUser(users user,String newname,String firstname, String lastname, String password)throws UsernameExistsException,SQLException{
		//String usernameDB = username(username);
		String insertQuery = "UPDATE users SET username = ?, firstname = ?,lastname = ? , password =? WHERE username = ? ";
		String insertUserQuery = "UPDATE users SET  firstname = ?,lastname = ? , password =? WHERE username = ? ";
		Connection con = databaseConnection.getConnection();
		try {
			if(!UserExists(newname,con)) {
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
				users userNew = retrieveUser(newname, firstname, lastname, password,con);			
				con.close();
				//return true;
				return userNew;
			}
			else if(newname.equals(user.getUsername())) {
				System.out.println("reached elseif loggedin name == username");	
				PreparedStatement preparedStatement = con.prepareStatement(insertUserQuery);
				preparedStatement.setString(1, firstname);
				preparedStatement.setString(2, lastname);
				preparedStatement.setString(3, password);
				preparedStatement.setString(4, newname);
				preparedStatement.executeUpdate();
				//System.out.println(rowsAffected);		
				users userNew = retrieveUser(user.getUsername(),firstname,lastname,password,con);
				con.close();
				return userNew;
				//return true;
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
			return null;
			//return false;
		}
		catch(SQLException e) {
			e.printStackTrace();
			con.close();
			//return false;
			return null;
		}finally {
			con.close();}
	}
	
	public static boolean updateUser(users user) throws SQLException {
		String updateVIPQuery = "UPDATE users SET isVIP = 1  WHERE userId = ?";
		Connection con = databaseConnection.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(updateVIPQuery);
			preparedStatement.setLong(1, user.getUserid());
			preparedStatement.executeUpdate();
			con.close();
			return true;
		}catch(SQLException e) {e.printStackTrace();con.close();return false;}
		finally {con.close();}
	}

	public static boolean UserExists(String newname, Connection con) throws SQLException {
		String insertQuery = "SELECT username FROM users WHERE username = ?";
		//Connection con = databaseConnection.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
			preparedStatement.setString(1, newname);
			System.out.println("before resultset");
			
			ResultSet resultSet1 = preparedStatement.executeQuery();// if resultset object isn't returning anything it means there is no such usrname,will be
			//caught by the sqlexception, then, return true in 
			System.out.println("retrieved username from checkUserExists:"+resultSet1.getString("username"));
			if(resultSet1.next()) {
				//con.close();
				return true;//user exists	
			}	
			else {
				System.out.println("user doesn't exist");
				//con.close();
				return false;

			}
		}catch(SQLException e) {
			e.printStackTrace();
			//con.close();
			return false;}//user doesnt exists
//		}finally {
//			con.close();}
//
//
	}
	
	public static users retrieveUser(String username , String firstname, String lastname, String password,Connection con) throws SQLException {
		String retrieveQuery = "SELECT userid, username,firstname,lastname,password,isVIP  FROM users WHERE username = ?";
		//Connection con = databaseConnection.getConnection();
		try {
		PreparedStatement preparedStatement = con.prepareStatement(retrieveQuery);
		preparedStatement.setString(1, username);
		ResultSet resultset = preparedStatement.executeQuery();
		if(resultset.next())
		{		//con.close();
				return new users(resultset.getInt("userid"), resultset.getString("username"),resultset.getString("firstname"),resultset.getString("lastname"),resultset.getString("password"),resultset.getInt("isVIP"));
	}
		else {
			//con.close();
			return null;}
		}catch(SQLException e) {
		e.printStackTrace();
		//con.close();
		return null;}
	}



}







