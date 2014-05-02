package Map.Checkpoint;

import java.util.Vector;

import Map.Road;

public class Source extends Checkpoint {

	public Vector<Road> out;
	
	public Source(double x, double y) {
		super(x, y);
		this.out = new Vector<Road>();
	}

	public Vector<Road> getOut(){
		return this.out;
	}
	
}
