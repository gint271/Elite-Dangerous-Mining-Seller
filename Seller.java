
// Main class, to be run at the start of mining.
import java.util.*;

public class Seller {
	
	public enum PadSize {SMALL, MEDIUM, LARGE};

	public static void main(String[] args) {
		// Record start time
		Date startTime = new Date();
		
		// Wait for end, get range and size
		// TODO: Can these be gotten from log?
		Scanner sc = new Scanner(System.in);  
		System.out.println("Tracking hold. Press enter when done mining.");
		sc.nextLine();
		
		float range;
		System.out.print("Enter jump range: ");
		range = Float.parseFloat(sc.nextLine());
		
		PadSize shipSize;
		System.out.print("Enter ship size (small, medium, large): ");
		String shipSizeString = sc.nextLine();
		switch(shipSizeString) {
		case "small":
			shipSize  = PadSize.SMALL;
			break;
		case "medium":
			shipSize = PadSize.MEDIUM;
			break;
		case "large":
			shipSize = PadSize.LARGE;
			break;
		default:
			sc.close();
			return;
		
		}
		
		System.out.println("Data entered is " + range + " and " + shipSize + " and " + startTime);
		
		
		// Read logs
		
		// Calculate best stations
		
		// Output to user

		sc.close();
	}

}
