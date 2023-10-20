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

	public static void invalidInputError() {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setTitle("Invalid Input Provided");
		errorAlert.setHeaderText(null);
		errorAlert.setContentText("You may have inputted a string.");
		errorAlert.showAndWait();
	}

	public static void postdeletedAlert(int postId) {
		Alert errorAlert = new Alert(AlertType.INFORMATION);
		errorAlert.setTitle("Post Deleted");
		errorAlert.setHeaderText(null);
		errorAlert.setContentText(String.format("The post %d has been deleted successfully ",postId));
		errorAlert.showAndWait();
	}
	public static void VIPuserAlert() {
		Alert errorAlert = new Alert(AlertType.INFORMATION);
		errorAlert.setTitle("New VIP");
		errorAlert.setHeaderText(null);
		errorAlert.setContentText(String.format("You are now a VIP. Please logout and login to gain access to VIP features!"));
		errorAlert.showAndWait();
	}
	public static void bulkImportSuccessfullAlert() {
		Alert errorAlert = new Alert(AlertType.INFORMATION);
		errorAlert.setTitle("BulkImport Window");
		errorAlert.setHeaderText(null);
		errorAlert.setContentText(String.format("Bulk import has been accomplished successfully"));
		errorAlert.showAndWait();
	}
	public static void bulkImportFailedAlert() {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setTitle("BulkImport Window");
		errorAlert.setHeaderText(null);
		errorAlert.setContentText(String.format("Bulk import failed, please check file type"));
		errorAlert.showAndWait();
	}
	
	public static void postDoesntExistAlert() {
		Alert errorAlert = new Alert(AlertType.INFORMATION);
		errorAlert.setTitle("PostId Invalid");
		errorAlert.setHeaderText(null);
		errorAlert.setContentText(String.format("The post you are trying to export does't exist."));
		errorAlert.showAndWait();
	}
	
	public static void CannotDeleteAlert() {
		Alert errorAlert = new Alert(AlertType.INFORMATION);
		errorAlert.setTitle("Invalid Option");
		errorAlert.setHeaderText(null);
		errorAlert.setContentText(String.format("You cannot delete the post because it does't belong to you."));
		errorAlert.showAndWait();
	}
	
	public static void successfullExportAlert() {
		Alert errorAlert = new Alert(AlertType.INFORMATION);
		errorAlert.setTitle("Export Successfull");
		errorAlert.setHeaderText(null);
		errorAlert.setContentText(String.format("Post has been exported successfully."));
		errorAlert.showAndWait();
	}
}



