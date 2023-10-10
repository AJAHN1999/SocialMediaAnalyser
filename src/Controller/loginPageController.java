package Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import Database.dbutility;
import View.dashboardScene;
import View.registrationPageScene;
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
	private Button registerButton;
	
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
		if(dbutility.authenticate(userName, Password) && !(userName.isEmpty()|| Password.isEmpty())) {
			// login successful in the login page
			System.out.println("reached successful");
			SuccesfullloginAlert();
			// Move to next scene(dashboard)
			movetoDashboard(userName);
			
		}
		else {
			// login unsuccessful in login page
			UnsuccessfulAlert();
			//try again
			username.setText("");
			password.setText("");
		}
	}
	
	@FXML
	public void setRegScene(ActionEvent event) {
		System.out.println("reached setReg");
		registrationPageScene registrationScene = new registrationPageScene(primaryStage);
		primaryStage.setTitle(registrationScene.getTitle());
		primaryStage.setScene(registrationScene.getScene());
		
		primaryStage.show();
	}
	
	public void movetoDashboard(String username) {
		dashboardScene dashboardScene = new dashboardScene(primaryStage);
		primaryStage.setTitle(dashboardScene.getTitle());
		primaryStage.setScene(dashboardScene.getScene(username));
		
		primaryStage.show();
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
	
	
}


