package GUI;

import restaurant.OrderHolder;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GUITables extends JFrame{
	private DynamicJTable table;
	private JScrollPane scroll;
	private OrderHolder orderHolder;
	private int i;
	private JPanel items;
	
	/**
	 * Construct a new GUITable object
	 * @param orderHolder orders
	 * @param i table number
	 * @throws IOException
	 */
	public GUITables(OrderHolder orderHolder, int i) throws IOException {
		this.orderHolder = orderHolder;
		this.i = i;
		this.scroll = this.reloadTable();
		init();
	}
	
	/**
	 * Creation of the frame, panel and buttons
	 */
	private void init() {
		//Creation of the frame
		setTitle("Table "+ i);
        setSize(420, 310);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Creating the panel
        items = new JPanel();
        items.setLayout(null);
        
        //Creating the buttons
        JButton exit = new JButton("EXIT");
        JButton remove = new JButton("REMOVE");
        
        Dimension size = exit.getPreferredSize();
        exit.setBounds(10, 235, 70, size.height);
        remove.setBounds(110, 235, 90, size.height);
        
        //Adjust the scroll
        scroll.setLocation(0, 0);
        scroll.setSize(405,150);
        scroll.setVisible(true);
        table.setShowGrid(false);
        
        //Adding elements to the panel
        items.add(scroll);
        items.add(exit);
        items.add(remove);
        
        //Add the panel to the frame
        add(items);
        
        //Remove one item
        remove.addActionListener(new ActionListener() 
        {
        	public void actionPerformed(ActionEvent e){
        		try {
					removeButtonActionPerformed(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        
        //Back to the cook frame
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
	 * Update the table orders
	 * @return the updated table
	 * @throws IOException
	 */
	private JScrollPane reloadTable() throws IOException {
    	String[] column = new String[] {"Item", "Amount"};
 
    	table = new DynamicJTable(orderHolder.getOrder(i), column);
    	table.setShowGrid(false);
        
    	scroll = new JScrollPane (table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	scroll.setLocation(0, 0);
        scroll.setSize(405,150);
                
    	return scroll;
    }
	
	/**
	 * Remove one order at time
	 * @param e event
	 * @throws IOException
	 */
	private void removeButtonActionPerformed(ActionEvent e) throws IOException {
		try {
			orderHolder.serveItem(i, table.getSelectedKey().toString());
			items.remove(scroll);
			scroll = reloadTable();
			items.add(scroll);
		} catch (Exception e2) {
			GUICook r = new GUICook(orderHolder);
	        r.setVisible(true);
			this.dispose();
			
		}
	}
	
	/**
	 * Return back to the Cook frame
	 * @param e
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void exitButtonActionPerformed(ActionEvent e) throws FileNotFoundException, IOException{
    	GUICook r = new GUICook(orderHolder);
        r.setVisible(true);
        this.dispose();
    }
}
