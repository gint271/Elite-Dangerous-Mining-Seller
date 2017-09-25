import java.util.*;
import java.io.*;

// TODO: Make iteratable
public class ShipHold {
	private Map<String, Integer> cargo;
	
	public ShipHold(String logPath, Date startTime) throws IOException {
		// Go over log and catch up to the hold's current state.
		// Assume it starts empty.
		
		FileReader fr = new FileReader(logPath);
		BufferedReader logReader = new BufferedReader(fr);
		
		// Go line by line, checking event type.
		// Perform operations to map based on type.
		String currentLine;
		while ((currentLine = logReader.readLine()) != null) {
			System.out.print(currentLine);
		}
		
		logReader.close();
	}
}
