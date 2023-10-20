

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import Database.DatabaseUtility;
import Model.users;
import customExceptions.UsernameExistsException;

public class SocialMediaAnalyzerTest {

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");

	@Test
	public void testAuthenticate() {
		users user = new users(1,"ajahnt","ajju","bhai","ajahn",1);
		users retrievedUser;
		try {
			retrievedUser = DatabaseUtility.authenticate("ajahnt", "ajahn");
			assertEquals(user.getUserid(), retrievedUser.getUserid());
			assertEquals(user.getPassword(),retrievedUser.getPassword());
		}catch(SQLException e) {System.out.println("couldn't retrieve user , test case failed");};
	}


	@Test
	public void testRegistration() {
		users userValid = new users("srinivas reddy","srinivas","reddy","srinivasreddy");//will fail when test case is run 
		//current user in test case is added to DB.
		try {
			assertTrue(DatabaseUtility.addUser(userValid));
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
	public void testUpdateProfile() {
		try {
			users user = DatabaseUtility.authenticate("ajahnt", "ajahn");
			users  updatedUser = DatabaseUtility.updateUser(user, "ajahnt", "aj","tund" , "ajahn");
			boolean result = (!user.getFirstname().equals(updatedUser.getFirstname()));
			assertFalse(result);

		}catch(UsernameExistsException e) {}
		catch(SQLException e ) {e.printStackTrace();}
	}



	


}	



//	@Test
//	public void testDeletePost() {
//		//System.out.println("Testing if posts are getting deleted properly...");
//		s1.deletePost(100);
//		s1.deletePost(123);
//		assertEquals(null,socialMediaAnalyzer.postsDb.get(100));
//		assertEquals(null,socialMediaAnalyzer.postsDb.get(123));
//		//System.out.println("deletePost method working!"+"\n");
//	}
//
//	@Test
//	public void testRetrievePost() {
//		//System.out.println("Testing if posts are getting retrieved properly...");
//		s1.addPost(100, p1);
//		s1.addPost(123, p2);
//		assertEquals(p1,s1.retrievePost(100));
//		assertEquals(p2,s1.retrievePost(123));
//	}
//	
//	@Test
//	public void testMostLikes() { // TESTING IF NUMBER OF POSTS BEING RETRIEVED IS CORRECT
//		//System.out.println("Testing if mostLikes is working properly...");
//		s1.addPost(100, p1);
//		s1.addPost(123, p2);
//		s1.addPost(1234,p3);
//		List<Post> mList = s1.mostLikes(2);
//		assertEquals(2,mList.size());
//	}
//	
//	@Test
//	public void testMostShares() {
//		s1.addPost(100, p1);
//		s1.addPost(123, p2);
//		s1.addPost(1234,p3);
//		List<Post> mList = s1.mostShares(3);
//		assertEquals(3,mList.size());
//	}
//	
//	@Test
//	public void testMostLikes1() { //TESTING IF NUMBER OF POSTS ARE IN ORDER OF MOST LIKES FIRST
//		System.out.println("TESTING IF NUMBER OF POSTS ARE IN ORDER OF MOST LIKES FIRST");
//		s1.addPost(100, p1);
//		s1.addPost(123, p2);
//		s1.addPost(1234,p3);
//		List<Post> mList = s1.mostLikes(3);
//		for (int i=0; i < mList.size()-1; i++) {
//			assertTrue("Likes are sorted",mList.get(i).getLikes()>=mList.get(i+1).getLikes());
//		}
//	}
//	
//	@Test
//	public void testMostShares1() { //TESTING IF NUMBER OF POSTS ARE IN ORDER OF MOST SHARES FIRST
//		System.out.println("TESTING IF NUMBER OF POSTS ARE IN ORDER OF MOST SHARES FIRST");
//		s1.addPost(100, p1);
//		s1.addPost(123, p2);
//		s1.addPost(1234,p3);
//		List<Post> mList = s1.mostShares(3);
//		for (int i=0; i < mList.size()-1; i++) {
//			assertTrue("Shares are sorted",mList.get(i).getShares()>=mList.get(i+1).getShares());
//		}
//	}
//		

//	
//	@Test
//	public void testEmptyFileReader(){ // testing if file is not found.
//		FileReader3000 f1 = new FileReader3000();
//		f1.path = "test1.csv";
//		assertThrows(FileNotFoundException.class , ()-> f1.readFile(),"");
//	}

