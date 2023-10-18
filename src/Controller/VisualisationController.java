package Controller;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Database.dbutilityposts;
import Model.posts;
import Model.users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;


public class VisualisationController {

	private Stage primaryStage;
	
	//private users userfromDashboard;
	
	@FXML
	PieChart sharesPieChart; 
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	//public void senduser(users user) {userfromDashboard = user;}
	
	//public  sharesCategeories = new HashMap<String, Integer>();
	
	
	@FXML
	public void initialize(users user) {
		HashMap<String,Integer> sharesCategories = shares(user);
		ObservableList<PieChart.Data> pieChartData =FXCollections.observableArrayList();
		for (Map.Entry<String, Integer> entry : sharesCategories.entrySet()) {
		    String category = entry.getKey();
		    int count = entry.getValue();
		    PieChart.Data data = new PieChart.Data(category, count);
		    pieChartData.add(data);
		}
		sharesPieChart.setData(pieChartData);

	}

	
	public HashMap<String,Integer> shares(users user){
		try {
		HashMap<String,Integer> categories = new HashMap<String,Integer>();
		int countZerotoNintynine=0;
		int countHuntoNintynine=0;
		int countThousand=0;
		ArrayList<posts> posts = dbutilityposts.retrieveNpostsFromDB(user);	
		for (posts everypost: posts) {
			if(everypost.getShares()>=0 && everypost.getShares()<=99) {
				countZerotoNintynine++;
			}
			else if (everypost.getShares()>=100 && everypost.getShares()<=999) {
				countHuntoNintynine++;
			}
			else if (everypost.getShares()>=1000) {
				countThousand++;
			}
		}
	categories.put("0-99 shares",countZerotoNintynine);
	categories.put("100-999 shares", countHuntoNintynine);
	categories.put("1000+ shares", countThousand);
	return categories;
	}catch(SQLException e) {e.printStackTrace();return null;}

}
}

	

