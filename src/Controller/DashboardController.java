package Controller;


import java.io.File;
import java.io.IOException;

import Database.FileImporter;
import Model.users;
import View.DataVisualisationScene;
import View.VIPScene;
import View.addPostScene;
import View.loginPageScene;
import View.retrievePostScene;
import View.updateProfileScene;
import alerts.alerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class DashboardController {

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

	@FXML
	private Button upgradetoVIP;

	@FXML
	private Button dataV;

	@FXML
	private Button bulkImport;

	private users user;

	@FXML
	private void initialize() {

	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}


	public void setwelcomeLabel(users userfromLogin) {//gets user from login page and sets welcomeLabel according to first and last name
		welcomeLabel.setText(String.format("welcome %s "+ "%s!", userfromLogin.getFirstname(),userfromLogin.getLastname()));
		user = userfromLogin; // assigns the user to the user object inside the class.
	}

	public void setDataVbutton(boolean status) { // sets dataVisualisation button to true upon VIP status
		dataV.setVisible(status);
	}

	public void setbulkImportButton(boolean status) { // sets bulk import button to visible  upon VIP status=1 
		bulkImport.setVisible(status);
	}

	public void setUpgradetoVIP(boolean status) {upgradetoVIP.setVisible(status);} //sets visibility to false upon VIP status.

	@FXML 
	private void setLoginPage(ActionEvent event) {	//back button which logs you out 
		loginPageScene loginScene = new loginPageScene(primaryStage);
		primaryStage.setTitle(loginScene.getTitle());
		primaryStage.setScene(loginScene.getScene());

		primaryStage.show();
	}

	@FXML
	private void setProfile(ActionEvent event) {	//moves to update profile page
		updateProfileScene profileupdateScene = new updateProfileScene(primaryStage);
		primaryStage.setTitle(profileupdateScene.getTitle());
		primaryStage.setScene(profileupdateScene.getScene(user));
		primaryStage.show();
	}

	@FXML
	private void movetoAddPost(ActionEvent event) {	//moves to addpost functionality
		addPostScene postScene = new addPostScene(primaryStage);
		primaryStage.setTitle(postScene.getTitle());
		primaryStage.setScene(postScene.getScene(user));
		primaryStage.show();
	}

	@FXML
	private void movetoRetrievePost(ActionEvent event) {	//moves to retrieve post functionality
		retrievePostScene retrieveScene = new retrievePostScene(primaryStage);
		primaryStage.setTitle(retrieveScene.getTitle());
		primaryStage.setScene(retrieveScene.getScene(user));
		primaryStage.show();


	}

	@FXML
	private void movetoVIP(ActionEvent event) {	//this action event triggers the VIP scene promting the user to confirm VIP subsrciption.
		VIPScene VIPScene = new VIPScene(primaryStage);
		primaryStage.setTitle(VIPScene.getTitle());
		primaryStage.setScene(VIPScene.getScene(user));
		primaryStage.show();
	}

	@FXML
	private void bulkImportCSV(ActionEvent event) {	//bulk import event which allows you to import files.
		FileChooser filechooser = new FileChooser();
		filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
		File selectedFile = filechooser.showOpenDialog(new Stage());

		if(selectedFile != null) {
			try {
				FileImporter fileReader = new FileImporter(user, selectedFile);
				fileReader.readFile();
			}catch(IOException e) {e.printStackTrace();alerts.bulkImportFailedAlert();}
		}
	}

	@FXML
	private void movetoDataVisualisation(ActionEvent event) {	//data visualisation button click triggers the pie chart in the next scene
		DataVisualisationScene DataScene = new DataVisualisationScene(primaryStage);
		primaryStage.setTitle(DataScene.getTitle());
		primaryStage.setScene(DataScene.getScene(user));
		primaryStage.show();
	}




}