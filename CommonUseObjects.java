package application;

import java.io.IOException;

import dal.FileSystem;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.stage.Stage;


// This class provides shared functionality among controllers
public class CommonUseObjects {
	
	private static CommonUseObjects useObj = new CommonUseObjects();

	// used to switch between scenes
	private Stage primaryStage;
	
	// write to and read from the files
	public FileSystem fs = new FileSystem();
	
	// The default password
	private static final String defaultPass = "p";
	
	// id of the recommendation to be displayed
	private String selectedRecommendationId = "";

	private CommonUseObjects() {}
	
	// method to jump to a specific scene (passed in as parameter)
	public void switchToScene(String Fxml) {
		try {
			CommonUseObjects obj = CommonUseObjects.getInstance();
			Stage pStage = obj.getPrimaryStage();
			Scene scene = new Scene (FXMLLoader.load(getClass().getClassLoader().getResource(Fxml)));
			pStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	// method to insert a delay
	public static void delay(long millis, Runnable continuation) {
		Task<Void> sleeper = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				try { Thread.sleep(millis); }
				catch (InterruptedException e) { }
				return null;
			}
		};
		sleeper.setOnSucceeded(event -> continuation.run());
		new Thread(sleeper).start();
	}
	
	public static CommonUseObjects getInstance() {
		return useObj;
	}
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	public FileSystem getFs() {
		return fs;
	}

	public void setFs(FileSystem fs) {
		this.fs = fs;
	}
	public String getDefaultPass() {
		return defaultPass;
	}
	
	public void setSelectedRecommendationId(String selectedRecommendationId) {
		this.selectedRecommendationId = selectedRecommendationId;
	}
	public String getSelectedRecommendationId() {
		return selectedRecommendationId;
	}
}
