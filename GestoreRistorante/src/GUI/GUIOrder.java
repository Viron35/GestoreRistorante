package GUI;

import restaurant.OpenOrder;
import restaurant.Menu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GUIOrder extends JFrame{
	private OpenOrder openOrder;
	private Menu menu;
	
	/**
	 * Construct a new GUIOrder object
	 * @param openOrder the orders
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public GUIOrder(OpenOrder openOrder,Menu menu) throws FileNotFoundException, IOException {
		this.menu = menu;
		this.openOrder = openOrder;
		init();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Create the frame, panel and buttons
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void init() throws FileNotFoundException, IOException{
		//Creation of the frame
		setTitle("ORDER");
        setSize(420, 310);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Creation of the panels
        JPanel panel = new JPanel();
        JPanel panelTable = new JPanel();
        
        panel.setLayout(null);
        panel.setSize(115, 300);
        panel.setLocation(0,0);
        panel.setBackground(Color.white);
        
        panelTable.setLayout(null);
        panelTable.setSize(290,300);
        panelTable.setLocation(120,0);

        //Creation of the buttons 
        JButton exit = new JButton("EXIT");
        JButton remove = new JButton("REMOVE");
        JButton clear = new JButton("CLEAR");
        JButton send  = new JButton("SEND");
        
        exit.setLocation(0,300);
        remove.setLocation(6,235);
        
        exit.setBounds(6, 210, 100, 30);
        remove.setBounds(6, 150, 100, 30);
        clear.setBounds(6, 90, 100, 30);
        send.setBounds(6, 30, 100, 30);
        
        
        //Creation of the table with scroll
        String[] column = new String[] {"Item", "Amount�"};
        DynamicJTable table = new DynamicJTable(openOrder,column);
        table.setShowGrid(false);
        
        JScrollPane scroll = new JScrollPane (table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setSize(260,200);
        scroll.setLocation(130,30);

        //Adding elements to the panel
        panelTable.add(scroll);
        panel.add(clear);
        panel.add(remove);
        panel.add(send);
        panel.add(exit);
        
        
        //Add the panel to the frame
        add(panel);
        add(panelTable);
        
        //Send the order to the Cook selecting the table
        send.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	if(!openOrder.empty()) {
            		//Creation of the panel to add the table
            		JPanel panelSend = new JPanel();
	                JTextField num = new JTextField(3);
	                panelSend.add(num);
	            	Object[] options = { "Send",
	                "Quit" };  
	                int result = JOptionPane.showOptionDialog(null, panelSend, "Select the table", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
	                        null, options, null);
	                
	                if(result == JOptionPane.OK_OPTION) {
						try{
							Integer.parseInt(num.getText());
							openOrder.sendOrder(Integer.parseInt(num.getText()));
							exitButtonActionPerformed(e);
						}catch (NumberFormatException nfe) {
							JOptionPane.showMessageDialog(null, "Use only numbers, " + num.getText() + " is not a valid number");
						}catch (IOException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Enter amount");
						}catch (Exception e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Table n." + Integer.parseInt(num.getText()) + " has already ordered");
						}  
	                }
            	}
            		
            }
        });
        
        //Removes a certain amount of a item
        remove.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	if(!openOrder.empty()) {
            		int row = table.getSelectedRow();

	            	//Check if there is a selected row
	            	if (row >= 0) {
	            		
	            		//Creation of the panel to remove a certain amount of ordered items 
	                    JPanel panel = new JPanel();
	                    JTextField num = new JTextField(3);
	                    panel.add(num);
	                    
	                    Object[] options = { "Send",
	                    "Quit" };                                                         
	                    int result = JOptionPane.showOptionDialog(null,panel, "Amount to delete", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
	                            null, options, null);
	                    //Try to convert String to int
	                    if(result == JOptionPane.OK_OPTION) {
	                    	try{
		                        Integer.parseInt(num.getText());
		                        openOrder.removeItem((String)table.getModel().getValueAt(row, 0),Integer.parseInt(num.getText()));
		                        table.display();
		                    }catch (NumberFormatException nfe) {
		                    	JOptionPane.showMessageDialog(null, "Use only numbers");
		                    }
	                    }
	                     
	                }else{                	
	                    JOptionPane.showMessageDialog(null, "No row selected");
	                }
            	}else {
            		try {
    					exitButtonActionPerformed(e);
    				} catch (IOException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
            	}
            }
        });
        
        //Delete the entire order
        clear.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
				openOrder.clear();
				table.display();
				if(openOrder.empty()) {
            		try {
    					exitButtonActionPerformed(e);
    				} catch (IOException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
				}
            }
        });
        
        
        //Back to the waiter frame
        exit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
            	System.out.println(openOrder.getOrderMap());
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
	 * Action to return on the waiter frame
	 * @param e
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void exitButtonActionPerformed(ActionEvent e) throws FileNotFoundException, IOException{
    	GUIWaiter r = new GUIWaiter(openOrder, menu);
        r.setVisible(true);
        this.dispose();
    }
	
}
