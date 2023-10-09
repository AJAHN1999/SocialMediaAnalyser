package View;

import java.io.IOException;

import Controller.loginPageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class loginPageScene {
	
	private Stage primaryStage;
	
	private Scene scene;
	
	public loginPageScene(Stage primaryStage) {
		this.primaryStage = primaryStage;
		scene = null;
	}
	

	public String getTitle() {
		return "Welcome to Data Analytics Hub";
	}
	
	public Scene getScene() {
		
		if(scene != null) {
			return scene;
		}
		
		// load FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource("loginPage.fxml"));
				
		// load the FXML
		Parent parentNode = null;
		try {
			parentNode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		loginPageController controller = loader.getController();
		controller.setPrimaryStage(primaryStage);

		
		// create a scene
		Scene scene = new Scene(parentNode);
		
		return scene;
		
	}
	
}
