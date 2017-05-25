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
			
			String directionsString = "Please select positive, integer lower and upper bounds in the labeled spaces below. Press 'Calculate' to search the number range for the largest palindrome.";
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
			
			Text errors = new Text();
			errors.setWrappingWidth(250);
			root.add(errors, 1, 5);
			
			Label resultsLabel = new Label("Results:");
			Text results = new Text();
			results.setDisable(true);
			results.setWrappingWidth(250);
			root.add(resultsLabel, 1, 6);
			root.add(results, 1, 7);
			
			Button calculateButton = new Button("Calculate");
			calculateButton.setOnAction(new EventHandler<ActionEvent>() {
			    @Override public void handle(ActionEvent e) {
			    	getLargestPalindrome(lowerBoundInput.getText(), upperBoundInput.getText(),errors, results);
			    }
			});
			root.add(calculateButton, 1, 4);
			
			Scene scene = new Scene(root,400,400);
		
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			mainStage.setScene(scene);
			mainStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
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
			int lowerValue = Integer.parseInt(lowerBound);
			int upperValue = Integer.parseInt(upperBound);
			FindLargestPalindrome find = new FindLargestPalindrome(lowerValue, upperValue);
			results.setText("Searching...");
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
			errors.setText("Lower and upper bounds must be positive integer values.");
			return false;
		}
		
		BigInteger lowerBigInt = new BigInteger(lowerBound);
		BigInteger upperBigInt = new BigInteger(upperBound);
		String maxInteger = Integer.toString(Integer.MAX_VALUE);
		BigInteger maxIntValue = new BigInteger(maxInteger);
		
		if (lowerBigInt.compareTo(maxIntValue) >= 1 || upperBigInt.compareTo(maxIntValue) >= 1)
		{
			errors.setText("Values must be in the positive, integer range [0 - 2147483647]");
			return false;
		}
		
		if (lowerBigInt.compareTo(upperBigInt) >= 1)
		{
			errors.setText("Please choose a lower bound that is less than or equal to the upper bound.");
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
