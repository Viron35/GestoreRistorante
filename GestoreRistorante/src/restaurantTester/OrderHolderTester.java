package restaurantTester;

import restaurant.OrderHolder;
import restaurant.PaymentHolder;

import java.io.IOException;

import restaurant.Menu;
import restaurant.OpenOrder;

public class OrderHolderTester {
	
public static void main(String[] args) throws IOException {
		
		// creation of new OrderHolder
		Menu menu = new Menu("resources/menu");
		PaymentHolder payments = new PaymentHolder("resources/");
		OrderHolder holderTest = new OrderHolder();
		OpenOrder creator = new OpenOrder(menu, holderTest, payments);
		
		// testing addition of items
		System.out.println("Expecting:");
		System.out.println("fish, 2");
		System.out.println("water, 1");
		System.out.println("---------");
		System.out.println("meat, 1");
		System.out.println("Result:");
		creator.addItem("fish", 2);
		creator.addItem("water",1);
		creator.sendOrder(1);
		creator.addItem("meat",1);
		creator.sendOrder(2);
		TesterTools.matrixPrinter(holderTest.getOrder(1).getItems());
		System.out.println("---------");
		TesterTools.matrixPrinter(holderTest.getOrder(2).getItems());
		System.out.println();
		
		// testing serving
		System.out.println("Expecting:");
		System.out.println("fish, 1");
		System.out.println("water, 1");
		System.out.println("---------");
		System.out.println("meat, 1");
		System.out.println("Result:");
		holderTest.serveItem(1, "fish");
		TesterTools.matrixPrinter(holderTest.getOrder(1).getItems());
		System.out.println("---------");
		TesterTools.matrixPrinter(holderTest.getOrder(2).getItems());
		System.out.println();
		
		// testing order removal by serving
		System.out.println("Expecting:");
		System.out.println("1, 37.00");
		System.out.println("Result:");
		holderTest.serveItem(2, "meat");
		TesterTools.matrixPrinter(holderTest.getItems());
		System.out.println();
		
		// testing transfer to payment holder
		System.out.println("Expecting:");
		System.out.println("2, 15.00");
		System.out.println("Result:");
		TesterTools.matrixPrinter(payments.getItems());
		System.out.println();
		
		
		
	}

}