package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Model.posts;
//import Model.posts;
import Model.users;

public class dbutilityposts {

	
	public static boolean addPosttoDB(users user, posts post ) throws SQLException {
		String insertQuery = "INSERT INTO posts (author, content, likes, shares, dateTime, userId) VALUES (?, ?, ?, ?,?,?)";
		Connection con = databaseConnection.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, post.getContent());
			preparedStatement.setInt(3, post.getLikes());
			preparedStatement.setInt(4, post.getShares());
			preparedStatement.setString(5, post.getDateTime().toString());
			preparedStatement.setInt(6, dbutility.retrieveuserId(user));
			int rowsaffected = preparedStatement.executeUpdate();
			if (rowsaffected == 1) {con.close(); return true;}
			else {con.close(); throw new SQLException();}
			}catch(SQLException e) {e.printStackTrace();con.close(); return false;}
		finally {con.close();}
}
}
