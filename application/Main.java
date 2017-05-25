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
			
			mainStage.centerOnScreen();
			mainStage.setTitle("Palindromic Number Calculator");
			mainStage.setMinWidth(450);
			
			GridPane root = new GridPane();
			root.setHgap(10);
			root.setVgap(10);
			root.setPadding(new Insets(10, 10, 10, 10));
			
			String directionsString = "Please select whole number lower and upper bounds in the labeled spaces below. Press 'Calculate' to search the number range for the largest palindrome.";
		    Text directions = new Text(directionsString);
		    directions.setWrappingWidth(300);
		    root.add(directions, 1,1);
		    
			Label lowerBoundLabel = new Label("Lower bound: ");
			Label upperBoundLabel = new Label("Upper bound: ");
			root.add(lowerBoundLabel, 0, 2);
			root.add(upperBoundLabel, 0, 3);
			
			TextField lowerBoundInput = new TextField();
			TextField upperBoundInput = new TextField();
			root.add(lowerBoundInput, 1, 2);
			root.add(upperBoundInput, 1, 3);
			
			Button calculateButton = new Button("Calculate");
			//Add event handler
			root.add(calculateButton, 1, 4);
			
			Label resultsLabel = new Label("Results:");
			TextArea results = new TextArea();
			results.setDisable(true);
			results.setMaxHeight(100);
			results.setMaxWidth(300);
			root.add(resultsLabel, 1, 5);
			root.add(results, 1, 6);
			
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
