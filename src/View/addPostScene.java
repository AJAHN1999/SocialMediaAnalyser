package View;

import java.io.IOException;

import Controller.addPostController;
import Model.users;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class addPostScene {
	
	private Stage primaryStage;
	
	private Scene scene;
	
	public addPostScene(Stage primaryStage) {
		this.primaryStage = primaryStage;
		scene = null;
	}
	

	public String getTitle() {
		return "Add Posts here";
	}
	
	public Scene getScene(users user) {
		
		if(scene != null) {
			return scene;
		}
		
		// load FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource("addPost.fxml"));
				
		// load the FXML
		Parent parentNode = null;
		try {
			parentNode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		addPostController controller = loader.getController();
		controller.setPrimaryStage(primaryStage);
		controller.senduser(user);
		// create a scene
		Scene scene = new Scene(parentNode);
		
		return scene;
		
	}
	
}