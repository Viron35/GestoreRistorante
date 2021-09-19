package restaurant;

import java.util.HashMap;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class is a tool for storing and paying for an order
 * Each table has an assigned price representing the cost of their order
 */

public class PaymentHolder implements Bidimensional {
	
		// stores the set of completed and unpaid orders
		private HashMap<Integer, Double> paymentMap;
		// stores the path to receipt folder
		private String path;
		// keeps count of receipts
		private int payCount;
		
		private Menu menu;
		
		private HashMap<String, Integer> orderMap;
		
		/**
		 * Constructs a new PaymentHolder object
		 */
		public PaymentHolder(String path) {
			this.paymentMap = new HashMap<Integer, Double>();
			this.path = path;
			this.payCount = 1;
		}
		
		/**
		 * Gets the number of tables with unpaid orders
		 * @return the number of tables with unpaid orders
		 */
		public int getRows() {
			return this.paymentMap.size();
		}
		
		/**
		 * Gets all the unpaid orders represented by a table number and their cost
		 * @return unpaid orders and their prices as matrix of objects
		 */
		public Object[][] getItems() {
			return Formatter.getFormattedListFromMap(this.paymentMap);
		}
		
		/**
		 * Gets the price to be payed by a given table
		 * @param table the ID of a table that has a completed order
		 * @return the price to be payed by a given table
		 */
		public double getPayment(int table) {
			if (!this.paymentMap.containsKey(table)) {
				throw new IllegalArgumentException("This table has no outsanding payments");
			}
			return this.paymentMap.get(table);
		}
		
		/**
		 * Adds a new unpaid order to the set
		 * @param table the id number of the table
		 * @param price the price of the order is not negative
		 */
		public void addPayment(int table, double price) {
			if (this.paymentMap.containsKey(table)) {
				double tmp = this.paymentMap.get(table);
				// multiple orders from the same table are added up
				this.paymentMap.replace(table, Formatter.getFormattedPrice(tmp + price));
			}
			else {
				this.paymentMap.put(table, price);
			}
		}
		
		/**
		 * Discounts the order payment by a given percentage
		 * @param table the id number of the table in the payments set 
		 * @param percentage the percentage is strictly between 0 and 100
		 */
		public void discountPayment(int table, double percentage) {
			if (percentage <= 0.0 || percentage >= 100.0) {
				throw new IllegalArgumentException("Invalid percentage");
			}
			if (!this.paymentMap.containsKey(table)) {
				throw new IllegalArgumentException("This table has no outsanding payments");
			}
			double newPrice = this.paymentMap.get(table) * (percentage / 100.0);
			this.paymentMap.replace(table, Formatter.getFormattedPrice(newPrice));
		}
		
		/**
		 * Removes a payment from the payments set (FOR PAYING)
		 * @param table the id number of the table in the payments set 
		 */
		public void removePayment(int table) throws IOException {
			if (!this.paymentMap.containsKey(table)) {
				throw new IllegalArgumentException("This table has no outsanding payments");
			}
			// printing receipt 
			String curPath = (this.path + this.payCount + ".txt");
			File file = new File(curPath);
			
			// trying to open file
			try {
				// in case file exits
				PrintWriter writer = new PrintWriter(file);
				writer.write("RICEVUTA\n\n");
				writer.write("Num. documento: " + this.payCount + "\n");
				writer.write("Tavolo: " + table + "\n");
				for(String key: orderMap.keySet()){
		            writer.write(key + " x " + orderMap.get(key)+ " = " + (menu.getItemPrice(key)*orderMap.get(key))+ "\n");}
			
				writer.write("Prezzo: " + this.getPayment(table) + "\n");
				

	
				writer.close();
			}
			catch (FileNotFoundException e) {
				// in case does not exist
			    e.printStackTrace();
			}
			this.payCount++;
			this.paymentMap.remove(table);
		}
		
		public void addOrder(HashMap<String, Integer> orderMap2) {
			this.orderMap = orderMap2;
		}
		
		public void addMenu(Menu menu) {
			this.menu = menu;
		}}
		

