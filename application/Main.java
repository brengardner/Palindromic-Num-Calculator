package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
/**
 * Runs main application using JavaFX
 * 
 * @author Brenna Gardner
 *
 */
public class Main extends Application {
	@Override
	public void start(Stage mainStage) {
		try {
			
			MainController controller = new MainController(mainStage);
			controller.appInit();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
