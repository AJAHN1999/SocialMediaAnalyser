package Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Model.users;
import View.updateProfileScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class addPostController {

	private Stage primaryStage;

	@FXML
	private TextField content;

	@FXML
	private TextField likes;

	@FXML
	private TextField shares;

	@FXML
	private TextField dateandtime;

	@FXML
	private Button addtoPost;

	private users user;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");

	@FXML
	private void initialize() {

	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}


	
	@FXML 
	private void addtoPost(ActionEvent event) {
		try {
		String contentUser = content.getText();
		int likesUser = Integer.parseInt(likes.getText());
		int sharesUser = Integer.parseInt(shares.getText());
		LocalDateTime dateandtimeUser = LocalDateTime.parse(dateandtime.getText(),formatter);
	}catch(NumberFormatException e) {
		e.printStackTrace();}
	catch(DateTimeParseException e ){
		e.printStackTrace();
	}
		
		
	}
	

	@FXML
	private void setProfile(ActionEvent event) {
		updateProfileScene profileupdateScene = new updateProfileScene(primaryStage);
		primaryStage.setTitle(profileupdateScene.getTitle());
		primaryStage.setScene(profileupdateScene.getScene(user));
		primaryStage.show();
	}
	}

