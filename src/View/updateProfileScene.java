package View;


import java.io.IOException;
import Controller.updateprofileController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class updateProfileScene {
	
	private Stage primaryStage;
	
	private Scene scene;
	
	public updateProfileScene(Stage primaryStage) {
		this.primaryStage = primaryStage;
		scene = null;
	}
	

	public String getTitle() {
		return "Update Details here";
	}
	
	public Scene getScene(String username) {
		
		if(scene != null) {
			return scene;
		}
		
		// load FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource("updateProfile.fxml"));
				
		// load the FXML
		Parent parentNode = null;
		try {
			parentNode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		updateprofileController controller = loader.getController();
		controller.setPrimaryStage(primaryStage);
		controller.sendusername(username);
		
		// create a scene
		Scene scene = new Scene(parentNode);
		
		return scene;
		
	}
	
}