package restaurantTester;

import restaurant.Menu;

import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MenuTester {
	
	public static void main(String[] args) throws IOException {
		
		// testing creation of menu from file
		Menu testMenu = new Menu("resources/menu");
		
		// testing addition of item
		System.out.println("Expecting:");
		System.out.println("lobster,22.00");
		System.out.println("fish,18.00");
		System.out.println("cake,6.00");
		System.out.println("meat,15.00");
		System.out.println("water,1.00");
		System.out.println("Result:");
		testMenu.addItem("lobster", 22.00);
		TesterTools.matrixPrinter(testMenu.getItems());
		System.out.println();
		
		// testing removal of item
		System.out.println("Expecting:");
		System.out.println("lobster,22.00");
		System.out.println("fish,18.00");
		System.out.println("cake,6.00");
		System.out.println("water,1.00");
		System.out.println("Result:");
		testMenu.removeItem("meat");
		TesterTools.matrixPrinter(testMenu.getItems());
		System.out.println();
		
		// testing item quantity
		System.out.println("Expecting: 4");
		System.out.println("Result: " + testMenu.getRows());
		System.out.println();
		
		// testing price finder
		System.out.println("Expecting: 6.00");
		System.out.println("Result: " + testMenu.getItemPrice("cake"));
		System.out.println();
		
		// testing menu file writer
		testMenu.WriteToFile();
		System.out.println("Expecting:");
		System.out.println("lobster,22.00");
		System.out.println("fish,18.00");
		System.out.println("cake,6.00");
		System.out.println("water,1.00");
		System.out.println("Result:");
		File file = new File("resources/menu");
		try {
			Scanner stream = new Scanner(file);
			while (stream.hasNext()) {
				String line = stream.next();
				System.out.println(line);
			}
			stream.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		// resetting file for next test run
		try {
			PrintWriter writer = new PrintWriter(file);
			writer.write("meat,15.00\n");
			writer.write("fish,18.00\n");
			writer.write("cake,6.00\n");
			writer.write("water,1.00\n");
			writer.close();
		}
		catch (FileNotFoundException e) {
		    System.out.println(e.getMessage());
		}
		
	}

}

