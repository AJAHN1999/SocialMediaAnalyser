package View;

import java.io.IOException;

import Controller.loginPageController;
import Controller.registrationController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class registrationPageScene {
	
	private Stage primaryStage;
	
	private Scene scene;
	
	public registrationPageScene(Stage primaryStage) {
		this.primaryStage = primaryStage;
		scene = null;
	}
	

	public String getTitle() {
		return "Please Register!";
	}
	
	public Scene getScene() {
		
		if(scene != null) {
			return scene;
		}
		
		// load FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource("registrationPage.fxml"));
				
		// load the FXML
		Parent parentNode = null;
		try {
			parentNode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		registrationController controller = loader.getController();
		controller.setPrimaryStage(primaryStage);

		
		// create a scene
		Scene scene = new Scene(parentNode);
		
		return scene;
		
	}
	
}