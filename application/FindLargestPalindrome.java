package application;
/**
 * Finds the largest palindromic number in a numeric sequence
 * determined by the lower and upper bounds.
 * 
 * @author Brenna Gardner
 *
 */
public class FindLargestPalindrome {

	int lowerBound;
	int upperBound;
	
	/**
	 * Constructor
	 * 
	 * @param lowerBound	int	Lower boundary of numeric sequence
	 * @param upperBound	int	Upper boundary of numeric sequence
	 */
	public FindLargestPalindrome(int lowerBound, int upperBound) {
		
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
	}
	
	/**
	 * Returns the largest palindromic number found in the 
	 * numeric sequence between the lowerBound and upperBound or
	 * -1 if not found.
	 * 
	 * @return	palindrome	The largest palindromic number found
	 * between the lowerBound and upperBound. If no palindromic
	 * number is found, -1 is returned.
	 */
	public int returnLargestPalindrome()
	{
		int largestPalindrome = -1;
		
		for (int i=this.lowerBound;i<=this.upperBound;i++)
		{
			if (isPalindrome(i) && i > largestPalindrome)
			{
				largestPalindrome = i;
			}
		}
		
		return largestPalindrome;
	}
	
	/**
	 * Determines if number is a palindrome
	 * 
	 * @param number	int	Integer value to test for a palindromic number
	 * @return boolean True if palindromic, else False
	 */
	public boolean isPalindrome(int number)
	{
		String numberString = Integer.toString(number);
		String reverseString = new StringBuilder(numberString).reverse().toString();
		
		if (numberString.equals(reverseString))
		{
			return true;
		}
		
		return false;
	}
	
	public static void main(String args[])
	{	
			
		FindLargestPalindrome testIsPalindrome = new FindLargestPalindrome(0, 1);
		System.out.println(testIsPalindrome.isPalindrome(0));
		System.out.println(testIsPalindrome.isPalindrome(19));
		System.out.println(testIsPalindrome.isPalindrome(1212121));
		System.out.println(testIsPalindrome.isPalindrome(123456));
		
		FindLargestPalindrome findTwelveToThirteen = new FindLargestPalindrome(12, 13);
		int twelveToThirteen = findTwelveToThirteen.returnLargestPalindrome();
		System.out.println(twelveToThirteen);
		
		FindLargestPalindrome findZeroToTen = new FindLargestPalindrome(0, 10);
		int zeroToTen = findZeroToTen.returnLargestPalindrome();
		System.out.println(zeroToTen);
		
		FindLargestPalindrome findZeroToOneHundred = new FindLargestPalindrome(0, 100);
		int zeroToOneHundred = findZeroToOneHundred.returnLargestPalindrome(); 
		System.out.println(zeroToOneHundred);
		
		FindLargestPalindrome findZeroToOneThousand = new FindLargestPalindrome(0, 1000);
		int zeroToOneThousand = findZeroToOneThousand.returnLargestPalindrome();
		System.out.println(zeroToOneThousand);
		
		FindLargestPalindrome findZeroToTenThousand = new FindLargestPalindrome(0, 10000);
		int zeroToTenThousand = findZeroToTenThousand.returnLargestPalindrome(); 
		System.out.println(zeroToTenThousand);
	}
	
}
