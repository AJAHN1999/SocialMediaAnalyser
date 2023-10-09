//
//
//import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.HashMap;
//import java.util.List;
//
//import org.junit.Test;
//
//public class SocialMediaAnalyzerTest {
//
//	
//
//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
//	Post p1 = new Post(100,"dsfsdf","dsfsdfdsf",23213,232,LocalDateTime.parse("12/12/2022 13:22",formatter));
//	Post p2 = new Post(123,"sdfsdfds","sdfsdfsd",3432,32423,LocalDateTime.parse("12/12/2022 13:22",formatter));
//	Post p3 = new Post(1234,"sdfsdfds","sdfsdfsd",34324,324235,LocalDateTime.parse("12/12/2022 13:22",formatter));
//	Post p4 = new Post(1,"sdfdfds","sdfsdfsd",34324,324235,LocalDateTime.parse("12/12/2022 13:22",formatter));
//	
//	HashMap<Integer, Post> posts1Db = new HashMap<Integer, Post>();
//	socialMediaAnalyzer s1 = new socialMediaAnalyzer();
//
//	@Test
//	public void testAddPost() {
//		//System.out.println("Testing if posts are getting added properly...");
//		s1.addPost(100, p1);
//		s1.addPost(123, p2);
//		assertEquals(p1,socialMediaAnalyzer.postsDb.get(100));
//		assertEquals(p2,socialMediaAnalyzer.postsDb.get(123));
//		//System.out.println("AddPost method working!"+"\n");
//	}
//	
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
//	@Test
//	public void testFileReader() {//testing if file has a attribute csv value like date and time missing
//		FileReader3000 f1 = new FileReader3000();
//		f1.path= "test.csv";
//		assertThrows(IOException.class,()-> f1.readFile(),"");
//	
//	}
//	
//	@Test
//	public void testEmptyFileReader(){ // testing if file is not found.
//		FileReader3000 f1 = new FileReader3000();
//		f1.path = "test1.csv";
//		assertThrows(FileNotFoundException.class , ()-> f1.readFile(),"");
//	}
//}
