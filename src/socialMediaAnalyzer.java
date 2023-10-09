//
//
//import java.time.*;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeParseException;
//import java.util.*;
//
//class InvalidContentException extends Exception {
//}
//
//public class socialMediaAnalyzer {
//	public static HashMap<Integer, Post> postsDb = new HashMap<Integer, Post>();
//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
//	Scanner input = new Scanner(System.in);
//
//	public void addPost(int id, Post p1) {
//
//		socialMediaAnalyzer.postsDb.put(id, p1);
//		System.out.println("The post has been added to the database!");
//	}
//
//	public void deletePost(int postId) {
//
//		if (postsDb.containsKey(postId)) {
//			postsDb.remove(postId);
//			System.out.printf("%d postId has been deleted\n", postId);
//		} else {
//			System.out.println("Sorry the post does not exist in the database!");
//		}
//	}
//
//	public Post retrievePost(int postId) {
//
//		if (postsDb.containsKey(postId)) {
//			return postsDb.get(postId);
//		} else {
//			//System.out.printf("No post with post ID: %d found!\n" + "\n", postId);
//			return null;
//		}
//
//	}
//
//	public List<Post> mostLikes(int N) {
//		List<Post> posts = new ArrayList<Post>();
//		// Post[] postsarray = new Post[N];
//		Comparator<Post> com = new Comparator<Post>() {		//idea credit - https://www.youtube.com/watch?v=ZA2oNhtNk3w&ab_channel=Telusko
//			public int compare(Post p1, Post p2) {
//				if (p1.getLikes() > p2.getLikes()) {
//					return -1;
//				} else {
//					return 1;
//				}
//			}
//		};
//		for (Post eachpost : postsDb.values()) {
//			posts.add(eachpost);
//		}
//		Collections.sort(posts, com);
//		try {
//			return posts.subList(0, N);
//		} catch (Exception e) {
//			return posts;
//		}
//	}
//
//	public List<Post> mostShares(int N) {
//		List<Post> posts1 = new ArrayList<Post>();
//		Comparator<Post> com = new Comparator<Post>() {		//idea credit - https://www.youtube.com/watch?v=ZA2oNhtNk3w&ab_channel=Telusko
//			public int compare(Post p1, Post p2) {
//				if (p1.getShares() > p2.getShares()) {
//					return -1;
//				} else {
//					return 1;
//				}
//			}
//		};
//		for (Post eachpost : postsDb.values()) {
//			posts1.add(eachpost);
//		}
//		Collections.sort(posts1, com);
//		try {
//			return posts1.subList(0, N);
//		} catch (Exception E) {
//			return posts1;
//		}
//	}
//
//	public LocalDateTime dateTimeFormat(String dateTime) {
//		try {
//			LocalDateTime dTime = LocalDateTime.parse(dateTime, formatter);
//			return dTime;
//		} catch (DateTimeParseException e) {
//			System.out.println(e.getMessage());
//			return null;
//		}
//	}
//
//}
	



	
