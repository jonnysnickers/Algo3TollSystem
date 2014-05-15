package Map.Checkpoint;

import java.util.Vector;

import Map.Road;
import Vehicle.Vehicle;

public class EndNode extends Checkpoint {

	public Vector<Road> in;
	
	public EndNode(double x, double y) {
		super(x, y);
		this.in = new Vector<Road>();
	}
	
	public Vector<Road> getOut(){
		return this.in;
	}
	
	//TODO
	public void destroyCar(Vehicle vehicle){
		
	}
	
	//TODO
	public void createCar(){
	
	}
	
	
}
