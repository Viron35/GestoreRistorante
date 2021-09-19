package restaurant;

import java.util.HashMap;

public class OpenOrder extends Order implements Bidimensional, Priceable {
	
	// reference to the restaurant Menu
	private Menu menu;
	// reference to the restaurant OrderHolder
	private OrderHolder holderOrder;
	// reference to the restaurant PaymentHolder
	private PaymentHolder holderPayment;
	
	
	/**
	 * Constructs a new OpenOrder object
	 * @param menu the menu for the restaurant
	 * @param holder the order holder for the restaurant
	 */
	public OpenOrder(Menu menu, OrderHolder holder1, PaymentHolder holder2) throws IllegalArgumentException {
		super(new HashMap<String, Integer>(), 0.0);
		this.menu = menu;
		this.holderOrder = holder1;
		this.holderPayment = holder2; 
	}
	
	/**
	 * Checks if there are no items in the order
	 * @return true if the order is empty
	 */
	public boolean empty() {
		if(super.getRows() == 0) {
			return true;
		}
		return false;
		
	}
	
	/**
	 * Adds a given quantity of an item to the order
	 * @param name the name of an item in the menu
	 * @param quantity the quantity of an item is at least 1
	 */
	public void addItem(String name, int quantity) throws IllegalArgumentException {
		double price = this.menu.getItemPrice(name);
		super.addItem(name, quantity);
		price *= quantity;
		price += super.getPrice();
		super.setPrice(price);
	}
	
	/**
	 * Removes a given quantity of an item from the order 
	 * @param name the name of an item in the menu
	 * @param quantity the quantity to remove is not less than the quantity of the item
	 */
	public void removeItem(String name, int quantity) throws IllegalArgumentException {
		double price = this.menu.getItemPrice(name);
		super.removeItem(name, quantity);
		price *= quantity;
		price = super.getPrice() - price;
		super.setPrice(price);
	}
	
	/**
	 * Clears all items and resets the order and the price
	 * Previous order items and price will be lost
	 */
	public void clear() {
		super.setOrderMap(new HashMap<String, Integer>());
		super.setPrice(0.0);
	}
	
	/**
	 * Adds the completed order to OrderHolder and the payment to PaymentHolder
	 * Clears the OpenOrder
	 * @param table the ID of the table that ordered
	 */
	public void sendOrder(int table) throws IllegalArgumentException {
		if (this.empty()) {
			throw new IllegalArgumentException("No items in the order");
		}
		// adding order for chef
		holderOrder.addOrder(new ClosedOrder(super.getOrderMap(), super.getPrice()),table);
		// addind payment for cashier
		this.holderPayment.addPayment(table, super.getPrice());
		// clearing for next order
		this.clear();
	}
	

}
