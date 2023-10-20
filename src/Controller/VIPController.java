package Controller;

import java.sql.SQLException;

import Database.DatabaseUtility;
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
	public void yesVIP(ActionEvent event) {//handles the Yes button in the prompt box 
		try {
			DatabaseUtility.updateUser(userfromDashboard);//changes the user's status to VIP in database.
			alerts.alerts.VIPuserAlert();
			movetoDashboardPage();//takes user back to dashboard after making him a VIP user.
			System.out.println("now a VIP user");
		}catch(SQLException e ) {e.printStackTrace();}
	}

	@FXML
	public void noVIP(ActionEvent event) {// takes the user back to dashboard.
		movetoDashboardPage();
	}
	public void movetoDashboardPage() {
		dashboardScene dashboardScene = new dashboardScene(primaryStage);
		primaryStage.setTitle(dashboardScene.getTitle());
		primaryStage.setScene(dashboardScene.getScene(userfromDashboard));
		primaryStage.show();
	}
}
