package Map.Checkpoint;

import java.util.Vector;

import Map.Road;

public class Sink extends Checkpoint {

	public Vector<Road> in;
	
	public Sink(double x, double y) {
		super(x, y);
		this.in = new Vector<Road>();
	}
	
	public Vector<Road> getOut(){
		return this.in;
	}
	
}
