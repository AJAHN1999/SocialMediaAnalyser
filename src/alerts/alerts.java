package alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class alerts {

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

	public static void postaddedAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Post Added");
		alert.setHeaderText(null);
		alert.setContentText("Post has been added successfully");
		alert.showAndWait();
	}
	
	public static void posterrorAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error:Post not added");
		alert.setHeaderText(null);
		alert.setContentText("Post addition failed");
		alert.showAndWait();
	}
	
	public static void datetimeerrorAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error:Invalid DateTime");
		alert.setHeaderText(null);
		alert.setContentText("Invalid date-time provided, please enter d/MM/YYYY HH:MM");
		alert.showAndWait();
	}
	
	public static void invalidlikesSharesrrorAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error:Invalid Likes or Shares");
		alert.setHeaderText(null);
		alert.setContentText("Invalid likes/shares type provided, please enter a number");
		alert.showAndWait();
	}


}
