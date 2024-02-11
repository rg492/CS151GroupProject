package application;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.util.Callback;


public class CheckboxCellFactory implements Callback<ListView<String>, ListCell<String>> {
	
	private List<String> selectedItems = new ArrayList<>();
	
	public String[] getSelectedItems() {
		return selectedItems.toArray(new String[0]);
	}
	
	public void addSelectedItem(String selectedItem) {
		if(!selectedItems.contains(selectedItem)) {
			selectedItems.add(selectedItem);
		}
	}
	
	@Override
	public ListCell<String> call(ListView<String> param) {
		return new ListCell<String>() {
			
			private AnchorPane aPane = new AnchorPane();
			private Label charLbl = new Label();
			private CheckBox checkbox = new CheckBox();
			
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);
				if(empty || item == null) {
					setText(null);
					setGraphic(null);
				}
				else {
					charLbl.setText(item);
					
					// list to action on checkbox
					checkbox.setOnAction(event -> {
						// if selected, add characteristic
						if(checkbox.isSelected()) {
							selectedItems.add(item);
						}
						// if unselected, remove characteristic
						else {
							selectedItems.remove(item);
						}
					});
					
					// mark the check box if item is in selectedItems
					if(selectedItems.contains(item)) {
						checkbox.setSelected(true);
					}
					
					aPane.setPrefHeight(30);
					aPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
					aPane.getChildren().removeAll(charLbl, checkbox);
					aPane.getChildren().addAll(charLbl, checkbox);
					
					AnchorPane.setTopAnchor(charLbl, 5.0);
					AnchorPane.setLeftAnchor(charLbl, 0.0);
					
					AnchorPane.setTopAnchor(checkbox, 5.0);
					AnchorPane.setRightAnchor(checkbox, 60.0);
					
					setText(null);
					setGraphic(aPane);
				}
			}
		};
	}
}
