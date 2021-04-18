package application.controller.report;

import java.net.URL;
import java.util.ResourceBundle;

import hibernate.entities.Item;
import hibernate.service.service.ItemService;
import hibernate.service.serviceImpl.ItemServiceImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;

public class ViewAllItemsController implements Initializable {
	    @FXML private TableView<Item> table;
	    @FXML private TableColumn<Item,Long> colSrNo;
	    @FXML private TableColumn<Item,Integer> colHsn;
	    @FXML private TableColumn<Item,String> colName;
	    @FXML private TableColumn<Item,String> colMetal;
	    @FXML private TableColumn<Item,Double> colPurity;
	    @FXML private TableColumn<Item,Double> colMetalWeight;
	    @FXML private TableColumn<Item,Double> colOther;
	    @FXML private TableColumn<Item,String> colUnit;
	    @FXML private TableColumn<Item,Double> colLabour;
	    @FXML private TableColumn<Item,Double> colOtherCharges;
	    private ObservableList<Item> list = FXCollections.observableArrayList();
	    private ItemService service;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		service = new ItemServiceImpl();
		list.addAll(service.getAllItems());
		
		
		colSrNo.setCellValueFactory(new PropertyValueFactory<>("id"));
		colHsn.setCellValueFactory(new PropertyValueFactory<>("hsn"));
		colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		colMetal.setCellValueFactory(new PropertyValueFactory<>("metal"));
		colPurity.setCellValueFactory(new PropertyValueFactory<>("purity"));
		colMetalWeight.setCellValueFactory(new PropertyValueFactory<>("metalWeight"));
		colOther.setCellValueFactory(new PropertyValueFactory<>("otherWeight"));
		colUnit.setCellValueFactory(new PropertyValueFactory<>("weightUnit"));
		colLabour.setCellValueFactory(new PropertyValueFactory<>("labourChareges"));
		colOtherCharges.setCellValueFactory(new PropertyValueFactory<>("otherCharges"));
		
		table.setItems(list);
	}

}
