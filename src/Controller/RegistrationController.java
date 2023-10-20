package Controller;

import java.sql.SQLException;
import Database.DatabaseUtility;
import Model.users;
import View.loginPageScene;
import customExceptions.UsernameExistsException;
import customExceptions.EmptyFieldException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import alerts.alerts;

public class RegistrationController {

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

	private void setEmptyFields() {//sets fields to empty when called
		UsernameRegistration.setText("");
		firstName.setText("");
		lastName.setText("");
		passwordR.setText("");
	}

	@FXML
	public void movetoLogin(ActionEvent event) {//logout  button action event which takes you back to loginpage
		movetoLoginPage();
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
				throw new EmptyFieldException();
			}
			else {
				users user = new users(userName,firstname,lastname,password);
				result = DatabaseUtility.addUser(user);
				if (result == true){
					alerts.showUserAddedAlert();
					//change scene to dashboard
					movetoLoginPage();}//move to login once registered.
				else {throw new UsernameExistsException();}
			}
		}catch(EmptyFieldException e) {
			result = false;
			alerts.emptyFieldsAlert();
		} catch (customExceptions.UsernameExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			alerts.UserExistsAlert();

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


}

