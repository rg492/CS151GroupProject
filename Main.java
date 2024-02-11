package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Main extends Application {
	
	Stage primaryStage;
	CommonUseObjects obj;
		
	@Override
	public void start(Stage primaryStage) {
		try {
			VBox root = (VBox)FXMLLoader.load(getClass().getClassLoader().getResource("view/LoginView.fxml"));
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("Faculty Recommendation Compiler");
			primaryStage.show();
			
			//adding a reference to use when switching scenes
			obj = CommonUseObjects.getInstance();
			obj.setPrimaryStage(primaryStage);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
