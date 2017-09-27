
// Main class, to be run at the start of mining.
import java.util.*;
import java.io.*;

public class Seller {
	
	public enum PadSize {SMALL, MEDIUM, LARGE};
	
	private final static int ID_INDEX = 0;
	private final static int NAME_INDEX = 2;
	private final static int X_INDEX = 3;
	private final static int Y_INDEX = 4;
	private final static int Z_INDEX = 5;
	private final static int POPULATION_INDEX = 7;

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
		
		Map<Integer, StarSystem> systemMap = new HashMap<Integer, StarSystem>();
		String currentLine;
		StarSystem startingSystem = null;
		// TODO: Make a system object.
		try {
			systemReader.readLine(); // Skip header.
			
			// Find and save the current System.
			while ((currentLine = systemReader.readLine()) != null) {
				String systemElements[] = currentLine.split(",");
				if (systemElements[NAME_INDEX].equals("\"GCRV 1568\"")) {
					System.out.println(currentLine);
					startingSystem = new StarSystem(Double.parseDouble(systemElements[X_INDEX]), 
							Double.parseDouble(systemElements[Y_INDEX]), Double.parseDouble(systemElements[Z_INDEX]));
					
				}
				
				// Import all systems with populations that could be sold to.
				String populationString = systemElements[POPULATION_INDEX];
				if (!populationString.equals("") && Integer.parseInt(populationString) == 1) {
					StarSystem populatedSystem = new StarSystem(Double.parseDouble(systemElements[X_INDEX]), 
							Double.parseDouble(systemElements[Y_INDEX]), Double.parseDouble(systemElements[Z_INDEX]));
					systemMap.put(Integer.parseInt(systemElements[ID_INDEX]), populatedSystem);
				}
			}
		} catch (IOException e) {
			System.out.println("Failed to read system file. " + e);
			sc.close();
			return;
		}
		
		// Filter out all systems out of range.
		if (startingSystem == null) {
			System.out.println("Error: Couldn't find starter system name.");
			sc.close();
			return;
		}
		
		// TODO: Do I really want a map, or just a list.  Seem to always iterate over everything, not
		// look at specific entries.
		// TODO: Make parallel.
		//final StarSystem fixedStartingSystem = startingSystem;
		//systemMap.values().parallelStream().filter(p -> (fixedStartingSystem.distanceTo(p) <= range)).to;
		
		Iterator<Map.Entry<Integer, StarSystem>> systemIter = systemMap.entrySet().iterator();
		while (systemIter.hasNext()) {
			Map.Entry<Integer, StarSystem> possibleSystem = systemIter.next();
			if (startingSystem.distanceTo(possibleSystem.getValue()) > range) {
				systemIter.remove();
			}
		}
		
		// Compile list of all stations in those systems.
		
		
		System.out.println("Done");
		
		// Output to user

		sc.close();
	} 

}
