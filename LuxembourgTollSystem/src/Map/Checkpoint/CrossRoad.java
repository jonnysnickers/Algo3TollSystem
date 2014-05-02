package Map.Checkpoint;

import java.util.Vector;

import Map.Road;

public class CrossRoad extends Checkpoint {

	private Vector<Road> in;
	private Vector<Road> out;
	
	public CrossRoad(double x, double y) {
		super(x, y);
		this.in = new Vector<Road>();
		this.out = new Vector<Road>();
	}
	
	public Vector<Road> getIn(){
		return this.in;
	}
	
	public Vector<Road> getOut(){
		return this.out;
	}

}
