import View.loginPageScene;
import javafx.application.Application;
import javafx.stage.Stage;


public class mainApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {


		loginPageScene loginPageScene = new loginPageScene(primaryStage);

		primaryStage.setTitle(loginPageScene.getTitle());
		primaryStage.setScene(loginPageScene.getScene());


		primaryStage.show();

	}



	public static void main(String[] args) {
		launch(args);
	}


}
