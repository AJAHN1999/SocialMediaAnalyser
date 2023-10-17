package Controller;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import Database.dbutility;
import Database.dbutilityposts;
import Model.posts;
import Model.users;
import View.dashboardScene;
import View.updateProfileScene;
import alerts.alerts;
import customExceptions.emptyFieldException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class retrievePostController {


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

		private users userfromDashboard;
		
		private void setEmptyFields() {
			postId.setText("");
		}

		@FXML
		private void initialize() {
			postIDColumn.setCellValueFactory(new PropertyValueFactory<>("postID"));
		    postAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
		    postContentColumn.setCellValueFactory(new PropertyValueFactory<>("content"));
		    postLikesColumn.setCellValueFactory(new PropertyValueFactory<>("likes"));
		    postSharesColumn.setCellValueFactory(new PropertyValueFactory<>("shares"));
		    dateTimeColumn.setCellValueFactory(new PropertyValueFactory<>("dateTime"));
		    authorIdColumn.setCellValueFactory(new PropertyValueFactory<>("authorID"));
		}
		

		
		public void setPrimaryStage(Stage primaryStage) {
			this.primaryStage = primaryStage;
		}

		public void senduser(users user) {userfromDashboard = user;}
		
		
		@FXML
		public void retrievePost(ActionEvent event) {
			ObservableList<posts> postList = FXCollections.observableArrayList();
			try {
				int postIdInput = Integer.parseInt(postId.getText());
				posts retrievedPost = dbutilityposts.retrievePostFromDB(userfromDashboard,postIdInput);
				postList.add(retrievedPost);
				postTableView.setItems(postList);			
			}catch(NumberFormatException e) {e.printStackTrace();alerts.invalidInputError();setEmptyFields();}
			catch(SQLException e) {e.printStackTrace();}
		}
		
//		@FXML
//		public void deletePost(ActionEvent event) {
//			int postIdInput = Integer.parseInt(postId.getText());
//			boolean deletedPost = dbutility.deletePost( postIdInput);
//			if(deletedPost) {
//				alerts.postdeletedAlert(postIdInput);
//			}
//			else {
//				System.out.println("post not found?");
//			}
//		}

//		@FXML
//		private void movetoDashboard(ActionEvent event) {
//			dashboardScene dashboardScene = new dashboardScene(primaryStage);
//			primaryStage.setTitle(dashboardScene.getTitle());
//			primaryStage.setScene(dashboardScene.getScene(userfromDashboard));
//			primaryStage.show();
//		}
		}

