package View;

import java.io.IOException;

import Controller.VIPController;
import Controller.retrievePostController;
import Model.users;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VIPScene {
	private Stage primaryStage;
	
	private Scene scene;
	
	public VIPScene(Stage primaryStage) {
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("VIPpage.fxml"));

		// load the FXML
		Parent parentNode = null;
		try {
			parentNode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		VIPController Vcontroller = loader.getController();
		Vcontroller.senduser(user);
		Vcontroller.setPrimaryStage(primaryStage);
		// create a scene
		Scene scene = new Scene(parentNode);
		
		return scene;
		
	}
}
