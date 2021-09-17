package restaurant;

import java.util.HashMap;

/**
 * This class defines static methods for formatting restaurant data
 * The static methods return correctly formatted data
 * Errors are thrown if input is not cannot be formatted 
 */

public class Formatter {
	
	/**
	 * Formats the price by rounding down to 2 decimal places
	 * Throws error for negative price
	 * @param price the price is a non negative amount
	 * @return the price rounded down to 2 decimal places
	 */
	static double getFormattedPrice(double price) {
		if (price < 0.0) {
			throw new IllegalArgumentException("Negative price");
		}
		return ((int) (price * 100)) / 100.0;
	}
	
	/**
	 * Formats the item quantity
	 * Throws error for non positive quantity
	 * @param quantity the quantity is a positive integer
	 * @return the quantity
	 */
	static int getFormattedQuantity(int quantity) {
		if (quantity < 1) {
			throw new IllegalArgumentException("Non positive quantity");
		}
		return quantity;
	}
	
	/**
	 * Reformats a HashMap
	 * Saves HashMap data in a standardised data structure (matrix of Object)
	 * @param map the HashMap to reformat
	 * @return the reformatted map as matrix of Objects
	 */
	public static Object[][] getFormattedListFromMap(HashMap map) {
		// setting variables
		Object[][] items = new Object[map.size()][2];
		int counter = 0;
		// looping for item and tracking array index by counter
		for (Object item : map.keySet()) {
			items[counter][0] = item;
			items[counter][1] = map.get(item);
			counter ++;
		}
		return items;
	}

}
