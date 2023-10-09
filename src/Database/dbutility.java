package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;


public class dbutility {
	// Database connection and query
     
        // Establish a database connection
        
    	public static boolean authenticate(String username, String password) {
    		try(Connection con = databaseConnection.getConnection()){
    		System.out.println("accessed try block");
    		System.out.println(con);
    		String query = "SELECT password FROM users WHERE username = ?";
    		System.out.println("accessed past query st");
    		PreparedStatement preparedStatement = con.prepareStatement(query);
    		preparedStatement.setString(1,username);
    		
    		 // Execute the query and retrieve results
    	    ResultSet resultSet = preparedStatement.executeQuery();
    	    System.out.println(resultSet);
    	    System.out.println(resultSet);
    	    if (resultSet.next()) {
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
}

