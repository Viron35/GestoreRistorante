package GUITester;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import GUI.DynamicJTable;
import restaurant.Restaurant;

public class DynamicJTableTester {
	
public static void main(String[] args) throws IOException {
		
		// implementing menu with one dish addition option using DynamicJTable
		
		String[] names = {"food","price"};
		
		Restaurant food = new Restaurant("resources/menu","resources/");
		
		JFrame frame = new JFrame();
		
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		panel.setLayout(new GridLayout());
		
		DynamicJTable table = new DynamicJTable(food.getMenu(), names);
		table.setBounds(30,40,200,300);
		
		JScrollPane pane = new JScrollPane(table);
		
		JButton add = new JButton("add");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				food.getMenu().addItem("pasta",7.00);
				table.display();
			}
		});
		add.setSize(20,30);
		JButton save = new JButton("save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				food.getMenu().WriteToFile();
			}
		});
		save.setSize(20,30);
		
		panel.add(pane);
		panel.add(add);
		panel.add(save);
		frame.add(panel, BorderLayout.CENTER);
		frame.setSize(400,400);
		frame.setTitle("test 1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
		frame.pack();
		frame.setVisible(true);
		
	}

}
