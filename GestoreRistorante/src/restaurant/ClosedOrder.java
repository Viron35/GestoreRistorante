package restaurant;

import java.util.HashMap;

/**
 * Represents a completed order which is ready to be cooked and served
 * No modifications can be made to the order apart from serving items
 */

public class ClosedOrder extends Order implements Bidimensional, Priceable {
	
	
	/**
	 * Constructs a new ClosedOrder object
	 * @param map the map with all order items is not empty
	 * @param price the price of the order is a non negative amount
	 */
	public ClosedOrder(HashMap<String, Integer> map, double price) {
		super(map,price);
	}
	
	
	/**
	 * @override Order
	 * Serves a given item from the order
	 * @param name the name of an item 
	 */
	public void serveItem(String name) {
		super.removeItem(name, 1);
	}
	
}
