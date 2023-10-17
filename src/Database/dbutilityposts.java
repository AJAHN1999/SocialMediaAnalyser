package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Model.posts;
//import Model.posts;
import Model.users;

public class dbutilityposts {

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
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
			preparedStatement.setInt(6, user.getUserid());
			int rowsaffected = preparedStatement.executeUpdate();
			if (rowsaffected == 1) {con.close(); return true;}
			else {con.close(); throw new SQLException();}
			}catch(SQLException e) {e.printStackTrace();con.close(); return false;}
		finally {con.close();}
}

	public static ArrayList<posts> retrieveNpostsFromDB(users user, int N, String type) throws SQLException {
		ArrayList<posts> topNposts = new ArrayList<posts>();
		if (type == "for users") {	
			String retrieveUserPosts = "SELECT postid,author,content,likes,shares,dateTime,userId FROM posts WHERE userid = ? ORDER BY likes DESC LIMIT ?";
			Connection con = databaseConnection.getConnection();
			try {
				PreparedStatement preparedStatement = con.prepareStatement(retrieveUserPosts);
				preparedStatement.setLong(1, user.getUserid());
				preparedStatement.setLong(2,N);
				ResultSet resultset = preparedStatement.executeQuery();
				while(resultset.next()) {
					topNposts.add( new posts(resultset.getInt("postid"),resultset.getInt("userId"),resultset.getString("content"), resultset.getString("author"),resultset.getInt("likes"),resultset.getInt("shares"),LocalDateTime.parse(resultset.getString("dateTime"))));					
				}
				return topNposts;
			}catch(SQLException e) {e.printStackTrace();return null;}
			finally {con.close();}
		}
		if(type == "for all users") {
			String retrievetopNPosts = "SELECT postid,author,content,likes,shares,dateTime,userId FROM posts ORDER BY likes DESC LIMIT ?";
			Connection con = databaseConnection.getConnection();
			try {
				PreparedStatement preparedStatement = con.prepareStatement(retrievetopNPosts);
				preparedStatement.setLong(1,N);
				ResultSet resultset = preparedStatement.executeQuery();			
				while(resultset.next()) {
					topNposts.add( new posts(resultset.getInt("postid"),resultset.getInt("userId"),resultset.getString("content"), resultset.getString("author"),resultset.getInt("likes"),resultset.getInt("shares"),LocalDateTime.parse(resultset.getString("dateTime"))));				
		}		
				return topNposts;
	}catch(SQLException e){e.printStackTrace();return null;}
			finally {con.close();}
		}
		else {return null;}
	}
	
	
	
	
	public static posts retrievePostFromDB(users user, int postId) throws SQLException {
		String retrievePostQuery = "SELECT postid,author,content,likes,shares,dateTime,userId FROM posts WHERE postId = ? AND userid = ?";
		Connection con = databaseConnection.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(retrievePostQuery);
			preparedStatement.setLong(1, postId);
			preparedStatement.setLong(2, user.getUserid());
			ResultSet resultset = preparedStatement.executeQuery();
			if(resultset.next())
			{
				//con.close();
					return new posts(resultset.getInt("postid"),resultset.getInt("userId"),resultset.getString("content"), resultset.getString("author"),resultset.getInt("likes"),resultset.getInt("shares"),LocalDateTime.parse(resultset.getString("dateTime")));
		}
			else {//con.close();
				return null;}	
			}catch(SQLException e) { e.printStackTrace();return null;}
		finally {con.close();}
	}

	
	public static boolean deletePost(int postId) throws SQLException {
		String deletePostQuery = "DELETE FROM posts WHERE postId = ?";
		Connection con = databaseConnection.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement(deletePostQuery);
			preparedStatement.setLong(1, postId);
			int result = preparedStatement.executeUpdate();
			if(result == 1) {return true;}
			else {return false;}
			
	}catch(SQLException e) {e.printStackTrace();return false;}
		finally {con.close();}	
	}
	
	
	
}

