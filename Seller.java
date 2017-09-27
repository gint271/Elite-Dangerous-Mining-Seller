
// Main class, to be run at the start of mining.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Seller {
	
	public enum PadSize {SMALL, MEDIUM, LARGE};
	
	private final static int ID_INDEX = 0;
	private final static int NAME_INDEX = 2;
	private final static int X_INDEX = 3;
	private final static int Y_INDEX = 4;
	private final static int Z_INDEX = 5;
	private final static int POPULATION_INDEX = 6;

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
			cargo = new ShipHold("./TestFiles/Journal.170925125640.01", 
					startTime);
		} catch (IOException e) {
			System.out.println("Failed to read log. " + e);
			sc.close();
			return;
		}
		
		// Calculate best stations
		FileReader fr;
		try {
			fr = new FileReader("./TestFiles/systems.csv");
		} catch (IOException e) {
			System.out.println("Failed to open system file. " + e);
			sc.close();
			return;
		}
		BufferedReader systemReader = new BufferedReader(fr);
		Map<Integer, Point3D> systemMap = new HashMap<Integer, Point3D>();
		String currentLine;
		int originID;
		// TODO: Make a system object.
		try {
			while ((currentLine = systemReader.readLine()) != null) {
				String systemElements[] = currentLine.split(",");
				if (systemElements[NAME_INDEX].equals("\"GCRV 1568\"")) {
					System.out.println(currentLine);
					originID = Integer.parseInt(systemElements[ID_INDEX]);
					
				} else if ()
			}
		} catch (IOException e) {
			System.out.println("Failed to read system file. " + e);
			sc.close();
			return;
		}
		
		System.out.println("Done");
		
		// Output to user

		sc.close();
	} 

}
