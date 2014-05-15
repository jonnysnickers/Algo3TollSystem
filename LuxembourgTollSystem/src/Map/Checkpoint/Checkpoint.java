package Map.Checkpoint;

import java.util.ArrayList;

import Map.Coordinates;
import Map.Road;
import Vehicle.Vehicle;

public class Checkpoint {
	
	private int 				id;
	private boolean				endNode;
	private Coordinates 		coordinates;
	private ArrayList<Road> 	exitRoadsList;
	
	public Checkpoint(int id, double x,double y, boolean endNode){
		this.id = id;
		this.coordinates = new Coordinates(x, y);
		this.endNode = endNode;
	}
	
	public int getId(){
		return this.id;
	}
	
	public Coordinates getCoordinates(){
		return this.coordinates;
	}
	public void addRoad(Road road){
		exitRoadsList.add(road);
	}
	//TODO
	public void handleVehicle(Vehicle vehicle){
		/**
		 * delete veh from old road
		 * add veh to new road
		 * change onRoad in veh
		 * add Toll
		 */
		if(vehicle.getOnRoad().isMonitored()){
			//addToll
		}
	}

	public boolean isEndNode() {
		return endNode;
	}

	public ArrayList<Road> getRoads() {
		return exitRoadsList;
	}
	
}
