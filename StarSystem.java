public class StarSystem {
	
	
	private double x;
	private double y;
	private double z;
	
	// Creates a system and sets it's coordinates.
	
	public StarSystem(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	// Calculate the distance in light years between the two systems.
	public double distanceTo(StarSystem other) {
		return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2) + Math.pow(this.z - other.z, 2));
	}
}
