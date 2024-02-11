package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class RecommendationCellFactory implements Callback<ListView<Recommendation>, ListCell<Recommendation>> {
	
	@Override
	public ListCell<Recommendation> call(ListView<Recommendation> param) {
		return new ListCell<Recommendation>() {
			
			private AnchorPane aPane = new AnchorPane();
			private Button editRecBtn = new Button();
			private Button deleteRecBtn = new Button();
			private Button compileRecBtn = new Button();
			private Label nameLbl = new Label();
			private Label compiledStatusLbl = new Label();
			
			@Override
			protected void updateItem(Recommendation recommendation, boolean empty) {
				super.updateItem(recommendation, empty);
				if(empty || recommendation == null) {
					setText(null);
					setGraphic(null);
				} 
				else {
					String lastName = recommendation.getStudentLastName();
					String compiledStatus = (recommendation.getIsCompiled()) ? "Compiled" : "Data Form";
					
					nameLbl.setText(lastName);
					nameLbl.setFont(new Font(18));
					
					compiledStatusLbl.setText(compiledStatus);
					if(compiledStatus.compareTo("Compiled") == 0) {
						compiledStatusLbl.setTextFill(Color.FORESTGREEN);
					}
					else {
						compiledStatusLbl.setTextFill(Color.BLUE);
					}
					
					compileRecBtn.setBackground(new Background(new BackgroundFill(Color.FORESTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
					compileRecBtn.setTextFill(Color.WHITE);
					
					editRecBtn.setText("Edit");
					
					deleteRecBtn.setText("Delete");
					deleteRecBtn.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
					deleteRecBtn.setTextFill(Color.WHITE);
					
					// of already compiled, button says "Open" instead of "Compile"
					if(recommendation.getIsCompiled()) {
						compileRecBtn.setText("Open");
					}
					else {
						compileRecBtn.setText("Compile");
					}
					
					// when compile/open button is clicked, switch to CompileRed view
					compileRecBtn.setOnAction(event -> {
						CommonUseObjects commonObj = CommonUseObjects.getInstance();
						commonObj.setSelectedRecommendationId(recommendation.getId());
						commonObj.switchToScene("view/CompileRec.fxml");
					});
					
					// when edit button is clicked, switch to EditRecData view
					editRecBtn.setOnAction(event -> {
						CommonUseObjects commonObj = CommonUseObjects.getInstance();
						commonObj.setSelectedRecommendationId(recommendation.getId());
						commonObj.switchToScene("view/EditRecData.fxml");
					});
					
					// delete recommendation if delete button is clicked
					deleteRecBtn.setOnAction(event -> {
						// delete from file system
						CommonUseObjects commonObj = CommonUseObjects.getInstance();
						commonObj.fs.deleteRecommendation(recommendation.getId());
						// reload the home page
						commonObj.switchToScene("view/HomePage.fxml");
					});
					
					aPane.setPrefHeight(100);
					aPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
					// aPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
					
					aPane.getChildren().removeAll(nameLbl, compiledStatusLbl, compileRecBtn, deleteRecBtn);
					aPane.getChildren().addAll(nameLbl, compiledStatusLbl, compileRecBtn, deleteRecBtn);
					// also add edit button, if recommendation is not compiled
					if(!recommendation.getIsCompiled()) {
						aPane.getChildren().remove(editRecBtn);
						aPane.getChildren().add(editRecBtn);
						anchorButtonsUncompiled();
					}
					else {
						anchorButtonsCompiled();
					}
					
					// anchor the labels, name, date, and compiledStatus
					AnchorPane.setLeftAnchor(nameLbl, 0.0);
					AnchorPane.setTopAnchor(nameLbl, 0.0);
					AnchorPane.setBottomAnchor(compiledStatusLbl, 0.0);
					AnchorPane.setLeftAnchor(compiledStatusLbl, 0.0);
					
					setText(null);
					setGraphic(aPane);
				}
			}
			
			private void anchorButtonsCompiled() {
				AnchorPane.setBottomAnchor(compileRecBtn, 5.0);
				AnchorPane.setRightAnchor(compileRecBtn, 0.0);
				
				AnchorPane.setBottomAnchor(deleteRecBtn, 5.0);
				AnchorPane.setRightAnchor(deleteRecBtn, 70.0);
			}
			
			private void anchorButtonsUncompiled() {
				AnchorPane.setBottomAnchor(compileRecBtn, 5.0);
				AnchorPane.setRightAnchor(compileRecBtn, 0.0);
				AnchorPane.setBottomAnchor(editRecBtn, 5.0);
				AnchorPane.setRightAnchor(editRecBtn, 84.0);
				AnchorPane.setBottomAnchor(deleteRecBtn, 5.0);
				AnchorPane.setRightAnchor(deleteRecBtn, 140.0);
			}
		};
	}
}
