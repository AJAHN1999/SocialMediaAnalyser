//
//
//import java.io.*;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//public class FileReader3000 {
//
//	String path = "src/assignment1/posts.csv";//file path in my assigment
//	String line = "";
//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
//
//	public void readFile() throws FileNotFoundException {
//		BufferedReader br = new BufferedReader(new FileReader(path));
//		try {
//			while ((line = br.readLine()) != null) {	//reference - https://www.youtube.com/watch?v=-Aud0cDh-J8&ab_channel=AlexLee
//				if (line.startsWith("ID")) {
//					continue;
//				}
//				String[] post = line.split(",");
//				Post p1 = new Post(Integer.parseInt(post[0]), post[1], post[2], Integer.parseInt(post[3]),
//						Integer.parseInt(post[4]), LocalDateTime.parse(post[5], formatter));
//				socialMediaAnalyzer.postsDb.put(Integer.parseInt(post[0]), p1);
//			}
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (NumberFormatException f) {
//			System.out.println("Invalid number of columns found "+f);
//			f.printStackTrace();
//		} //catch (Exception e) {
//			//System.out.println("Something went wrong.." + e);
//		//}
//	}
//}


