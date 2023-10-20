package Controller;

import java.sql.SQLException;


import Database.DatabaseUtility;
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
import customExceptions.EmptyFieldException;

public class LoginPageController {

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
	private void loginUser(ActionEvent event) throws SQLException {

		// Retrieve entered username and password
		try {
			String userName = username.getText();
			String Password = password.getText();
			if(userName.isEmpty()||Password.isEmpty()) {       	
				throw new EmptyFieldException();
			}
			System.out.println(userName);
			System.out.println(Password);
			users user = DatabaseUtility.authenticate(userName, Password);
			if(user!=null) {

				movetoDashboard(user);
				alerts.SuccesfullloginAlert();

			}
			else {alerts.UnsuccessfulAlert();setFields();}     

		} catch(EmptyFieldException e) {
			alerts.emptyFieldsAlert();setFields();}
	}


	@FXML
	public void setRegScene(ActionEvent event) {
		registrationPageScene registrationScene = new registrationPageScene(primaryStage);
		primaryStage.setTitle(registrationScene.getTitle());
		primaryStage.setScene(registrationScene.getScene());

		primaryStage.show();
	}

	/*User object upon login is sent to the next scene*/
	public void movetoDashboard(users user) {
		dashboardScene dashboardScene = new dashboardScene(primaryStage);
		primaryStage.setTitle(dashboardScene.getTitle());
		primaryStage.setScene(dashboardScene.getScene(user));

		primaryStage.show();
	}

	private void setFields() {//sets fields back to empty when called.
		username.setText("");
		password.setText("");
	}




}


