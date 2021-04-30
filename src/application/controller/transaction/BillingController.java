package application.controller.transaction;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import GuiUtil.AlertNotification;
import application.Main;
import hibernate.entities.Customer;
import hibernate.entities.Item;
import hibernate.reportEnties.TransactionReport;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import service.CustomerService;
import service.ItemService;
public class BillingController implements Initializable {

	 @FXML private DatePicker date;
	 @FXML private TextField txtCustomerName;
	 @FXML private TextArea txtCustomerInfo;
	 	@FXML private TextField txtItemName;
	 	@FXML private TextField txtMetal;
	    @FXML private TextField txtPurity;
	    @FXML private TextField txtMetalWeight;
	    @FXML private TextField txtOtherWeight;
	    @FXML private TextField txtTotalWeight;
	    @FXML private TextField txtLabourCharges;
	    @FXML private TextField txtOtherCharges;
	    @FXML private TextField txtRate;
 	    @FXML private TextField txtQty;
	    @FXML private TextField txtAmount;
	    @FXML private TextField txtTotalCharges;
	    @FXML private Button btnAdd;
	    
	    @FXML private TableView<TransactionReport> table;
	    @FXML private TableColumn<TransactionReport,Integer>colSrno;
	    @FXML private TableColumn<TransactionReport,String> colName;
	    @FXML private TableColumn<TransactionReport,String> colMetal;
	    @FXML private TableColumn<TransactionReport,Double> colPurity;
	    @FXML private TableColumn<TransactionReport,Double> colWeight;
	    @FXML private TableColumn<TransactionReport,Double> colQty;
	    @FXML private TableColumn<TransactionReport,Double> colLabour;
	    @FXML private TableColumn<TransactionReport,Double> colRate;
	    @FXML private TableColumn<TransactionReport,Double> colAmount;
	    
	    @FXML private Button btnClear;

	 private CustomerService customerService;
	 private ItemService itemService;
	 private AlertNotification message;
	 private List<String>customerNames = new ArrayList<>();
	 private SuggestionProvider<String>customerNameProvider;
	 private ObservableList<TransactionReport>trList = FXCollections.observableArrayList();
	 int trid;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		trid=1;
		customerService = new CustomerService();
		itemService = new ItemService();
		message = new AlertNotification();
		customerNames.addAll(customerService.getAllCustomerNames());
		customerNameProvider = SuggestionProvider.create(customerNames);
		new AutoCompletionTextFieldBinding<>(txtCustomerName,customerNameProvider);
		new AutoCompletionTextFieldBinding<>(txtItemName, SuggestionProvider.create(itemService.getAllItemNames()));
        
		colSrno.setCellValueFactory(new PropertyValueFactory<>("id"));  
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colMetal.setCellValueFactory(new PropertyValueFactory<>("metal"));
		colPurity.setCellValueFactory(new PropertyValueFactory<>("purity"));
		colWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
		colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
		colLabour.setCellValueFactory(new PropertyValueFactory<>("labour"));
		colRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
		colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
		table.setItems(trList);
		
		txtCustomerName.setOnKeyPressed(event->txtCustomerInfo.setText(""));
		txtQty.setText(""+1);
		txtRate.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                	txtRate.setText(oldValue);
                }
            }
        });
		txtQty.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,7}([\\.]\\d{0,4})?")) {
                	txtQty.setText(oldValue);
                }
            }
        });
		txtRate.setOnAction(event -> {
			if (!txtRate.getText().equals("") || isNumber(txtRate.getText())) {
				if (!txtQty.getText().equals("") || isNumber(txtQty.getText())) {
					calculateAmount();
					txtQty.requestFocus();
				}
			}
		});
		txtQty.setOnAction(event -> {
			if (!txtRate.getText().equals("") || isNumber(txtRate.getText())) {
				if (!txtQty.getText().equals("") || isNumber(txtQty.getText())) {
					calculateAmount();
					btnAdd.requestFocus();
				}
			}

		});

	}
	
	 @FXML
	    void btnNewAction(ActionEvent event) throws IOException {
		 Stage stage = new Stage();
			Parent root = FXMLLoader.load(Main.class.getResource("/application/view/create/AddCustomer.fxml"));
			stage.setScene(new Scene(root));
			stage.setTitle("Add New Customer");
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(((Node) event.getSource()).getScene().getWindow());
			stage.show();
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent e) {
					customerNames.clear();
					customerNames.addAll(customerService.getAllCustomerNames());
					customerNameProvider.clearSuggestions();
					customerNameProvider.addPossibleSuggestions(customerNames);
				}
			});
	    }

	    @FXML
	    void btnSearchAction(ActionEvent event) {
	    	if(txtCustomerName.getText().equals(""))
	    	{
	    		txtCustomerName.requestFocus();
	    		return;
	    	}
	    	Customer customer = customerService.getCustomerByName(txtCustomerName.getText());
	    	txtCustomerInfo.setText(customer.toString());
	    }
	    @FXML
	    void btnSearchItemAction(ActionEvent event) {
	    	try {
				if(txtItemName.getText().equals(""))
				{
					message.showErrorMessage("Enter Item Name");
					txtItemName.requestFocus();
					return;
				}
				Item item = itemService.getItemByName(txtItemName.getText());
				if(item==null)
				{
					message.showErrorMessage("Item Not Found Plese Select Correct Name");
					txtItemName.requestFocus();
					return;
				}
				setItemProperties(item);
				txtRate.requestFocus();
				
			} catch (Exception e) {
				e.printStackTrace();
				message.showErrorMessage("Error in Search Item "+e.getMessage());
			}
	    }
	    @FXML
	    void btnClearItemAction(ActionEvent event) {

	    }
	    @FXML
	    void btnAddAction(ActionEvent event) {
	    	if(txtMetal.getText().equals(""))
	    	{
	    		message.showErrorMessage("Search Item ");
	    		txtItemName.requestFocus();
	    		return;
	    	}
	    	calculateAmount();
	    	if(!isNumber(txtAmount.getText()))
	    	{
	    		message.showErrorMessage("Enter Correct Rate and Quantity");
	    		txtRate.requestFocus();
	    		return;
	    	}
	    	TransactionReport tr = new TransactionReport();
	    	tr.setId(trid);
	    	tr.setAmount(Double.parseDouble(txtAmount.getText()));
	    	tr.setLabour(Double.parseDouble(txtTotalCharges.getText()));
	    	tr.setMetal(txtMetal.getText());
	    	tr.setName(txtItemName.getText());
	    	tr.setPurity(Double.parseDouble(txtPurity.getText()));
	    	tr.setRate(Double.parseDouble(txtRate.getText()));
	    	tr.setWeight(Double.parseDouble(txtTotalWeight.getText()));
	    	tr.setQty(Double.parseDouble(txtQty.getText()));
	    	if(trid==1)
	    	{
	    		trList.add(tr);
	    		btnClear.fire();
	    	}
	    	else
	    	{
	    		int flag=-1;
	    		for(int i=0;i<trList.size();i++)
	    		{
	    			if(tr.getName().equals(trList.get(i).getName()))
	    			{
	    				flag=i;
	    				break;
	    			}
	    		}
	    		if(flag==-1)
	    		{
	    			tr.setId(trList.size()+1);;
	    			trList.add(tr);
	    			btnClear.fire();
	    		}
	    		else
	    		{
	    			TransactionReport t = trList.get(flag);
	       			t.setQty(Double.parseDouble(txtQty.getText())+t.getQty());
	       			t.setAmount(t.getAmount()+Double.parseDouble(txtAmount.getText()));
	       			trList.remove(flag);
	       			trList.add(flag, t);
	    			btnClear.fire();
	    		
	    		}
	    	}
	    	
	    	
	    }
	    @FXML
	    void btnClearAction(ActionEvent event) {
	    	txtItemName.setText("");   
	    	txtMetal.setText("");
	    	txtPurity.setText("");       
	    	txtMetalWeight.setText("");  
	    	txtOtherWeight.setText("");  
	    	txtTotalWeight.setText("");
	    	txtLabourCharges.setText("");
	    	txtOtherCharges.setText("");
	    	txtRate.setText("");
	    	txtQty.setText("");
	    	txtAmount.setText("");
	    	txtTotalCharges.setText("");
	    	trid = trList.size()+1;
	    }

	    void setItemProperties(Item item)
	    {
	    	txtMetal.setText(item.getMetal());
	    	txtPurity.setText(""+item.getPurity());
	    	txtMetalWeight.setText(""+item.getMetalweight());
	    	txtOtherWeight.setText(""+item.getOtherweight());
	    	txtTotalWeight.setText(""+(item.getMetalweight()+item.getOtherweight()));
	    	txtLabourCharges.setText(""+item.getLabouruchareges());
	    	txtOtherCharges.setText(""+item.getOthercharges());
	    	txtTotalCharges.setText(""+(item.getLabouruchareges()+item.getOthercharges()));
	    	
	    }
	    private boolean isNumber(String num) {
			if (num == null) {
				return false;
			}
			try {
				Double.parseDouble(num);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
	    void calculateAmount()
	    {
	    	txtAmount.setText(""+(
	    			((Double.parseDouble(txtRate.getText())/10)*Double.parseDouble(txtMetalWeight.getText()))*
	    			Double.parseDouble(txtQty.getText())));
	    	txtAmount.setText(""+(Double.parseDouble(txtAmount.getText())+Double.parseDouble(txtTotalCharges.getText())));
	    	
	    }
	    
}
