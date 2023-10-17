package View;

import java.io.IOException;

import Controller.addPostController;
import Controller.retrievePostController;
import Model.users;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class retrievePostScene {
	
	private Stage primaryStage;
	
	private Scene scene;
	
	public retrievePostScene(Stage primaryStage) {
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("retrievePost.fxml"));
				
		// load the FXML
		Parent parentNode = null;
		try {
			parentNode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		retrievePostController controller = loader.getController();
		controller.setPrimaryStage(primaryStage);
		controller.senduser(user);
		// create a scene
		Scene scene = new Scene(parentNode);
		
		return scene;
		
	}
	
}