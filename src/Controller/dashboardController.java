package Controller;


import View.loginPageScene;
import View.updateProfileScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.stage.Stage;


public class dashboardController {

	private Stage primaryStage;

	@FXML
	private Label welcomeLabel;

	@FXML
	private Button editProfile;

	@FXML
	private Button addpost;

	@FXML
	private Button retrievepost;

	@FXML
	private Button logout;

	private String username;

	@FXML
	private void initialize() {

	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}


	public void setwelcomeLabel(String username) {
		this.username = username;
		welcomeLabel.setText(String.format("welcome %s!", username));
	}

	@FXML 
	private void setLoginPage(ActionEvent event) {
		loginPageScene loginScene = new loginPageScene(primaryStage);
		primaryStage.setTitle(loginScene.getTitle());
		primaryStage.setScene(loginScene.getScene());

		primaryStage.show();
	}

	@FXML
	private void setProfile(ActionEvent event) {
		updateProfileScene profileupdateScene = new updateProfileScene(primaryStage);
		primaryStage.setTitle(profileupdateScene.getTitle());
		primaryStage.setScene(profileupdateScene.getScene(username));
		primaryStage.show();
	}

}