package GUI;

import restaurant.Restaurant;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;


public class GUIRestaurant extends JFrame{
	
	private Restaurant res;
	private GUIChef chef;
	private GUIWaiter waiter;
	private GUICook cook;
	private GUICash cash;
	
	public GUIRestaurant(Restaurant r) throws IOException{
		this.res = r;
        init();
    }
    private void init(){
        setTitle("RESTAURANT");
        setSize (300,200); // imposta le dimensioni
        setLocationRelativeTo(null);// centra il frame
        setResizable(false);
        setVisible (true); // rende visibile f
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        add(panel);
              
        
        JButton buttonChef = new JButton("CHEF");
        JButton buttonWaiter = new JButton("WAITER");
        JButton buttonCook = new JButton("COOK");
        JButton buttonCash = new JButton("CASHIER");
        
        Dimension size = buttonCash.getPreferredSize();// prende le misure del bottone
        buttonChef.setBounds(25, 25, size.width, size.height);// posizione da left, posizione da top, larghezza del size, altezza del size
        buttonWaiter.setBounds(160, 25, size.width, size.height);
        buttonCook.setBounds(25, 100, size.width, size.height);
        buttonCash.setBounds(160, 100, size.width, size.height);
        panel.setLayout(null);
        
        panel.add(buttonChef);
        buttonChef.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                try {
                    chefButtonActionPerformed(e);
                } catch (IOException ex) {
                    Logger.getLogger(GUIRestaurant.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        });
        
        panel.add(buttonWaiter);
        buttonWaiter.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                try {
                    waiterButtonActionPerformed(e);
                } catch (IOException ex) {
                    Logger.getLogger(GUIRestaurant.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        });
        panel.add(buttonCook);
        buttonCook.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                try {
                    cookButtonActionPerformed(e);
                } catch (IOException ex) {
                    Logger.getLogger(GUIRestaurant.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        });
        
        panel.add(buttonCash);
        buttonCash.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                try {
                    cashButtonActionPerformed(e);
                } catch (IOException ex) {
                    Logger.getLogger(GUIRestaurant.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        });
        //panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        //panel.add(chef);
        
        
    }
    private void chefButtonActionPerformed(ActionEvent e) throws IOException{
    	//if (newFrameValidation()) {
    		this.chef = new GUIChef(res.getMenu());
    		this.chef.setVisible(true);
    		// this.dispose();
    	//}
    }
    private void waiterButtonActionPerformed(ActionEvent e) throws IOException{
    	//if (newFrameValidation()) {
    		this.waiter = new GUIWaiter(res.getOpenOrder(), res.getMenu());
    		this.waiter.setVisible(true);
    	//}
    }
    private void cookButtonActionPerformed(ActionEvent e) throws IOException{
    	//if (newFrameValidation()) {
    		//System.out.println(res.getOrderHolder().getOrder(1));
    		this.cook = new GUICook(res.getOrderHolder());
    		this.cook.setVisible(true);
    		//this.dispose();
    	//}
    }
    private void cashButtonActionPerformed(ActionEvent e) throws IOException{
    	//if (newFrameValidation()) {
    		//System.out.println(res.getOrderHolder().getOrder(1));
    		this.cash = new GUICash(res.getPaymentHolder());
    		this.cash.setVisible(true);
    		//this.dispose();
    	//}
    }
          
}
