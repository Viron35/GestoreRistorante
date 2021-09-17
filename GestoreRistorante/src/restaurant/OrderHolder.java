package restaurant;

import java.util.HashMap;

/**
 * This class is a tool for storing and serving items from orders
 * Each table has an Order object to track and interact with the order
 */

public class OrderHolder implements Bidimensional {
	
	// stores the set of completed and unpaid orders
	private HashMap<Integer, ClosedOrder> orderMap;
	// reference to the restaurant PaymentHolder
	// private PaymentHolder thePayments;
	
	/**
	 * Constructs a new OrderHolder object
	 * @param thePayments the payment holder for the current restaurant session
	 */
	public OrderHolder() {
		this.orderMap = new HashMap<Integer, ClosedOrder>();
		// this.thePayments = thePayments;
	}
	
	/**
	 * Gets the number of tables with active orders
	 * @return the number of tables with active orders
	 */
	public int getRows() {
		return this.orderMap.size();
	}
	
	/**
	 * Gets all orders represented by a table number and their cost
	 * @return orders and their prices as matrix of objects
	 */
	public Object[][] getItems() {
		// setting variables
		Object[][] items = new Object[orderMap.size()][2];
		int counter = 0;
		// looping for item and tracking array index by counter
		for (Object item : orderMap.keySet()) {
			items[counter][0] = item;
			items[counter][1] = orderMap.get(item).getPrice();
			counter ++;
		}
		return items;
	}
	
	/**
	 * Gets the Order of a given table
	 * @return the Order of a given table
	 */
	public Order getOrder(int table) {
		if (!this.orderMap.containsKey(table)) {
			throw new IllegalArgumentException("No such table");
		}
		return this.orderMap.get(table);
	}
	
	/**
	 * Adds a new order to the set
	 * @param table the id number of the table
	 */
	public void addOrder(ClosedOrder order, int table) {
		if (this.orderMap.containsKey(table)) {
			throw new IllegalArgumentException("Table has allready ordered");
		}
		this.orderMap.put(table, order);
	}
	
	/**
	 * Removes a single unit of an item (FOR SERVING)
	 * @param table the id number of the table in the orders
	 * @param name the name of an item in the given order
	 */
	public void serveItem(int table, String name) {
		if (!this.orderMap.containsKey(table)) {
			throw new IllegalArgumentException("No such table");
		}
		this.orderMap.get(table).serveItem(name);
		// checks if order is complete
		if (this.orderMap.get(table).getRows() == 0) {
			// double price = this.getOrder(table).getPrice();
			// this.thePayments.addPayment(table, price);
			this.orderMap.remove(table);
		}
	}

}

