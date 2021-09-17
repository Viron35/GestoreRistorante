package restaurant;

import java.io.IOException;

/**
 * This class creates the strucure for running a restaurant
 * Instanciate this class when starting the resturant application
 * Relevant objects may be passed directly to GUI classes
 * All restaurant data strucures and operations are defined for these objects
 */

public class Restaurant {
	// restaurant menu
	private Menu menu;
	// restaurant order creator
	private OpenOrder creator;
	// restaurant orders
	private OrderHolder orders;
	// restaurant payments
	private PaymentHolder payments;

	/**
	 * Constructs a new Restaurant object
	 * @param pathToMenu the path to the menu
	 * @throws IOException 
	 */
	public Restaurant(String pathToMenu, String pathToReceipts) throws IOException {
		this.menu = new Menu(pathToMenu);
		this.payments = new PaymentHolder(pathToReceipts);
		this.orders = new OrderHolder();
		this.creator = new OpenOrder(menu, orders, payments);
	}

	/**
	 * Gets the restaurant menu
	 * @return the restaurant menu
	 */
	public Menu getMenu() {
		return this.menu;
	}
	
	/**
	 * Gets the restaurant OpenOrder
	 * @return the restaurant order creator 
	 */
	public OpenOrder getOpenOrder() {
		return this.creator;
	}

	/**
	 * Gets the restaurant OrderHolder
	 * @return the restaurant order holder
	 */
	public OrderHolder getOrderHolder() {
		return this.orders;
	}

	/**
	 * Gets the restaurant PaymentHolder
	 * @return the restaurant payment holder
	 */
	public PaymentHolder getPaymentHolder() {
		return this.payments;
	}

}
