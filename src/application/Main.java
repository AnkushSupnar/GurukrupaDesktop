package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	String pageName;
	@Override
	public void start(Stage primaryStage) {
		try {
			pageName="home/Login";
			//pageName="home/DashboardFrame";
			//pageName="create/additem";
			//pageName="create/createcounter";
			//pageName="create/adduser";
			//pageName="create/AddCustomer";
			Parent root = FXMLLoader.load(getClass().getResource("/application/view/"+pageName+".fxml"));
	 		Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Gurukrupa Jewellors Management System");
			primaryStage.show();			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
