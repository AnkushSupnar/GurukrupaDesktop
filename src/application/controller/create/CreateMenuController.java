package application.controller.create;

import java.net.URL;
import java.util.ResourceBundle;

import application.controller.util.ViewUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class CreateMenuController implements Initializable {
	 	@FXML private AnchorPane mainPanel;
	 	private ViewUtil viewUtil;
	 	private BorderPane home;
	 	private Pane addItem,viewItems;
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			viewUtil = new ViewUtil();
		}

		@FXML
	    void btnAddItemAction(ActionEvent event) {
			addItem = viewUtil.getPage("create/additem");
	    	home = (BorderPane) mainPanel.getParent();
	    	home.setCenter(addItem);
	    	mainPanel.setVisible(false);
	    }
		@FXML
	    void btnViewAllItemAction(ActionEvent event) {
			viewItems = viewUtil.getPage("report/viewallitems");
	    	home = (BorderPane) mainPanel.getParent();
	    	home.setCenter(viewItems);
	    	mainPanel.setVisible(false);
	    }
		@FXML
	    void btnEditItemAction(ActionEvent event) {
			addItem = viewUtil.getPage("create/additem");
	    	home = (BorderPane) mainPanel.getParent();
	    	home.setCenter(addItem);
	    	mainPanel.setVisible(false);
	    }
}
