package View;

import java.io.IOException;

import Controller.TestController;
import Controller.VIPController;
import Model.users;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TestScene {
	private Stage primaryStage;
	
	private Scene scene;
	
	public TestScene(Stage primaryStage) {
		this.primaryStage = primaryStage;
		scene = null;
	}
	

	public String getTitle() {
		return "Retrieve Posts here";
	}
	
	public Scene getScene(users user) {
		
		if(scene != null) {
			return scene;
		}
		
		// load FXML
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("test.fxml"));

		// load the FXML
		Parent parentNode = null;
		try {
			parentNode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		TestController controller = loader.getController();
//		controller.senduser(user);
		controller.setPrimaryStage(primaryStage);
		// create a scene
		Scene scene = new Scene(parentNode);
		
		return scene;
		
	}
}
