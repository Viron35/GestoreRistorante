package GUI;

import restaurant.OrderHolder;
import restaurant.PaymentHolder;

import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableCellRenderer;

public class GUICook extends JFrame{
	
	private OrderHolder orderHolder;
	private JScrollPane scroll;
	private DynamicJTable table;
	
	/**
	 * Construct a new GUICook object
	 * @param orderHolder orders to do
	 * @throws IOException
	 */
	public GUICook(OrderHolder orderHolder) throws IOException {
		this.orderHolder = orderHolder;
		this.scroll = this.reloadTable();
		init();
	}
	
	/**
	 * Create the frame, panel and buttons
	 */
	private void init() {
		//Creation of the frame
		setTitle("COOK");
        setSize(420, 310);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		     
        //Creation of the panel
        JPanel mainPanel = new JPanel();
             
        mainPanel.setSize(420, 310);
        mainPanel.setLayout(null);
       
        //Creation of the background
        JPanel panel = new JPanel();
        
        panel.setLayout(null);
        panel.setSize(410, 50);
        panel.setLocation(0,220);
        panel.setBackground(Color.white);
        
        //Creation of the button exit
        JButton exit = new JButton("EXIT");
        
        Dimension size = exit.getPreferredSize();
        exit.setBounds(10, 235, size.width, size.height);
        
        //Render the cell of the table and adjust the scroll
        DefaultTableCellRenderer rendar = new DefaultTableCellRenderer();
        rendar.setHorizontalAlignment(JLabel.CENTER);
        scroll.setLocation(0, 0);
        scroll.setSize(405,150);
        scroll.setVisible(true);
        table.setShowGrid(false);
        table.getColumnModel().getColumn(0).setCellRenderer(rendar); 
        
        //Adding elements to the panel 
        mainPanel.add(exit);
        mainPanel.add(scroll);
        mainPanel.add(panel);
        //Add panel to the frame
        add(mainPanel);
 
        //Open the orders of a selected table
        table.getSelectionModel().addListSelectionListener((ListSelectionListener) new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
            	int i = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
            	
            	tableSelectActionPerformed(event, orderHolder, i);
            	
            }
        });
        //Exit from the frame
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
	 * Dispose the current frame without lose data
	 * @param e event
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void exitButtonActionPerformed(ActionEvent e) throws FileNotFoundException, IOException{
        this.dispose();
        }
	
	/**
	 * Reload the remaining orders
	 * @return the updated orders
	 * @throws IOException
	 */
	private JScrollPane reloadTable() throws IOException {
    	String[] colonne = new String[] {"Table"};
 
    	table = new DynamicJTable(orderHolder, colonne);
    	table.setShowGrid(false);
        
    	scroll = new JScrollPane (table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	scroll.setLocation(0, 0);
        scroll.setSize(405,150);
                
    	return scroll;
    }
	
	/**
	 * Open a new frame with the orders of a given table by a selected row
	 * @param e event
	 * @param orderHolder the orders
	 * @param i the table order to open
	 */
	private void tableSelectActionPerformed(ListSelectionEvent e, OrderHolder orderHolder, int i) {
		try {
			GUITables t = new GUITables(orderHolder, i);
			t.setVisible(true);
			
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		this.dispose();
	}
}
