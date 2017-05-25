package application;
	
import java.math.BigInteger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Main extends Application {
	@Override
	public void start(Stage mainStage) {
		try {
			
			mainStage.centerOnScreen();
			mainStage.setTitle("Palindromic Calculator");
			mainStage.setMinHeight(400);
			
			GridPane root = buildGridPane();
			Scene scene = new Scene(root,400,400);
		
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			mainStage.setScene(scene);
			mainStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Builds and returns application main stage heading
	 * 
	 * @return	heading	Text	Name of application
	 */
	private Text buildHeading()
	{
		Text heading = new Text("Palindromic Number Calculator");
		heading.setId("heading");
		
		return heading;
	}
	
	/**
	 * Builds and returns application directions
	 * 
	 * @return	directions	Text	Directions for application usage
	 */
	private Text buildDirections()
	{
		String directionsString ="Choose a range of two positive integers for the lower and upper bounds below (ie 1-100).\n\nPress 'Calculate' to search the selected range for the largest palindromic number.";
	    Text directions = new Text(directionsString);
	    directions.setWrappingWidth(380);
	    
	    return directions;
	}
	
	/**
	 * Builds and returns VBox containing input form
	 * 
	 * @return	inputBox	VBox	Input form
	 */
	private VBox buildInputVBox()
	{
		VBox inputBox = new VBox();
	    inputBox.setSpacing(10);
	    
	    Text errors = new Text("\n");
		errors.setId("errors");
		errors.setWrappingWidth(400);
		
		Label lowerBoundLabel = new Label("Lower bound: ");
		TextField lowerBoundInput = new TextField();	
		HBox lowerBoundBox = buildHBox(lowerBoundLabel, lowerBoundInput);
		
		Label upperBoundLabel = new Label("Upper bound: ");
		TextField upperBoundInput = new TextField();
		HBox upperBoundBox = buildHBox(upperBoundLabel, upperBoundInput);
		
		Label resultsLabel = new Label("Results:");
		Text results = new Text();
		results.setWrappingWidth(400);
		
		Button calculateButton = new Button("Calculate");
		calculateButton.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	getLargestPalindrome(lowerBoundInput.getText(), upperBoundInput.getText(),errors, results);
		    }
		});
		
		inputBox.getChildren().addAll(errors, lowerBoundBox, upperBoundBox, calculateButton, resultsLabel, results);
		
		return inputBox;
	}
	
	/**
	 * 
	 * 
	 */
	private HBox buildHBox(Label label, TextField input)
	{
		HBox boundBox = new HBox();
		boundBox.setSpacing(10);
		boundBox.getChildren().addAll(label, input);
		
		return boundBox;
	}
	
	/**
	 * Builds GridPane containing application elements
	 * 
	 * @return	root	GridPane	Application root pane for main stage
	 */
	private GridPane buildGridPane()
	{
		GridPane root = new GridPane();
		root.setId("root");
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(10, 10, 10, 10));
		
		Text heading = buildHeading();
		root.add(heading, 0, 0, 2, 1);
	
		Text directions = buildDirections();
	    root.add(directions, 0, 1, 2, 1);
	    
	    VBox inputBox = buildInputVBox();
		root.add(inputBox, 0, 2);
		
		return root;
	}
	
	/**
	 * Finds the largest palindrome between lower and upper bounds and
	 * displays result or error message
	 * 
	 * @param lowerBound	String		User input to lower bound field
	 * @param upperBound	String		User input to upper bound field
	 * @param errors		Text		Display errors
	 * @param results		Text		Display results
	 */
	private void getLargestPalindrome(String lowerBound, String upperBound,Text errors, Text results)
	{
		
		boolean validInput = areBoundsValid(lowerBound, upperBound, errors);
		
		if (validInput)
		{	
			errors.setText("\n");
			int lowerValue = Integer.parseInt(lowerBound);
			int upperValue = Integer.parseInt(upperBound);
						FindLargestPalindrome find = new FindLargestPalindrome(lowerValue, upperValue);
			int value = find.returnLargestPalindrome();
			String message = "";
			
			if (value == -1)
			{
				message = "No palindromic number found between " + lowerBound + " and " + upperBound;
			}else{
				message = "Largest palindromic number found is: " + value;
			}
				
			results.setText(message);
		}
	}
	
	/**
	 * Determines if lower and upper bounds are positive integers and
	 * displays error to user if either value is invalid.
	 * 
	 * @param lowerBound	String	User input for lower bound
	 * @param upperBound	String	User input for upper bound
	 * @param errors		Text	gui component where errors are displayed
	 * @return				boolean	True if upper and lower bounds are valid, else False
	 */
	private boolean areBoundsValid(String lowerBound, String upperBound, Text errors)
	{
		
		if (!isNumber(lowerBound) || !isNumber(upperBound))
		{
			errors.setText("Lower and upper bounds must be positive integer values.\n");
			return false;
		}
		
		BigInteger lowerBigInt = new BigInteger(lowerBound);
		BigInteger upperBigInt = new BigInteger(upperBound);
		String maxInteger = Integer.toString(Integer.MAX_VALUE);
		BigInteger maxIntValue = new BigInteger(maxInteger);
		
		if (lowerBigInt.compareTo(maxIntValue) >= 1 || upperBigInt.compareTo(maxIntValue) >= 1)
		{
			errors.setText("Values must be in the positive integer range [0 - 2147483647]");
			return false;
		}
		
		if (lowerBigInt.compareTo(upperBigInt) >= 0)
		{
			errors.setText("Please choose a lower bound that is less than the upper bound.");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Determines if a given string is a valid positive integer value
	 * 
	 * @param input	String	String of user input to check for integer value
	 * @return	boolean	True if a valid integer, else False
	 */
	private boolean isNumber(String input)
	{
		if (input.matches("^[0-9]*") && !input.matches(""))
		{
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
