package restaurantTester;

import java.io.IOException;

import restaurant.PaymentHolder;

public class PaymentHolderTester {
	
public static void main(String[] args) throws IOException {
		
		// testing creation of menu from file
		PaymentHolder payTest = new PaymentHolder("resources/");
		
		// testing addition of payments
		System.out.println("Expecting:");
		System.out.println("1,18.00");
		System.out.println("2,110.00");
		System.out.println("Result:");
		payTest.addPayment(1, 18.00);
		payTest.addPayment(2, 100.00);
		payTest.addPayment(2, 10.00);
		TesterTools.matrixPrinter(payTest.getItems());
		System.out.println();
		
		// testing discount
		// testing addition of payments
		System.out.println("Expecting:");
		System.out.println("1,18.00");
		System.out.println("2,77.00");
		System.out.println("Result:");
		payTest.discountPayment(2, 70);
		TesterTools.matrixPrinter(payTest.getItems());
		System.out.println();
		
		// testing item quantity
		System.out.println("Expecting: 2");
		System.out.println("Result: " + payTest.getRows());
		System.out.println();
		
		// testing payment removal
		System.out.println("Expecting:");
		System.out.println("1,18.00");
		System.out.println("Result:");
		payTest.removePayment(2);
		TesterTools.matrixPrinter(payTest.getItems());
		System.out.println();
		
		
		
	}

}
