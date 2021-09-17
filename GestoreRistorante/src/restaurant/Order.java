package restaurant;

import java.util.HashMap;

/**
 * This class represents an abstract restaurant order
 * The order stores all ordered items and their quantity
 * The total price of the order is also stored
 */

public class Order implements Bidimensional, Priceable {
	
	// stores the set ordered items
	private HashMap<String, Integer> orderMap;
	// total price of the order
	private double price;
	
	/**
	 * Constructs a new Order object
	 * @param map the map to store order items
	 * @param price the price of the order is a non negative amount
	 */
	public Order(HashMap<String, Integer> map, double price) {
		this.orderMap = map;
		this.price = Formatter.getFormattedPrice(price);
	}
	
	/**
	 * Gets the number of different items in the order
	 * @return the number of different items in the order
	 */
	public int getRows() {
		return this.orderMap.size();
	}
	
	/**
	 * Gets all order items with prices and quantities and returns them in a matrix of object 
	 * @return order items and their quantities as matrix of objects
	 */
	public Object[][] getItems() {
		return Formatter.getFormattedListFromMap(this.orderMap);
	}
	
	/**
	 * Gets the total price of the order
	 * @return the total price of the order
	 */
	public double getPrice() {
		return this.price;
	}
	
	/**
	 * Clears all attributes and resets the order
	 */
	public HashMap<String, Integer> getOrderMap() {
		return this.orderMap;
	}
	
	/**
	 * Resets the map that stores the order to any given map
	 * @param map the map that stores a valid order
	 */
	protected void setOrderMap(HashMap<String, Integer> map) {
		this.orderMap = map;
	}
	
	/**
	 * Resets the price of the order
	 * @param price the price is a non negative amount
	 */
	protected void setPrice(double price) {
		this.price = Formatter.getFormattedPrice(price);
	}
	
	/**
	 * Adds a given quantity of an item to the order
	 * @param name the name of an item 
	 * @param quantity the quantity of an item is at least 1
	 */
	protected void addItem(String name, int quantity) {
		if (this.orderMap.containsKey(name)) {
			int tmp = this.orderMap.get(name);
			this.orderMap.replace(name, Formatter.getFormattedQuantity(tmp + quantity));
		}
		else {
			this.orderMap.put(name, quantity);
		}
	}
	
	/**
	 * Removes a given quantity of an item from the order 
	 * @param name the name of an item 
	 * @param quantity the quantity to remove is greater than the quantity of given item
	 */
	protected void removeItem(String name, int quantity) {
		if (!this.orderMap.containsKey(name)) {
			throw new IllegalArgumentException("Item not in order");
		}
		int tmp = this.orderMap.get(name);
		if (tmp < quantity) {
			// asked to remove greater quantity than has been ordered
			throw new IllegalArgumentException("Invalid quantity");
		}
		else if (tmp == quantity) {
			// asked to remove same quantity as has been ordered
			this.orderMap.remove(name);
		}
		else {
			// asked to remove less than has been ordered
			this.orderMap.replace(name, Formatter.getFormattedQuantity(tmp - quantity));
		}
	}

}
