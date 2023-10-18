package Controller;

import java.sql.SQLException;

import Database.dbutility;
import Model.users;
import View.dashboardScene;
import View.registrationPageScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import alerts.alerts;
import customExceptions.emptyFieldException;

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
		try {
        String userName = username.getText();
        String Password = password.getText();
        if(userName.isEmpty()||Password.isEmpty()) {       	
        	throw new emptyFieldException();
        	}
        System.out.println(userName);
        System.out.println(Password);
        users user = dbutility.authenticate(userName, Password);
        if(user!=null) {
        	
        	movetoDashboard(user);
        	alerts.SuccesfullloginAlert();
        
        }
        else {alerts.UnsuccessfulAlert();setFields();}     

	} catch(emptyFieldException e) {
		alerts.emptyFieldsAlert();setFields();}
	}
        
	
	@FXML
	public void setRegScene(ActionEvent event) {
		System.out.println("reached setReg");
		registrationPageScene registrationScene = new registrationPageScene(primaryStage);
		primaryStage.setTitle(registrationScene.getTitle());
		primaryStage.setScene(registrationScene.getScene());
		
		primaryStage.show();
	}
	
	public void movetoDashboard(users user) {
		dashboardScene dashboardScene = new dashboardScene(primaryStage);
		primaryStage.setTitle(dashboardScene.getTitle());
		primaryStage.setScene(dashboardScene.getScene(user));
		
		primaryStage.show();
	}
	
	private void setFields() {
		username.setText("");
		password.setText("");
	}
	
	
	
	
}


