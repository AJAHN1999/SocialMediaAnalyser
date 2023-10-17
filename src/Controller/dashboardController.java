package Controller;


import Model.users;
import View.addPostScene;
import View.loginPageScene;
import View.retrievePostScene;
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

	private users user;

	@FXML
	private void initialize() {

	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}


	public void setwelcomeLabel(users userfromLogin) {
		welcomeLabel.setText(String.format("welcome %s "+ "%s!", userfromLogin.getFirstname(),userfromLogin.getLastname()));
		user = userfromLogin;
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
		primaryStage.setScene(profileupdateScene.getScene(user));
		primaryStage.show();
	}
	
	@FXML
	private void movetoAddPost(ActionEvent event) {
		addPostScene postScene = new addPostScene(primaryStage);
		primaryStage.setTitle(postScene.getTitle());
		primaryStage.setScene(postScene.getScene(user));
		primaryStage.show();
	}

	@FXML
	private void movetoRetrievePost(ActionEvent event) {
		retrievePostScene retrieveScene = new retrievePostScene(primaryStage);
		primaryStage.setTitle(retrieveScene.getTitle());
		primaryStage.setScene(retrieveScene.getScene(user));
		primaryStage.show();
	}
}