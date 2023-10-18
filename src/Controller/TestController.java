package Controller;

import java.awt.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TestController {

	private Stage primaryStage;
	
	@FXML
	private Button ajahnButton;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@FXML
	public void ajahnButtonOnClick(ActionEvent event) {
		
	}
	
//	@FXML
//	public void ajahnButton(ActionEvent event) {
//		System.out.println("Ajahn Pressed");
//	}
	
	
}
