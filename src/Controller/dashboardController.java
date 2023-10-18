package Controller;


import java.io.File;
import java.io.IOException;

import Database.FileReader3000;
import Model.users;
import View.DataVisualisationScene;
import View.TestScene;
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


	public void setwelcomeLabel(users userfromLogin) {
		welcomeLabel.setText(String.format("welcome %s "+ "%s!", userfromLogin.getFirstname(),userfromLogin.getLastname()));
		user = userfromLogin;
	}
	
	public void setDataVbutton(boolean status) {
		dataV.setVisible(status);
	}
	
	public void setbulkImportButton(boolean status) {
		bulkImport.setVisible(status);
	}
	
	public void setUpgradetoVIP(boolean status) {upgradetoVIP.setVisible(status);}

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
	
	@FXML
	private void movetoVIP(ActionEvent event) {
		VIPScene VIPScene = new VIPScene(primaryStage);
		primaryStage.setTitle(VIPScene.getTitle());
		primaryStage.setScene(VIPScene.getScene(user));
		primaryStage.show();
	}
	
	@FXML
	private void bulkImportCSV(ActionEvent event) {
		FileChooser filechooser = new FileChooser();
		filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
		File selectedFile = filechooser.showOpenDialog(new Stage());
		
		if(selectedFile != null) {
			try {
			FileReader3000 fileReader = new FileReader3000(user, selectedFile);
			fileReader.readFile();
		}catch(IOException e) {e.printStackTrace();alerts.bulkImportFailedAlert();}
		}
	}
		
	@FXML
	public void movetoDataVisualisation(ActionEvent event) {
		DataVisualisationScene DataScene = new DataVisualisationScene(primaryStage);
		primaryStage.setTitle(DataScene.getTitle());
		primaryStage.setScene(DataScene.getScene(user));
		primaryStage.show();
	}
	
	
	
	
}