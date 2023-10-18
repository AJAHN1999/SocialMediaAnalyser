package View;

import java.io.IOException;

import Controller.VisualisationController;
import Model.users;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DataVisualisationScene {
		
		private Stage primaryStage;
		
		private Scene scene;
		
		public DataVisualisationScene(Stage primaryStage) {
			this.primaryStage = primaryStage;
			scene = null;
		}
		

		public String getTitle() {
			return "Welcome to Data Visualisation";
		}
		
		public Scene getScene(users user) {
			
			if(scene != null) {
				return scene;
			}
			
			// load FXML
			FXMLLoader loader = new FXMLLoader(getClass().getResource("dataVisualisation.fxml"));
					
			// load the FXML
			Parent parentNode = null;
			try {
				parentNode = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			VisualisationController controller = loader.getController();
			controller.setPrimaryStage(primaryStage);
			controller.initialize(user);
			
			// create a scene
			Scene scene = new Scene(parentNode);
			
			return scene;
			
		}
		
	}

