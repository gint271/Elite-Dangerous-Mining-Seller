
// Main class, to be run at the start of mining.
import java.io.IOException;
import java.util.*;

public class Seller {
	
	public enum PadSize {SMALL, MEDIUM, LARGE};

	public static void main(String[] args) {
		// Record start time
		// TODO: Check timezone
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
		
		
		// Create up to date cargo.
		ShipHold cargo;
		try {
			cargo = new ShipHold("C:\\Users\\gint271\\Saved Games\\Frontier Developments\\Elite Dangerous\\Journal.170925125640.01.txt", 
					startTime);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to read log. " + e);
			sc.close();
			return;
		}
		
		// Calculate best stations
		
		// Output to user

		sc.close();
	}

}
