package GUI;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import restaurant.Bidimensional;

/**
 * This class creates an updating JTable for Mappable objects
 * Use this class to display any table for the restaurant application 
 * This table is not editable and allows for the selection of one row
 */

public class DynamicJTable extends JTable {
	
	// names table columns
	private String[] colNames;
	// the Mappable object with table data
	private Bidimensional map;
	
	/**
	 * Constructs a new DynamicJTable object
	 * @param map the Mappable class with table data
	 * @param colNames the table column names
	 */
	public DynamicJTable(Bidimensional map, String[] colNames) {
		super();
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.colNames = colNames;
		this.map = map;
		this.display();
		
	}
	/**
	 * 
	 */
	public boolean isCellEditable(int row, int column){
		return false;
	}
	
	/**
	 * Returns the key value of the currently selected row
	 */
	public Object getSelectedKey() {
		return this.getValueAt(this.getSelectedRow(), 0);
	}
	
	/**
	 * Displays the table with contents and accounts for content updates
	 */
	public void display() {
		DefaultTableModel model = new DefaultTableModel(map.getItems(), colNames);
		this.setModel(model);
	}
	
	

}