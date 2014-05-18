package Map;

import java.util.ArrayList;
import java.util.Random;

import Vehicle.Vehicle;

public class Checkpoint {
	
	private int 				id;
	private boolean				endNode;
	private Coordinates 		coordinates;
	private ArrayList<Road> 	exitRoadsList;
	/**
	 * 
	 * @param id - node ID
	 * @param x - x coordinate on map
	 * @param y - y coordinate on map
	 * @param endNode - is checkpoint endNode
	 */
	public Checkpoint(int id, double x,double y, boolean endNode){
		this.id = id;
		this.coordinates = new Coordinates(x, y);
		this.endNode = endNode;
		exitRoadsList = new ArrayList<>();
	}
	
	public String toString(){
		StringBuilder res = new StringBuilder();
		res.append("Point " + this.id + ": \n Roads:\n");
		for(Road r : exitRoadsList){
			res.append( "\t" + r.toString());
		}
		return res.toString();
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
	
	/**
	 * 
	 * @param probability - probability of new vehicle appearing in given point 
	 */
	public void updateState(){
		if(isEndNode()){
			generateVehicle();
		}
		for(Road r : this.getRoads()){
			r.update();
		}
	}
	private void generateVehicle(){
		double probablility = 0.1;
		Random rand = new Random();
		if(rand.nextDouble() < probablility){
			Vehicle v = new Vehicle(this.getRoads().get( rand.nextInt(this.getRoads().size()) ));
			System.out.println("Node " + this.id + " generated vehicle!");
			System.out.println("Vehicle: " + v.getPlate() + " on road: " + v.getOnRoad().getStart().getId() + " " + v.getOnRoad().getEnd().getId());
		}
	}
	
}
