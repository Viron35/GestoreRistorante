package restaurant;

/**
 * This interface represents a class with a two dimensional data structure
 * It defines basic sizing and visualising methods for that structure
 */

public interface Bidimensional {
	
	/**
	 * Gets number of rows in the structure
	 */
	public int getRows();
	
	/**
	 * Gets the structure in a standardised 2D array format
	 */
	public Object[][] getItems();

}