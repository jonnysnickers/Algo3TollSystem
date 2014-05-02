package Map.Checkpoint;

import Map.Coordinates;
import Vehicle.Vehicle;

public class Checkpoint {
	
	private int id;
	private Coordinates coordinates;
	
	public Checkpoint(double x,double y){
		this.id = 1;
		this.coordinates = new Coordinates(x, y);
	}
	
	public int getId(){
		return this.id;
	}
	
	public Coordinates getCoordinates(){
		return this.coordinates;
	}
	
	//TODO
	public void handleVehicle(Vehicle vehicle){
		
	}
	
}
