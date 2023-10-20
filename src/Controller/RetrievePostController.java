package Controller;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import java.util.ArrayList;


import Database.DatabaseUtilityPosts;
import Model.posts;
import Model.users;
import View.dashboardScene;
import alerts.alerts;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class RetrievePostController {

	ObservableList<String> comboList = FXCollections.observableArrayList("for users","for all users");
	ObservableList<posts> postList = FXCollections.observableArrayList();

	private Stage primaryStage;

	@FXML
	private TableView<posts> postTableView;

	@FXML
	private TableColumn<posts, Integer> postIDColumn;

	@FXML
	private TableColumn<posts, String> postAuthorColumn;

	@FXML
	private TableColumn<posts, String> postContentColumn;

	@FXML
	private TableColumn<posts, Integer> postLikesColumn;

	@FXML
	private TableColumn<posts, Integer> postSharesColumn;

	@FXML
	private TableColumn<posts, String> dateTimeColumn;

	@FXML
	private TableColumn<posts, Integer> authorIdColumn;

	@FXML
	private TextField postId;

	@FXML
	private Button retrievePost;

	@FXML
	private ComboBox combobox; 

	@FXML
	private TextField Nposts;

	private users userfromDashboard;

	private void setEmptyFields() {
		postId.setText("");
	}


	public void senduser(users user) {userfromDashboard = user;}//gets user object from dashboard

	@FXML
	private void initialize() {
		postIDColumn.setCellValueFactory(new PropertyValueFactory<>("postID"));
		postAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		postContentColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
		postLikesColumn.setCellValueFactory(new PropertyValueFactory<>("likes"));
		postSharesColumn.setCellValueFactory(new PropertyValueFactory<>("shares"));
		dateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
		authorIdColumn.setCellValueFactory(new PropertyValueFactory<>("authorID"));
		combobox.setValue("for users");
		combobox.setItems(comboList);    
	}



	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}


	/*
	 * This method will handle the retrieve button and retrieves the post as entered in the text field
	 * the retrieval is done by using the postId which is converted into integer for the search*/
	@FXML
	public void retrievePost(ActionEvent event) {			
		try {
			int postIdInput = Integer.parseInt(postId.getText());
			posts retrievedPost = DatabaseUtilityPosts.retrievePostFromDB(userfromDashboard,postIdInput);
			postList.clear();
			postList.add(retrievedPost);
			postTableView.setItems(postList);			
		}catch(NumberFormatException e) {alerts.invalidInputError();setEmptyFields();}
		catch(SQLException e) {e.printStackTrace();}
	}

	/*This method will take the input from the text field and delete the post 
	 * This event handler will call the DatabaseUtilit's delete post static method to delete the post 
	 * by using the post Id as the search filter. */
	@FXML
	public void deletePost(ActionEvent event) {
		try {
			int postIdInput = Integer.parseInt(postId.getText());
			boolean deletedPost = DatabaseUtilityPosts.deletePost(postIdInput, userfromDashboard);
			if(deletedPost) {
				alerts.postdeletedAlert(postIdInput);
			}
			else {
				alerts.CannotDeleteAlert();
			}
		}catch(NumberFormatException e) {alerts.emptyFieldsAlert();}
		catch(SQLException e) {e.printStackTrace();}
	}

	/*
	 * This event handler will take in number of posts from the user and uses it */
	@FXML
	private void retrievetopNPosts(ActionEvent event) {
		try {
			int numberofPosts = Integer.parseInt(Nposts.getText());
			String comboBoxValue = combobox.getValue().toString();
			ArrayList<posts> topNposts = DatabaseUtilityPosts.retrieveNpostsFromDB(userfromDashboard, numberofPosts, comboBoxValue);
			postList.clear();
			postList.addAll(topNposts);
			postTableView.setItems(postList);
		}catch(NumberFormatException e) {if(Nposts.getText().isEmpty()) {alerts.emptyFieldsAlert();}else{alerts.invalidInputError();};}
		catch(SQLException e) {e.printStackTrace();}
	}

	@FXML
	private void backtoDashboard(ActionEvent event) {//back button event hadndler that takes you back to the dashboard
		dashboardScene dashboardScene = new dashboardScene(primaryStage);
		primaryStage.setTitle(dashboardScene.getTitle());
		primaryStage.setScene(dashboardScene.getScene(userfromDashboard));
		primaryStage.show();
	}

	@FXML
	private void exportPost(ActionEvent event) {//handles the export button
		try {
			int postIdInput = Integer.parseInt(postId.getText());//input for export is taken from the user through text field.
			posts retrievedPost = DatabaseUtilityPosts.retrievePostFromDB(userfromDashboard,postIdInput);
			if(retrievedPost == null) {
				alerts.postDoesntExistAlert();//handles empty text fields
			}
			else {
				FileChooser fileChooser = new FileChooser();
				fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
				File selectedFile = fileChooser.showSaveDialog(new Stage());
				if (selectedFile != null) {
					// Process the selected CSV file, passing the selectedFile to the export method
					DatabaseUtilityPosts.exportPostToCSV(retrievedPost, selectedFile);
					alerts.successfullExportAlert();
				}
			}
		}catch(NumberFormatException e) {if (postId.getText().isEmpty()) {alerts.emptyFieldsAlert();}else{alerts.invalidInputError();}}
		catch(SQLException e) {e.printStackTrace();}
	}







}

