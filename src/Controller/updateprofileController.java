package Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.SQLException;

import Database.dbutility;
import View.dashboardScene;
import View.loginPageScene;
import View.registrationPageScene;
import customExceptions.emptyFieldException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import alerts.alerts;

public class updateprofileController {

	private Stage primaryStage;

	@FXML
	private TextField usernameUF;

	@FXML
	private TextField firstnameUF;

	@FXML
	private TextField lastnameUF;

	@FXML
	private TextField passwordUF;

	private String loggedinName;

	private String newName;//name if changes have been made to the update username successfully


	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	private void setEmptyFields() {
		usernameUF.setText("");
		firstnameUF.setText("");
		lastnameUF.setText("");
		passwordUF.setText("");
	}


	public void  sendusername(String username) {
		loggedinName = username;	
	}

	@FXML 
	public void updateUser(ActionEvent event) {
		// Retrieve entered username, password, firstname and lastname
		boolean result;
		try {
			String userName = usernameUF.getText();
			String firstname = firstnameUF.getText();
			String lastname = lastnameUF.getText();
			String password = passwordUF.getText();

			if(userName.isEmpty()||firstname.isEmpty()||lastname.isEmpty()||password.isEmpty()) {
				throw new emptyFieldException();
			}
			else {
				result = dbutility.updateUser(loggedinName, userName, firstname, lastname, password);
				if (result == true){
					newName = userName;
					alerts.userUpdatedAlert();
					//change scene to dashboard
					movetoDashboardPage();
				}
				else {
					alerts.UserExistsAlert();
				}
				// if result is true then showupdate alert and move back to dashboard.
			}
		}catch(emptyFieldException e) {
			result = false;
			alerts.emptyFieldsAlert();
			newName = loggedinName;
		} catch (customExceptions.UsernameExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			alerts.UserExistsAlert();

			setEmptyFields();
		}catch(SQLException e ) {
			e.printStackTrace();
			setEmptyFields();
		}
	}


	@FXML
	public void movetoDashboardPage() {
		dashboardScene dashboardScene = new dashboardScene(primaryStage);
		primaryStage.setTitle(dashboardScene.getTitle());
		primaryStage.setScene(dashboardScene.getScene(newName));

		primaryStage.show();
	}


}
