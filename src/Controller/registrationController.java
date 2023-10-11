package Controller;

import java.sql.SQLException;
import Database.dbutility;
import View.loginPageScene;
import customExceptions.emptyFieldException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import alerts.alerts;

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
	public void movetoLogin(ActionEvent event) {
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
				throw new emptyFieldException();
			}
			else {
				result = dbutility.addUser(userName, firstname, lastname, password);
				if (result == true){
					alerts.showUserAddedAlert();
					//change scene to dashboard
					movetoLoginPage();}
			}
		}catch(emptyFieldException e) {
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

