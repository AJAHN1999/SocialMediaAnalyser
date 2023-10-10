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


public class registrationController {
	
	private Stage primaryStage;
	
	@FXML
	private TextField UsernameRegistration;
	
	@FXML
	private TextField firstName;
	
	@FXML
	private TextField lastName;
	
	@FXML
	private TextField passwordR;
	
	
		

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	private void setEmptyFields() {
		UsernameRegistration.setText("");
		firstName.setText("");
		lastName.setText("");
		passwordR.setText("");
	}
		
	
    
    @FXML 
    public void addtoDatabase(ActionEvent event) throws SQLException {
    	// Retrieve entered username, password, firstname and lastname
    	boolean result;
    	try {
        String userName = UsernameRegistration.getText();
        String firstname = firstName.getText();
        String lastname = lastName.getText();
        String password = passwordR.getText();
        
        if(userName.isEmpty()||firstname.isEmpty()||lastname.isEmpty()||password.isEmpty()) {
        	throw new emptyFieldException();
        }
        else {
        	 result = dbutility.addUser(userName, firstname, lastname, password);
        	 if (result == true){
         		showUserAddedAlert();
         		//change scene to dashboard
         		movetoLoginPage();}
        }
    	}catch(emptyFieldException e) {
    		result = false;
    		emptyFieldsAlert();
    	} catch (customExceptions.UsernameExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 UserExistsAlert();

			 setEmptyFields();
		}
    	finally {
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
}

		