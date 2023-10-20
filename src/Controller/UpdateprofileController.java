package Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.SQLException;

import Database.DatabaseUtility;
import Model.users;
import View.dashboardScene;
import View.loginPageScene;
import View.registrationPageScene;
import customExceptions.UsernameExistsException;
import customExceptions.EmptyFieldException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import alerts.alerts;

public class UpdateprofileController {

	private Stage primaryStage;

	@FXML
	private TextField usernameUF;

	@FXML
	private TextField firstnameUF;

	@FXML
	private TextField lastnameUF;

	@FXML
	private PasswordField passwordUF;

	private users usernamefromDashboard;

	//private String newName;//name if changes have been made to the update username successfully


	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	private void setEmptyFields() {
		usernameUF.setText("");
		firstnameUF.setText("");
		lastnameUF.setText("");
		passwordUF.setText("");
	}


	public void  senduser(users user) {
		usernamefromDashboard = user;	
		usernameUF.setText(usernamefromDashboard.getUsername());
		firstnameUF.setText(usernamefromDashboard.getFirstname());
		lastnameUF.setText(usernamefromDashboard.getLastname());
	}

	@FXML 
	public void updateUser(ActionEvent event) {
		// Retrieve entered username, password, firstname and lastname
		//boolean result;

		try {
			String userName = usernameUF.getText();
			String firstname = firstnameUF.getText();
			String lastname = lastnameUF.getText();
			String password = passwordUF.getText();

			if(userName.isEmpty()||firstname.isEmpty()||lastname.isEmpty()||password.isEmpty()) {
				throw new EmptyFieldException();
			}
			else {

				users userafterUpdate = DatabaseUtility.updateUser(usernamefromDashboard,userName,firstname,lastname,password);
				if (userafterUpdate!= null){
					alerts.userUpdatedAlert();
					//change scene to dashboard
					usernamefromDashboard = userafterUpdate;
					movetoDashboardPage();
				}
				else {
					alerts.UserExistsAlert();
				}
				// if result is true then showupdate alert and move back to dashboard.
			}
		}catch(EmptyFieldException e) {
			//result = false;
			alerts.emptyFieldsAlert();
		} catch (UsernameExistsException e) {
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
		primaryStage.setScene(dashboardScene.getScene(usernamefromDashboard));

		primaryStage.show();
	}


}
