package Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import Database.dbutility;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class loginPageController {
	
	private Stage primaryStage;
	
	@FXML
	private TextField username;
	
	@FXML
	private PasswordField password;
	
	@FXML
	private Button loginButton;
	
	@FXML
	private void initialize() {
	
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@FXML
	public void loginUser(ActionEvent event) {
		
		// Retrieve entered username and password
        String userName = username.getText();
        String Password = password.getText();
        System.out.println(userName);
        System.out.println(Password);
		
		if(dbutility.authenticate(userName, Password)) {
			// login successful in the login page
			System.out.println("reached successful");
			SuccesfullloginAlert();
			// Move to next scene(dashboard)
			
			
		}
		else {
			// login unsuccessful in login page
			UnsuccessfulAlert();
			//try again
			username.setText("");
			password.setText("");
			System.out.println("accessed to unsucessful alert");
			//move to register page!
			
		}
	}
		
	public static void SuccesfullloginAlert() {
		Alert successAlert = new Alert(AlertType.INFORMATION);
		successAlert.setTitle("Login Successful");
		successAlert.setHeaderText(null);
		successAlert.setContentText("Welcome to the dashboard!");
		successAlert.showAndWait();
	}
	
	public static void UnsuccessfulAlert() {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setTitle("Login Failed");
		errorAlert.setHeaderText(null);
		errorAlert.setContentText("Invalid credentials. Please try again.");
		errorAlert.showAndWait();
	}
	

//	@FXML
//	public void switchSceneHandler(ActionEvent event) {
//		
//		SecondScene secondScene = new SecondScene(primaryStage);
//		primaryStage.setTitle(secondScene.getTitle());
//		primaryStage.setScene(secondScene.getScene());
//
//		primaryStage.show();
//		
//	}
	
}