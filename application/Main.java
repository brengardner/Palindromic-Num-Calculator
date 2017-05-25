package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Main extends Application {
	@Override
	public void start(Stage mainStage) {
		try {
			//Main stage props
			mainStage.centerOnScreen();
			mainStage.setTitle("Palindromic Number Calculator");
			mainStage.setMinWidth(500);
			
			//Add grid style layout
			GridPane root = new GridPane();
			Scene scene = new Scene(root,400,400);
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			mainStage.setScene(scene);
			mainStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
