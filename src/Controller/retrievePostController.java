package Controller;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import java.util.ArrayList;


import Database.dbutilityposts;
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


public class retrievePostController {

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
		

		public void senduser(users user) {userfromDashboard = user;}

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

		
		
		@FXML
		public void retrievePost(ActionEvent event) {			
			try {
				int postIdInput = Integer.parseInt(postId.getText());
				posts retrievedPost = dbutilityposts.retrievePostFromDB(userfromDashboard,postIdInput);
				postList.clear();
				postList.add(retrievedPost);
				postTableView.setItems(postList);			
			}catch(NumberFormatException e) {e.printStackTrace();alerts.invalidInputError();setEmptyFields();}
			catch(SQLException e) {e.printStackTrace();}
		}
		
		@FXML
		public void deletePost(ActionEvent event) {
			try {
			int postIdInput = Integer.parseInt(postId.getText());
			boolean deletedPost = dbutilityposts.deletePost(postIdInput, userfromDashboard);
			if(deletedPost) {
				alerts.postdeletedAlert(postIdInput);
			}
			else {
				System.out.println("post not found?");
			}
			}catch(SQLException e) {e.printStackTrace();}
		}

		
		@FXML
		public void retrievetopNPosts(ActionEvent event) {
			try {
		        int numberofPosts = Integer.parseInt(Nposts.getText());
		        String comboBoxValue = combobox.getValue().toString();
		        ArrayList<posts> topNposts = dbutilityposts.retrieveNpostsFromDB(userfromDashboard, numberofPosts, comboBoxValue);
		        postList.clear();
		        postList.addAll(topNposts);
		        postTableView.setItems(postList);
		        }
			catch(SQLException e) {e.printStackTrace();}
		}
		
		@FXML
		private void backtoDashboard(ActionEvent event) {
			dashboardScene dashboardScene = new dashboardScene(primaryStage);
			primaryStage.setTitle(dashboardScene.getTitle());
			primaryStage.setScene(dashboardScene.getScene(userfromDashboard));
			primaryStage.show();
		}
		
		@FXML
		public void exportPost(ActionEvent event) {
			try {
				int postIdInput = Integer.parseInt(postId.getText());
				posts retrievedPost = dbutilityposts.retrievePostFromDB(userfromDashboard,postIdInput);
				if(retrievedPost == null) {
					System.out.println("cannot export because it doesn't belong to you");
				}
				else {
					FileChooser fileChooser = new FileChooser();
				    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
				    File selectedFile = fileChooser.showSaveDialog(new Stage());
				    if (selectedFile != null) {
				        // Process the selected CSV file, passing the selectedFile to the export method
				        dbutilityposts.exportPostToCSV(retrievedPost, selectedFile);
				    }
				}
			}
			catch(SQLException e) {e.printStackTrace();}
		}
				
		
		

		
//		public void ExportToCSV() {
//			// Get the selection model
//			TableView.TableViewSelectionModel<posts> selectionModel = postTableView.getSelectionModel();
//			// To select a single item (e.g., when a row is clicked)
//			selectionModel.setSelectionMode(SelectionMode.SINGLE);
//			posts selectedPost = selectionModel.getSelectedItem(); // For single selection
//
//			
//		}
		
	
		
		
		}

