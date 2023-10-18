package Controller;

import java.sql.SQLException;

import Database.dbutility;
import Model.users;
import View.dashboardScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class VIPController {

	
	private Stage primaryStage;
	
	private users userfromDashboard;
	
	public void senduser(users user) {userfromDashboard = user;}
	

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@FXML
	public void yesVIP(ActionEvent event) {
		try {
			dbutility.updateUser(userfromDashboard);
			alerts.alerts.VIPuserAlert();
			movetoDashboardPage();
			System.out.println("now a VIP user");
		}catch(SQLException e ) {e.printStackTrace();}
	}
	
	@FXML
	public void noVIP(ActionEvent event) {
		movetoDashboardPage();
	}
	public void movetoDashboardPage() {
	dashboardScene dashboardScene = new dashboardScene(primaryStage);
	primaryStage.setTitle(dashboardScene.getTitle());
	primaryStage.setScene(dashboardScene.getScene(userfromDashboard));
	primaryStage.show();
	}
}
