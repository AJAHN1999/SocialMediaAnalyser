package View;

import java.io.IOException;

import Controller.DashboardController;
import Controller.LoginPageController;
import Controller.UpdateprofileController;
import Model.users;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class dashboardScene {

	private Stage primaryStage;

	private Scene scene;

	public dashboardScene(Stage primaryStage) {
		this.primaryStage = primaryStage;
		scene = null;
	}


	public String getTitle() {
		return "Welcome to your dashboard";
	}

	public Scene getScene(users user) {

		if(scene != null) {
			return scene;
		}

		// load FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));

		// load the FXML
		Parent parentNode = null;
		try {
			parentNode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		DashboardController controller = loader.getController();
		controller.setPrimaryStage(primaryStage);
		controller.setwelcomeLabel(user);
		if(user.getIsVIP()==1) {
			controller.setDataVbutton(true);
			controller.setbulkImportButton(true);
			controller.setUpgradetoVIP(false);}

		// create a scene
		Scene scene = new Scene(parentNode);

		return scene;

	}

}