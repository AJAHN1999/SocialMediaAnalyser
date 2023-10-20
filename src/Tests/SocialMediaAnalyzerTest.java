package Tests;


import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;
import Database.DatabaseUtility;
import Database.DatabaseUtilityPosts;
import Model.posts;
import Model.users;
import customExceptions.UsernameExistsException;

public class SocialMediaAnalyzerTest {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
	
	
	

	@Test
	public void testAuthenticate() {
		users user = new users(1,"ajahnt","ajju","bhai","ajahnt",1);
		users retrievedUser;
		try {
			retrievedUser = DatabaseUtility.authenticate("ajahnt", "ajahnt");
			assertEquals(user.getUserid(), retrievedUser.getUserid());
			assertEquals(user.getPassword(),retrievedUser.getPassword());
		}catch(SQLException e) {System.out.println("couldn't retrieve user , test case failed");};
	}


	@Test
	public void testRegistration() {
		users userValid = new users("rami reddy","rami","reddy","ramireddy");//will fail when test case is run 
		//current user in test case is added to DB.
		try {
			assertTrue(DatabaseUtility.addUser(userValid));
			DatabaseUtility.deleteUser(userValid);
		} catch (UsernameExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testUserVIPUpdate() {
		users userTest;
		try {
			userTest = DatabaseUtility.authenticate("ram reddy", "ramreddy");
			boolean result = DatabaseUtility.updateUser(userTest);
			assertTrue(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	 @Test
	 public void testRetrievePostFromDB() {
	        // Assuming postId exists in the database
	       
	        
	        try {
	        	users testUser = DatabaseUtility.authenticate("ajahnt", "ajahnt");
	            posts post = DatabaseUtilityPosts.retrievePostFromDB(testUser, 5);
	            assertNotNull(post);
	        } catch (SQLException e) {
	            fail("SQLException occurred: " + e.getMessage());
	        }
	    }

	/*This test case works, however you may have */
//	@Test
//	public void testUpdateProfile() {
//		try {
//			users user = DatabaseUtility.authenticate("ajahnt", "ajahn");
//			users  updatedUser = DatabaseUtility.updateUser(user, "ajahnt", "aj","tund" , "ajahn");
//			boolean result = (!user.getFirstname().equals(updatedUser.getFirstname()));
//			assertFalse(result);
//			
//
//		}catch(UsernameExistsException e) {}
//		catch(SQLException e ) {e.printStackTrace();}
//	}


	@Test
    public void testGettersAndSetters() {
        // Test getters
		users user1 = new users("john_doe", "John", "Doe", "password");
        assertEquals("john_doe", user1.getUsername());
        assertEquals("John", user1.getFirstname());
        assertEquals("Doe", user1.getLastname());
        assertEquals("password", user1.getPassword());
        assertEquals(0, user1.getIsVIP());
	}
	
	
	  @Test
	   public void testAddPosttoDB() {
	        // Create a test user
	        users testUser = new users("testUser", "Test", "User", "password");       
	        // Create a test post
	        posts testPost = new posts("Test Author", "Test Content", 0, 0, LocalDateTime.now());
	        
	        try {
	        	DatabaseUtility.addUser(testUser);
	            boolean result = DatabaseUtilityPosts.addPosttoDB(testUser, testPost);
	            assertTrue(result);
	            DatabaseUtilityPosts.deletePost(testPost.getPostID(), testUser);
	            DatabaseUtility.deleteUser(testUser);
	        }catch(UsernameExistsException e) {e.printStackTrace();} 
	        catch (SQLException e) {
	            fail("SQLException occurred: " + e.getMessage());
	        }
	    }
	  
//	  @Test
//	    public void testDeletePost() {
//	        try {
//	        	users testUser = DatabaseUtility.authenticate("ajahnt", "ajahnt");
//	        	 posts testPost = new posts("Test Author", "Test Content", 0, 0, LocalDateTime.now());
//	        	 DatabaseUtilityPosts.addPosttoDB(testUser, testPost);
//	            boolean result = DatabaseUtilityPosts.deletePost(testPost.getPostID(), testUser);
//	            assertTrue(result);
//	        } catch (SQLException e) {
//	            fail("SQLException occurred: " + e.getMessage());
//	        }
//	    }

	  
	  


}	


