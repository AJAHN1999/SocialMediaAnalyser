

//import java.io.FileNotFoundException;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.time.format.DateTimeParseException;
//import java.util.InputMismatchException;
//import java.util.List;
//import java.util.Scanner;
//
//class InvalidMenuOptionException extends Exception {
//}
//
//public class Menu {
//	socialMediaAnalyzer obj1 = new socialMediaAnalyzer();
//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
//
//	public Menu() {// menu constructor which reads the file upon the start of menu.
//		FileReader3000 f1 = new FileReader3000();
//		try {
//			f1.readFile();
//		} catch (FileNotFoundException e) { // throws filenotfound in case the appropriate path is not provided.
//			e.printStackTrace();
//		}
//	}
//
//	public void MainMenu() {
//		System.out.println("---------------------------------------------");
//		System.out.println("Welcome to Social Media Analyzer!");
//		System.out.println("---------------------------------------------");
//		System.out.println("> Select from main menu");
//		System.out.println("---------------------------------------------");
//		System.out.println("1) Add a social media post");
//		System.out.println("2) Delete an existing social media post");
//		System.out.println("3) Retrieve a social media post");
//		System.out.println("4) Retrieve the top N posts with most likes");
//		System.out.println("5) Retrieve the top N posts with most shares");
//		System.out.println("6) Exit");
//		System.out.println("----------------------------------------------" + "\n");
//		System.out.println("Please select an option:");
//	}
//
//	public void runMenu() {		//runMenu method to run the program
//		int ID = 0;
//		boolean dontExit = true;
//		Scanner input = new Scanner(System.in);
//		int option = 0;
//		while (dontExit) {
//			MainMenu();
//			try {
//				option = input.nextInt();
//				input.nextLine();
//				if (option > 6) {
//					throw new InvalidMenuOptionException();
//				}
//			} catch (InvalidMenuOptionException e) {
//				System.out.println("Please provide an option between 1 to 6. " + e);
//				continue;
//			} catch (InputMismatchException e) {
//				input.nextLine();
//				System.out.println("You may have entered a string, please try again " + e);
//				continue;
//			}
//			switch (option) {
//			case 1:
//				// object should be added to the hashmap
//				try {
//					System.out.println("Please provide post ID:");
//					ID = input.nextInt();
//					if (ID < 0) {
//						throw new IllegalArgumentException();
//					}
//					input.nextLine();
//					// FIRST CHECKING IF A POST WITH THE SAME POST ID EXISTS
//					Post check = obj1.retrievePost(ID);
//					if (check != null) {
//						System.out.println("\n"
//								+ "There is already a post with the same postId, please try again with a different one!"
//								+ "\n");
//						continue;
//					}
//					System.out.println("Please provide the post content:");
//					String postContent = input.nextLine();
//					if (postContent.contains(",")) {
//						throw new InvalidContentException();
//					}
//					System.out.println("Please provide the post author:");
//					String postAuthor = input.nextLine();
//					System.out.println("Please provide the number of likes of the post:");
//					int postLikes = input.nextInt();
//					if (postLikes < 0) {
//						throw new IllegalArgumentException();
//					}
//					System.out.println("Please provide the number of shares of the post:");
//					int postShares = input.nextInt();
//					if (postShares < 0) {
//						throw new IllegalArgumentException();
//					}
//					input.nextLine();
//					System.out
//							.println("Please provide the date and time of the post in the format of DD/MM/YYYY HH:MM");
//					String postDateTime = input.nextLine();
//					if (!postDateTime.matches("^\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}")) {
//						System.out.println("you may have inputted invalid date and time format, please try again");
//						continue;
//					}
//					Post p1 = new Post(ID, postContent, postAuthor, postLikes, postShares,
//							dateTimeFormat(postDateTime.trim()));
//					obj1.addPost(ID, p1);
//				} catch (InputMismatchException e) {
//					input.nextLine();
//					System.out.println("You may have entered a string, please try again " + e);
//				} catch (InvalidContentException e) {
//					// input.nextLine();
//					System.out.println("You have provided invalid content containing ','" + e);
//				} catch (IllegalArgumentException e) {
//					System.out.println("You have provided a negative number " + e);
//				} catch (Exception e) { // TO HANDLE ANY UNKNOWN EXCEPTIONS...
//					input.nextLine();
//					System.out.println("Something went wrong..");
//				}
//
//				break;
//			case 2:
//				// object should be deleted by searching from hashmap
//				System.out.println("Please provide the post ID:");
//				try {
//					int postId = input.nextInt();
//					input.nextLine();
//					obj1.deletePost(postId);
//				} catch (InputMismatchException e) {
//					input.nextLine();
//					System.out.println("You may have entered a string, please try again " + e);
//				} catch (Exception e) {
//					input.nextLine();
//					System.out.println("Something went wrong..");
//				}
//				break;
//
//			case 3:
//				// object should be retrieved from the hashmap
//				System.out.println("Please enter the post ID: ");
//				try {
//					int postID = input.nextInt();
//					input.nextLine();
//					Post post = obj1.retrievePost(postID);
//					if (post != null) {
//						System.out.printf(
//								"POST ID: %d\nPOST CONTENT: %s\nPOST AUTHOR: %s\nNO. OF LIKES: %d\nNO. OF SHARES: %d\nDATE AND TIME OF POST:%s\n",
//								post.getId(), post.getContent(), post.getAuthor(), post.getLikes(), post.getShares(),
//								post.getDateTime().format(formatter));
//					} else {
//						System.out.println("No such post found!" + "\n");
//						continue;
//					}
//				} catch (InputMismatchException e) {
//					input.nextLine();
//					System.out.println("You may have entered a string, please try again " + e);
//				} catch (Exception e) {
//					input.nextLine();
//					System.out.println("Something went wrong.." + e);
//				}
//				break;
//
//			case 4:
//
//				// retrieveing top N posts with most likes from hashmap
//				System.out.println("Please specify the [N]umber of posts to retrieve:");
//				try {
//					int num = input.nextInt();
//					input.nextLine();
//					int count = 1;
//					List<Post> topLikes = obj1.mostLikes(num);
//					for (Post eachPost : topLikes) {
//						System.out.printf("%d | %d | %s | %d " + "\n", count, eachPost.getId(), eachPost.getContent(),
//								eachPost.getLikes());
//						count++;
//					}
//				} catch (InputMismatchException e) {
//					System.out.println("You may have entered a string, please try again  " + e);
//
//				} catch (Exception e) {
//					System.out.println("Wrong input given");
//					// continue;
//				}
//				break;
//			case 5:
//				// retrieveing top N posts with most shares from hashmap
//				System.out.println("Please specify the [N]umber of posts to retrieve:");
//				try {
//					int count = 1;
//					int N = input.nextInt();
//					input.nextLine();
//					List<Post> topShares = obj1.mostShares(N);
//					for (Post eachPost : topShares) {
//						System.out.printf("%d | %d | %s | %d" + "\n", count, eachPost.getId(), eachPost.getContent(),
//								eachPost.getShares());
//						count++;
//					}
//				} catch (InputMismatchException e) {
//					input.nextLine();
//					System.out.println("You may have entered a string, please try again " + e);
//				} catch (Exception e) {
//					System.out.println("Something went wrong... " + e);
//				}
//				break;
//			case 6:
//				// exit the program
//				dontExit = false;
//				System.out.println("Thank you for using Social Media Analyzer!");
//				break;
//			default:
//				System.out.println("Please select a valid option" + "\n");
//			}
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
//	

