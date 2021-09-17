package GUI;

import restaurant.PaymentHolder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

public class GUICash extends JFrame{
	private PaymentHolder payments;
	private DynamicJTable table;
	private JScrollPane scroll;
	private JPanel mainPanel;
	
	/**
	 * Construct a new GUICash object
	 * @param payments the payments to be made
	 * @throws IOException
	 */
	public GUICash(PaymentHolder payments) throws IOException {
		this.payments = payments;
		this.scroll = this.reloadPayments();
		init();
	}
	
	/**
	 * Create the frame, panel and buttons
	 */
	private void init() {
		//Creation of the frame
		setTitle("CASH");
        setSize(420, 310);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Creation of the buttons
        JButton exit = new JButton("EXIT");
        JButton remove = new JButton("REMOVE"); 
        
        Dimension size = exit.getPreferredSize();
        
        exit.setBounds(10, 235, size.width, size.height);
        remove.setBounds(110, 235, 90, size.height);
        
        //Creation of the mainPanel
        mainPanel = new JPanel();
        mainPanel.setSize(420, 310);
        mainPanel.setLayout(null);
        
        //Creation of the background
        JPanel panel = new JPanel();
        
        panel.setLayout(null);
        panel.setSize(410, 50);
        panel.setLocation(0,220);
        panel.setBackground(Color.white);
        
        //Adding elements to the panel
        mainPanel.add(exit);
        mainPanel.add(remove);
        mainPanel.add(scroll);
        mainPanel.add(panel);
        //Add the mainPanel to the frame
        add(mainPanel);
        
        //Remove the payments
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
        //Exit from the frame and return to the Main menu
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
	 * Close the frame on exit button press
	 * @param e
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void exitButtonActionPerformed(ActionEvent e) throws FileNotFoundException, IOException{
        this.dispose();
    }
	
	/**
	 * Action for button remove when pressed 
	 * @param e 
	 * @throws IOException
	 */
	private void removeButtonActionPerformed(ActionEvent e) throws IOException {
		try {
			payments.removePayment(Integer.parseInt(table.getSelectedKey().toString()));
			mainPanel.remove(scroll);
			scroll = reloadPayments();
			JOptionPane.showMessageDialog(null, "Receipt processing");
			mainPanel.add(scroll);
		} catch (Exception e2) {
			System.out.println(e);
		}
	}
	
	/**
	 * Reload the table of remaining payments
	 * @return the updated table with a scrollbar
	 * @throws IOException
	 */
	private JScrollPane reloadPayments() throws IOException {
    	String[] column = new String[] {"Table", "Price"};
 
    	table = new DynamicJTable(payments, column);
    	table.setShowGrid(false);
        
    	scroll = new JScrollPane (table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    	scroll.setLocation(0, 0);
        scroll.setSize(405,150);
                
    	return scroll;
    }
}
