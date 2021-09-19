package GUI;

import restaurant.Menu;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class GUIChef extends JFrame{
	
	private Menu menu;
	
	private DynamicJTable table;
	private JScrollPane scroll;
	private JPanel menuPanel = new JPanel();
	
	/**
	 * Construct a new GUICheff object
	 * @param menu the menu of the restaurant
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public GUIChef(Menu menu) throws FileNotFoundException, IOException{
		super();
		this.menu = menu;
		this.scroll = this.reloadMenu();
        init();         
    }
	
	/**
	 * Create the frame, panels and buttons
	 */
    private void init() throws FileNotFoundException, IOException{
    	//Creation of the frame
        setTitle("CHEF");
        setSize(520, 310);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Creation of the buttons
        JButton add = new JButton("NEW");
        JButton delete = new JButton("DELETE");
        JButton edit = new JButton("EDIT");
        JButton exit = new JButton("BACK");
    
        Dimension size = delete.getPreferredSize();
        
        add.setBounds(6, 10, size.width, size.height);
        delete.setBounds(6, 70, size.width, size.height);
        edit.setBounds(6, 130, size.width, size.height);
        exit.setBounds(6, 235, size.width, size.height);
        
        //Creation of the panels
        JPanel buttonPanel = new JPanel();
        JPanel addPanel = addItem();
        JPanel editPanel = editItem();
        
        addPanel.setSize(420, 400);
        
        editPanel.setSize(420, 400);
        
        buttonPanel.setSize(100, 520);
        buttonPanel.setLayout(null);
        buttonPanel.setBackground(Color.white);
        
        menuPanel.setSize(520,150);
        menuPanel.setLayout(null);
        
        //Adding elements to the panels
        buttonPanel.add(add);
        buttonPanel.add(edit);
        buttonPanel.add(delete);
        buttonPanel.add(exit);
        menuPanel.add(scroll);
        
        //Adjust the scroll
        scroll.setLocation(100, 0);
        scroll.setSize(405,150);
        scroll.setVisible(true);
        table.setShowGrid(false);
        
        //Adding the panels to the frame
        add(buttonPanel);
        add(menuPanel);
        add(addPanel);
        add(editPanel);
        
        //Action listener for the button add  
        add.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	if(addPanel.isVisible()){
                    addPanel.setVisible(false);
                    
                }else{
                	editPanel.setVisible(false);
                    addPanel.setVisible(true);
                }
            }                
        });
        
        //Delete the item of a selected row
        delete.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	if(table.getSelectedRow() != -1) {
            		int reply = JOptionPane.showConfirmDialog(null, "Delete this item?", "Confirm deletion", JOptionPane.YES_NO_OPTION);
	            	if (reply == JOptionPane.YES_OPTION) {
	            		try {
	    					removeButtonActionPerformed(e);
	    					JOptionPane.showMessageDialog(null, "Item deleted");
	    				} catch (IOException e1) {
	    					// TODO Auto-generated catch block
	    					JOptionPane.showMessageDialog(null, "Select item");
	    				}
	            	} else {
	            	    JOptionPane.showMessageDialog(null, "Item not deleted");
	            	}
            	}else {
            		JOptionPane.showMessageDialog(null, "Select a row");
            	}
            	
            	
            	
            }                
        });
        
        //Show the panel to edit one item
        edit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	
            	if(editPanel.isVisible()){
                    editPanel.setVisible(false);
                    
                }else{
                	addPanel.setVisible(false);
                    editPanel.setVisible(true);
                }
            }                
        });
        //Return to the Main menu
        exit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                try {
					exitButtonActionPerformed(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            	
            }
        });
  
    }
    
    /**
     * Creation of the panel to add items on menu
     * @return the panel to add items on menu
     */
    private JPanel addItem() {
    	//Creation of the panel to return
    	JPanel addPanel = new JPanel();
        addPanel.setLayout(null);
        addPanel.setVisible(false);
        
        //Creation of the button save
        JButton save = new JButton("ADD");
        save.setBounds(270, 195, 70, 25);
        
        //Creation of the text fields
        JTextField itemNameField = new JTextField("Item name");
        JTextField itemPriceField = new JTextField("Item price");
        
        itemNameField.setForeground(new Color(153,153,153));
        itemNameField.setLocation(146, 180);
        itemNameField.setSize(100, 25);
        
        itemPriceField.setForeground(new Color(153,153,153));
        itemPriceField.setLocation(146, 210);
        itemPriceField.setSize(100, 25);
        
        //Adding elements to the panel
        addPanel.add(itemNameField);
        addPanel.add(itemPriceField);
        addPanel.add(save); 
        
        //Focus listener for the text fields
        itemNameField.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(itemNameField.getText().equals("Item name")) {
					itemNameField.setText("");
					itemNameField.setForeground(Color.BLACK);
				}else if (itemNameField.getText().equals("") ){
					itemNameField.setForeground(Color.GRAY);
				}
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(itemNameField.getText().equals("")) {
					itemNameField.setText("Item name");
					itemNameField.setForeground(Color.GRAY);
				}else {
					itemNameField.setForeground(Color.BLACK);
				}
			}
        	
        });
        
        itemPriceField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(itemPriceField.getText().equals("Item price")) {
					itemPriceField.setText("");
					itemPriceField.setForeground(Color.BLACK);
				}else if (itemPriceField.getText().equals("") ){
					itemPriceField.setForeground(Color.GRAY);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(itemPriceField.getText().equals("")) {
					itemPriceField.setText("Item price");
					itemPriceField.setForeground(Color.GRAY);
				}else {
					itemPriceField.setForeground(Color.BLACK);
				}
			}
        	
        });
        //Add the item to the menu
        save.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	
            	try {
					saveButtonActionPerformed(e, itemNameField,  itemPriceField);
				} catch (NumberFormatException | IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Use only numbers and one comma, " + itemPriceField.getText() + " is not a valid number");
					itemPriceField.setText("Item price");
					itemPriceField.setForeground(Color.GRAY);
				} catch (IllegalArgumentException e1) {
					JOptionPane.showMessageDialog(null, "This item alredy exists");
					itemNameField.setText("Item name");
					itemNameField.setForeground(Color.GRAY);
					itemPriceField.setText("Item price");
					itemPriceField.setForeground(Color.GRAY);
				}
            }
        });
        
        return addPanel;
    }
    
    /**
     * Creation of the panel to edit items on menu
     * @return the panel to edit items on menu
     */
    private JPanel editItem() {
    	//Creation of the panel to return
    	JPanel editPanel = new JPanel();
    	editPanel.setLayout(null);
    	editPanel.setVisible(false);
    	
    	//Creation of the button edit
    	JButton edit = new JButton("EDIT");
    	edit.setBounds(270, 195, 70, 25);
    	
    	//Creation of the text fields
    	JTextField item = new JTextField("Item");
    	JTextField newPrice = new JTextField("New price");

    	item.setForeground(new Color(153,153,153));
    	item.setLocation(146, 180);
    	item.setSize(100, 25);
    	
    	newPrice.setForeground(new Color(153,153,153));
    	newPrice.setLocation(146, 210);
    	newPrice.setSize(100, 25);

    	//Adding elements to the panel
        editPanel.add(item);
        editPanel.add(newPrice);
        editPanel.add(edit);
    	
    	//Focus listener for the text fields
    	item.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(item.getText().equals("Item")) {
					item.setText("");
					item.setForeground(Color.BLACK);
				}else if (item.getText().equals("") ){
					item.setForeground(Color.GRAY);
				}
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(item.getText().equals("")) {
					item.setText("Item");
					item.setForeground(Color.GRAY);
				}else {
					item.setForeground(Color.BLACK);
				}
			}
        	
        });
    	
    	newPrice.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				if(newPrice.getText().equals("New price")) {
					newPrice.setText("");
					newPrice.setForeground(Color.BLACK);
				}else if (newPrice.getText().equals("") ){
					newPrice.setForeground(Color.GRAY);
				}
				
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if(newPrice.getText().equals("")) {
					newPrice.setText("New price");
					newPrice.setForeground(Color.GRAY);
				}else {
					newPrice.setForeground(Color.BLACK);
				}
			}
        	
        });
    	//Edit the item
    	edit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	
            	try {
					editButtonActionPerformed(e, item, newPrice);
				}catch(NumberFormatException e1){
					
					JOptionPane.showMessageDialog(null, "Use only numbers and one comma, " + newPrice.getText() + " is not a valid number");
					newPrice.setText("New price");
		    		newPrice.setForeground(Color.GRAY);
					
				}catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "The item doesn't exist");
					
					item.setText("Item");
		    		item.setForeground(Color.GRAY);
		            
		            newPrice.setText("New price");
		    		newPrice.setForeground(Color.GRAY);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
    	
    	return editPanel;
    }
    
    /**
     * Add the item on menu
     * @param e event 
     * @param itemNameField the item name to add on menu
     * @param itemPriceField the item price to add on menu
     * @throws IOException
     */
    private void saveButtonActionPerformed(ActionEvent e, JTextField itemNameField, JTextField itemPriceField) throws IOException{
    	//Check if the name field contains only text or the price field contains only numbers and comma
    	if(!isAlpha(itemNameField.getText())) {
    		
    		JOptionPane.showMessageDialog(null, "Use only letters");
    		
    		itemNameField.setText("Item name");
	        itemNameField.setForeground(Color.GRAY);
	        
	        itemPriceField.setText("Item price");
	        itemPriceField.setForeground(Color.GRAY);
	        
    	}else if (!isDigit(itemPriceField.getText())){
    
    		JOptionPane.showMessageDialog(null, "Use only numbers and comma");
	        itemPriceField.setText("Item price");
	        itemPriceField.setForeground(Color.GRAY);
	        
    	}else {
    	
    		menu.addItem(itemNameField.getText(), Double.parseDouble(itemPriceField.getText()));
	        menu.WriteToFile();                
	        
	        menuPanel.remove(scroll);            
	        scroll = reloadMenu();
	        menuPanel.add(scroll);
	        
	        itemNameField.setText("Item name");
	        itemNameField.setForeground(Color.GRAY);
	        
	        itemPriceField.setText("Item price");
	        itemPriceField.setForeground(Color.GRAY);
    	}    	
    }
    
    /**
     * Remove the selected item on menu
     * @param e event
     * @throws IOException
     */
    private void removeButtonActionPerformed(ActionEvent e) throws IOException {
    	menu.removeItem(table.getSelectedKey().toString());
    	menu.WriteToFile();
    	menuPanel.remove(scroll);            
        scroll = reloadMenu();
        menuPanel.add(scroll);
    }
    
    /**
     * Edit the items on menu
     * @param e event
     * @param item the item name to edit
     * @param newPrice the new price to add
     * @throws IOException
     */
    private void editButtonActionPerformed(ActionEvent e, JTextField item, JTextField newPrice) throws IOException {
    	//Check if the name field contains only text or the price field contains only numbers and comma
    	if(!isAlpha(item.getText())) {
    		
    		JOptionPane.showMessageDialog(null, "Use only letters");
    		
    		item.setText("Item");
    		item.setForeground(Color.GRAY);
            
            newPrice.setText("New price");
    		newPrice.setForeground(Color.GRAY);
	        
    	}else if (!isDigit(newPrice.getText())){
    
    		JOptionPane.showMessageDialog(null, "Use only numbers and comma");
    		
    		item.setText("Item");
    		item.setForeground(Color.GRAY);
            
            newPrice.setText("New price");
    		newPrice.setForeground(Color.GRAY);
	        
    	}else {
    
    		menu.replaceItem(item.getText(), Double.parseDouble(newPrice.getText()));
	    	menu.WriteToFile();
	    	menuPanel.remove(scroll);            
	        scroll = reloadMenu();
	        menuPanel.add(scroll);
	    	
	        item.setText("Item");
			item.setForeground(Color.GRAY);
	        
	        newPrice.setText("New price");
			newPrice.setForeground(Color.GRAY);
    	}
    }
    
    /**
     * Dispose the current frame and create a new main menu frame
     * @param e
     * @throws IOException
     */
    private void exitButtonActionPerformed(ActionEvent e) throws IOException{
    	//GUIRestaurant r = new GUIRestaurant();
        //r.setVisible(true);
        this.dispose();
    }
    
    /**
     * Reload the menu to update the items
     * @return the updated table menu
     * @throws IOException
     */
    private JScrollPane reloadMenu() throws IOException {
    	String[] column = new String[] {"Item", "Price (€)"};
    	
    	table = new DynamicJTable(this.menu, column);
    	table.setShowGrid(false);
        
    	scroll = new JScrollPane (table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	scroll.setLocation(100, 0);
        scroll.setSize(405,150);
                
    	return scroll;
    }
    
    /**
     * Check if a given string contains only letters
     * @param name the string to check if contains only letters
     * @return
     */
    public boolean isAlpha(String name) {
        return name.matches("[a-z A-Z]+");
    }
    
    /**
     * Check if a given string contains only numbers and comma
     * @param value the string to check if contains only numbers and point
     * @return
     */
    public boolean isDigit(String value) {
    	return value.matches("[0-9.]+");
    }
}
