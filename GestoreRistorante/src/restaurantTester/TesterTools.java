package restaurantTester;

import java.util.Arrays;

public class TesterTools {
	
	public static void matrixPrinter(Object[][] matrix) {
		for (Object[] item : matrix) {
			System.out.println(Arrays.toString(item));
		}
	}

}
