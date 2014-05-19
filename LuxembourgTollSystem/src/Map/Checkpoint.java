package Map;

import java.util.ArrayList;
import java.util.Random;

import Vehicle.Vehicle;

public class Checkpoint {
	
	private int 						id;
	private boolean						endNode; 		//Determines if checkpoint is at the border of our map
	private Coordinates 				coordinates;	//Coordinates representing point location on the map
	private ArrayList<Road> 			exitRoadsList;	//List of roads exiting given checkpoint
	
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

	public boolean isEndNode() {
		return endNode;
	}

	public ArrayList<Road> getRoads() {
		return exitRoadsList;
	}
	
	/**
	 * Method responsible for updating world state by generating new cars
	 * and updating each road exiting given checkpoint
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
	
	/**
	 * Method generates vehicle with given probability. For our purpose, 
	 * to simulate large traffic we set this value to 1 for all checkpoints
	 */
	private void generateVehicle(){
		double probablility = 1;
		Random rand = new Random();
		if(rand.nextDouble() < probablility){
			Vehicle v = new Vehicle(this.getRoads().get( rand.nextInt(this.getRoads().size()) ));
			System.out.println("Node " + this.id + " generated vehicle!");
			System.out.println("Vehicle: " + v.getPlate() + " on road: " + v.getOnRoad().getStart().getId() + " " + v.getOnRoad().getEnd().getId());
		}
	}
	
}
