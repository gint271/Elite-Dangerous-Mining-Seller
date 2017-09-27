import java.util.*;
import java.io.*;

// TODO: Make iteratable
public class ShipHold {
	private Map<String, Integer> cargo;
	
	public ShipHold(String logPath, Date startTime) throws IOException {
		// Go over log and catch up to the hold's current state.
		// Assume it starts empty.
		cargo = new HashMap<String, Integer>();
		
		FileReader fr = new FileReader(logPath);
		BufferedReader logReader = new BufferedReader(fr);
		
		// Go line by line, checking event type.
		// Perform operations to map based on type.
		String currentLine;
		while ((currentLine = logReader.readLine()) != null) {
			// TODO: Assumes English,  some log entries have unlocalised types, some don't.
			 // Find the event type.
			String eventType = readValue("event", currentLine);
			if (eventType.equals("MiningRefined")) {
				// Ore names on this entry are captialized, for some reason.
				editCargo(readValue("Type_Localised", currentLine).toLowerCase(), 1);
			} else if (eventType.equals("EjectCargo") || eventType.equals("MarketSell")) {
				editCargo(readValue("Type", currentLine), -readInt("Count", currentLine));
			} else if (eventType.equals("Died")) {
				cargo = new HashMap<String, Integer>();
			}
		}
		
		System.out.println(cargo);
		
		logReader.close();
	}
	
	// Returns the text between the first encountered pair of double quotation marks.
	// Used for reading names of minerals/event types.
	// Warning: Pretty stupid about where in the structure the keyword is found, could be in a chat message or something.
	private String readValue(String key, String line) {
		int startIndex = line.indexOf("\"" + key + "\"") + key.length() + 3;
		
		String readText = "";
		
		// Move to the first character after a "
		for (; line.charAt(startIndex) != '"'; startIndex++);
		startIndex++;
		
		for (; line.charAt(startIndex) != '"'; startIndex++) {
			readText = readText + line.charAt(startIndex);
		}
		
		return readText;
	}
	
	private int readInt(String key, String line) {
		int startIndex = line.indexOf("\"" + key + "\"") + key.length() + 3;
		
		String readText = "";
		
		for (; line.charAt(startIndex) != ','; startIndex++) {
			readText = readText + line.charAt(startIndex);
		}
		
		return Integer.parseInt(readText);
	}
	
	// Changes the amount of itemName in the hold by count.
	// Exception if a negative amount of an item occurs.
	private void editCargo (String itemName, int countChange) {
		// Error checks
		//if ((!cargo.containsKey(itemName) && countChange < 0) || (cargo.get(itemName) + countChange < 0)) {
		//	throw new IllegalArgumentException();
		//}
		
		if (!cargo.containsKey(itemName)) {
			cargo.put(itemName, countChange);
		} else {
			cargo.put(itemName, cargo.get(itemName) + countChange);
		}
	}
}
