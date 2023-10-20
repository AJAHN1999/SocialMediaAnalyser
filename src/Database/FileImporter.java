package Database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Model.posts;
import Model.users;
import alerts.alerts;


public class FileImporter {

	String line = "";
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");
	private users user;
	private File file;
	
	public FileImporter(users user, File file) {
		this.user = user;
		this.file = file;
	}

	public void readFile() throws FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(this.file));
		try {
			while ((line = br.readLine()) != null) {	//reference - https://www.youtube.com/watch?v=-Aud0cDh-J8&ab_channel=AlexLee
				if(line.startsWith("ID")) {continue;}
				String[] post = line.split(",");
				try {
				posts p1 = new posts(post[2],post[1],Integer.parseInt(post[3]),Integer.parseInt(post[4]),LocalDateTime.parse(post[5], formatter));
				DatabaseUtilityPosts.addPosttoDB(this.user, p1);		}catch(NumberFormatException e) {continue;}
				catch(DateTimeParseException e){e.printStackTrace();continue; }
			}
			alerts.bulkImportSuccessfullAlert();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException f) {
			System.out.println("Invalid number of columns found "+f);
			f.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}