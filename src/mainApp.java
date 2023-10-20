
import View.loginPageScene;
import javafx.application.Application;
import javafx.stage.Stage;


public class mainApp extends Application {
		

	@Override
	public void start(Stage primaryStage) throws Exception {


		loginPageScene loginPageScene = new loginPageScene(primaryStage);//primary stage is passed to the loginpage scene

		primaryStage.setTitle(loginPageScene.getTitle());
		primaryStage.setScene(loginPageScene.getScene());
		primaryStage.setResizable(false);
		primaryStage.show();

	}


	public static void main(String[] args) {

		launch(args);
	}


}
