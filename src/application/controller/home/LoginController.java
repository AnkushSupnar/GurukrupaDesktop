package application.controller.home;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;

import hibernate.entities.Login;
import hibernate.service.service.LoginService;
import hibernate.service.serviceImpl.LoginServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class LoginController implements Initializable {
	 	@FXML
	    private JFXComboBox<String> cmbUserName;

	    @FXML
	    private JFXPasswordField txtPassword;

	    @FXML
	    private JFXButton btnLogin;

	    @FXML
	    private JFXButton btnCancel;

	    private LoginService service;
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
	    	service = new LoginServiceImpl();
	    	cmbUserName.getItems().addAll(service.getAllUserNames());
	    	
		}
	    
	    @FXML
	    void btnCancelAction(ActionEvent event) {

	    }

	    @FXML
	    void btnLoginAction(ActionEvent event) {
	    	try {
				if(cmbUserName.getValue().equals(""))
				{
					new Alert(AlertType.ERROR,"Select User Name!!!").showAndWait();
					cmbUserName.requestFocus();
					return;
				}
				if(txtPassword.getText().equals(""))
				{
					new Alert(AlertType.ERROR,"ENter User Password!!!").showAndWait();
					txtPassword.requestFocus();
					return;
				}
				Login login = service.getLoginByName(cmbUserName.getValue());
				if(login.getPassword().equals(txtPassword.getText()))
				{
					new Alert(AlertType.ERROR,"Wron Password!!!").showAndWait();
					txtPassword.requestFocus();
					return;
				}
			} catch (Exception e) {
				new Alert(AlertType.ERROR,"Error "+e.getMessage()).showAndWait();
				
			}
	    }

		
}
