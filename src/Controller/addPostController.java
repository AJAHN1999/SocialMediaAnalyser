package Controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import Database.dbutility;
import Database.dbutilityposts;
import Model.posts;
import Model.users;
import View.dashboardScene;
import View.updateProfileScene;
import alerts.alerts;
import customExceptions.emptyFieldException;
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
	private TextField dateAndTime;

	@FXML
	private Button addtoPost;

	private users userfromDashboard;
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm");

	@FXML
	private void initialize() {

	}
	
	private void setEmptyFields() {
		content.setText("");
		likes.setText("");
		shares.setText("");
		dateAndTime.setText("");
	}
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public void senduser(users user) {userfromDashboard = user;}
	
	@FXML 
	private void addtoPost(ActionEvent event) throws SQLException {
		try {
		String contentUser = content.getText();
		int likesUser = Integer.parseInt(likes.getText());
		int sharesUser = Integer.parseInt(shares.getText());
		LocalDateTime dateandtimeUser = LocalDateTime.parse(dateAndTime.getText(),formatter);
		if(contentUser.isEmpty() || likes.getText().isEmpty()||shares.getText().isEmpty()||dateAndTime.getText().isEmpty()) 
		{throw new emptyFieldException();}
		boolean addedPost = dbutilityposts.addPosttoDB(userfromDashboard, new posts(userfromDashboard.getUsername(),contentUser, likesUser, sharesUser, dateandtimeUser));
		if(addedPost) {alerts.postaddedAlert();setEmptyFields();}
		else {alerts.posterrorAlert();setEmptyFields();}
	}catch(NumberFormatException e) {
		e.printStackTrace();alerts.invalidlikesSharesrrorAlert();;}
	catch(DateTimeParseException e ){
		e.printStackTrace();alerts.datetimeerrorAlert();
	}catch(SQLException e) {e.printStackTrace();}
	catch(emptyFieldException e ) {e.printStackTrace();alerts.emptyFieldsAlert();}
}
	

	@FXML
	private void movetoDashboard(ActionEvent event) {
		dashboardScene dashboardScene = new dashboardScene(primaryStage);
		primaryStage.setTitle(dashboardScene.getTitle());
		primaryStage.setScene(dashboardScene.getScene(userfromDashboard));
		primaryStage.show();
	}
	}

