package Controller;

import java.sql.SQLException;

import Database.dbutility;
import View.dashboardScene;
import View.registrationPageScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import alerts.alerts;

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
	public void loginUser(ActionEvent event) throws SQLException {
		
		// Retrieve entered username and password
        String userName = username.getText();
        String Password = password.getText();
        System.out.println(userName);
        System.out.println(Password);
		if(dbutility.authenticate(userName, Password) && !(userName.isEmpty()|| Password.isEmpty())) {
			// login successful in the login page
			System.out.println("reached successful");
			alerts.SuccesfullloginAlert();
			// Move to next scene(dashboard)
			movetoDashboard(userName);
			
		}
		else {
			// login unsuccessful in login page
			alerts.UnsuccessfulAlert();
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
	
	
	
	
	
	
}


