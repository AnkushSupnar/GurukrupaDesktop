package application.controller.home;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.controller.util.ViewUtil;
import hibernate.entities.Login;
import hibernate.service.service.LoginService;
import hibernate.service.serviceImpl.LoginServiceImpl;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController implements Initializable {
	 @FXML
	    private TextField txtUserName;

	    @FXML
	    private PasswordField txtPassword;

	    @FXML
	    private Button btnLogin;

	    @FXML
	    private Button btnCancel;
	    @FXML
	    private Hyperlink adduserLink;

	    private LoginService service;
	    private SuggestionProvider<String> userNameProvider;
	  //  private ObservableList<String>nameList = FXCollections.observableArrayList();
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
	    	
	    	service = new LoginServiceImpl();
	    	userNameProvider = SuggestionProvider.create(service.getAllUserNames());
	    	new AutoCompletionTextFieldBinding<>(txtUserName, userNameProvider);
	    	
	    	if(service.getAllUserNames().size()==0)
	    	{
	    		new Alert(AlertType.ERROR,"No User Found Click on Add User!!!").showAndWait();
	    		adduserLink.setVisible(true);
	    	}
		}
	    
	    @FXML
	    void adduserLink(ActionEvent event) throws IOException {

	    	new ViewUtil().changeWindow(event, "home/AddUserFrame");
	    }
	    @FXML
	    void btnCancelAction(ActionEvent event) {

	    }

	    @FXML
	    void btnLoginAction(ActionEvent event) {
	    	try {
				if(txtUserName.getText().equals(""))
				{
					new Alert(AlertType.ERROR,"Select User Name!!!").showAndWait();
					txtUserName.requestFocus();
					return;
				}
				if(txtPassword.getText().equals(""))
				{
					new Alert(AlertType.ERROR,"ENter User Password!!!").showAndWait();
					txtPassword.requestFocus();
					return;
				}
				Login login = service.getLoginByName(txtUserName.getText());
				if(!login.getPassword().equals(txtPassword.getText()))
				{
					new Alert(AlertType.ERROR,"Wron Password!!!").showAndWait();
					txtPassword.requestFocus();
					return;
				}
				new Alert(AlertType.INFORMATION,"Login Success").show();
				new ViewUtil().changeWindow(event, "home/DashboardFrame");
			} catch (Exception e) {
				e.printStackTrace();
				new Alert(AlertType.ERROR,"Error "+e.getMessage()).showAndWait();
				
			}
	    }

		
}
