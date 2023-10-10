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
					userUpdatedAlert();
					//change scene to dashboard
					//movetoDashboardPage();
				}
				else {
					UserExistsAlert();
				}
				// if result is true then showupdate alert and move back to dashboard.
			}
		}catch(emptyFieldException e) {
			result = false;
			emptyFieldsAlert();
		} catch (customExceptions.UsernameExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			UserExistsAlert();
			//      		 UsernameRegistration.setText("");
			//  			 firstName.setText("");
			//  			 lastName.setText("");
			//  			 passwordR.setText("");
			setEmptyFields();
		}catch(SQLException e ) {
			e.printStackTrace();
			//			UsernameRegistration.setText("");
			// 			firstName.setText("");
			// 			lastName.setText("");
			// 			passwordR.setText("");
			setEmptyFields();
		}
	}

	public void movetoLoginPage() {
		loginPageScene loginScene = new loginPageScene(primaryStage);
		primaryStage.setTitle(loginScene.getTitle());
		primaryStage.setScene(loginScene.getScene());

		primaryStage.show();
	}

	public static void showUserAddedAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("User Added");
		alert.setHeaderText(null);
		alert.setContentText("User has been successfully added to the database.");
		alert.showAndWait();
	}

	public static void UserExistsAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("User Already Exists");
		alert.setHeaderText(null);
		alert.setContentText("A user with the same username already exists.");
		alert.showAndWait();
	}
	public static void emptyFieldsAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Empty Fields Present");
		alert.setHeaderText(null);
		alert.setContentText("You may not have entered one or many fields");
		alert.showAndWait();
	}
	
	public static void userUpdatedAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("User Updated");
		alert.setHeaderText(null);
		alert.setContentText("Changes have been made successfully");
		alert.showAndWait();
}
}
