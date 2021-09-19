package restaurant;


import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class represents the menu of the restaurant
 * The menu is read from a given file and can be saved to the same file
 * The menu can be fully edited
 */

public class Menu implements Bidimensional {
	
	// stores the set of menu items
	private HashMap<String, Double> menuMap;
	// stores the path to menu file
	private String path;
	
	/**
	 * Constructs a new Menu object and reads menu from a file
	 * @param path the path is a valid path to a correctly formatted menu file
	 * @throws IOException 
	 */
	public Menu(String path) throws IOException {
		this.menuMap = new HashMap<String, Double>();
		this.path = path;
		this.readFromFile();
	}
	
	/**
	 * Reads from the file given by the path if path is valid 
	 * Saves data in the menu if the file is formatted correctly
	 * Catches formatting error and disregards incorrectly formatted rows
	 * Catches invalid file path
	 * @throws IOException 
	 */
	public void readFromFile() throws IOException {
		// declaring auxiliary variables
		String line;
		String[] menuItem;
		Double itemPrice;
		File file = new File(this.path);
		// trying to open file
		if(file.exists()) {
			try {
				// in case file exits
				Scanner stream = new Scanner(file);
				while (stream.hasNext()) {
					line = stream.next();
					menuItem = line.split(",");
					// trying to access data
					try {
						// in case two CS values found
						if (this.menuMap.containsKey(menuItem[0])) {
							// in case of repeating item names
							throw new RuntimeException("Repeating value " + menuItem[0]);
						}
						// trying to insert data into map
						try {
							// in case price is in valid format
							itemPrice = Double.parseDouble(menuItem[1]);
							this.menuMap.put(menuItem[0], Formatter.getFormattedPrice(itemPrice));
						}
						catch (NumberFormatException e) {
							// in case price is not in valid format
							e.printStackTrace();
						}
					}
					catch (ArrayIndexOutOfBoundsException e) {
						// in case  two CS values not found
						e.printStackTrace();
					}
				}
				stream.close();
			} 
			catch (FileNotFoundException e) {
				// in case file does not exist
				e.printStackTrace();
			}
		}else {
			file.createNewFile();
		}
	}
	
	/**
	 * Writes data from the menu to the file given by the path if path is valid 
	 * Saves data in a valid format for reopening with readFromFile method
	 * Catches invalid file path
	 */
	public void WriteToFile() {
		// declaring auxiliary variables
		String itemPrice;
		File file = new File(this.path);
		// trying to open file
		try {
			// in case file exits
			PrintWriter writer = new PrintWriter(file);
			for (String item : this.menuMap.keySet()) {
				// converting data to text and writing to file
				itemPrice = String.valueOf(this.getItemPrice(item));
				writer.write(item + "," + itemPrice + "\n");
			}
			writer.close();
		}
		catch (FileNotFoundException e) {
			// in case does not exist
		    e.printStackTrace();
		}
	}
	
	/**
	 * Gets the number of items on the menu
	 * @return the number of items on the menu
	 */
	public int getRows() {
		return this.menuMap.size();
	}
	
	/**
	 * Gets all menu items with prices and returns them in a matrix of Object 
	 * @return menu items and prices as matrix of Object
	 */
	public Object[][] getItems() {
		return Formatter.getFormattedListFromMap(this.menuMap);
	}
	
	/**
	 * Gets the price of a given item on the menu
	 * @param name the name of a given item
	 * @return the price of a given item
	 */
	public double getItemPrice(String name) throws IllegalArgumentException {
		if (! this.menuMap.containsKey(name)) {
			throw new IllegalArgumentException("Item does not exist");
		}
		return this.menuMap.get(name);
	}
	
	/**
	 * Adds a new item to the menu
	 * @param name the name of an item not on the menu
	 * @param price the price of the item is a non negative amount
	 */
	public void addItem(String name, double price) throws IllegalArgumentException {
		if (this.menuMap.containsKey(name)) {
			throw new IllegalArgumentException("Item allready exists");
		}
		this.menuMap.put(name, Formatter.getFormattedPrice(price));
	}
	
	/**
	 * Replaces the price of an item on the menu
	 * @param name the name of the item on the menu
	 * @param price the price of the item is a non negative amount
	 */
	public void replaceItem(String name, double price) throws IllegalArgumentException {
		if (! this.menuMap.containsKey(name)) {
			throw new IllegalArgumentException("Item does not exist");
		}
		this.menuMap.replace(name, Formatter.getFormattedPrice(price));
	}
	
	/**
	 * Removes an item from the menu
	 * @param name the name of the item on the menu
	 */
	public void removeItem(String name) throws IllegalArgumentException {
		if (!this.menuMap.containsKey(name)) {
			throw new IllegalArgumentException("Item does not exist");
		}
		this.menuMap.remove(name);
	}

}
