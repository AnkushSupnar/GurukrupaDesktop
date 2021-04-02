package application.controller.home;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.controller.util.ViewUtil;
import hibernate.entities.Login;
import hibernate.service.service.LoginService;
import hibernate.service.serviceImpl.LoginServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
public class AddUserController implements Initializable {

	 	@FXML private TextField txtUserName;
	    @FXML private TextField txtPassword;
	    @FXML private TextField txtPassword2;
	    @FXML private Button btnAdd;
	    @FXML private Button btnClear;
 	    @FXML private Button btnExit;
 	    
 	    private LoginService service;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		service = new LoginServiceImpl();
	}
	 @FXML
	    void btnAddAction(ActionEvent event) throws IOException {
		 if(txtUserName.getText().equals(""))
		 {
			 new Alert(AlertType.ERROR,"Enter User Name").showAndWait();
			 txtUserName.requestFocus();
			 return;
		 }
		 if(txtPassword.getText().equals(""))
		 {
			 new Alert(AlertType.ERROR,"Enter Password").showAndWait();
			 txtPassword.requestFocus();
			 return;
		 }
		 if(txtPassword2.getText().equals(""))
		 {
			 new Alert(AlertType.ERROR,"Enter Password Again").showAndWait();
			 txtPassword2.requestFocus();
			 return;
		 }
		 if(!txtPassword.getText().equals(txtPassword2.getText()))
		 {
			 new Alert(AlertType.ERROR,"Password Not Matched").showAndWait();
			 txtPassword.requestFocus();
			 return;
		 }
		 Login login =null;
		 login = service.getLoginByName(txtUserName.getText());
		 if(login==null)
		 {
		  login = new Login(txtUserName.getText(), txtPassword.getText());
		  service.saveLogin(login);
		  new Alert(AlertType.INFORMATION,"Login Save Success").showAndWait();
		  new ViewUtil().changeWindow(event, "home/LoginFrame");
		  
		 }
		 else
		 {
			 service.saveLogin(login);
			 new Alert(AlertType.INFORMATION,"ogin Update Success").showAndWait();
			 new ViewUtil().changeWindow(event, "home/LoginFrame");
		 }
		 
		 
	    }

	    @FXML
	    void btnClearAction(ActionEvent event) {

	    }

	    @FXML
	    void btnExitAction(ActionEvent event) {

	    }

}
