package Vehicle;

import java.util.ArrayList;
import java.util.Random;

import LTS.DataBase;
import Map.Map;
import Map.Road;
import Map.Checkpoint.Checkpoint;

abstract public class Vehicle {

	private Road 		onRoad;
	private double 		maxSpeed;
	private double  	distanceOnRoad;
	private double 		currentSpeed;
	private String 		plate;

	public Vehicle(double max, double curr, String plate) {
		this.maxSpeed = max;
		this.currentSpeed = curr;
		this.plate = plate;
	}
	
	//Function activated every time when global state is updated
	void move(){
		//TODO
		double traveledDistance = currentSpeed * Map.getInstance().getTimeStep();
		if(roadEnded())chooseNewRoad();
	}

	//Method chooses new road for vehicle when vehicle reaches end of the road
	void chooseNewRoad(){
		distanceOnRoad -= onRoad.getLength();
		Checkpoint end = onRoad.getEnd();
		onRoad.removeVehicle(this);
			
		if(!end.isEndNode()){
			if( onRoad.isMonitored() ){
				DataBase.getInstance().addExitedToll(plate);
			}
			ArrayList <Road> nextRoads = end.getRoads();
			Random rand = new Random();
			onRoad = nextRoads.get(rand.nextInt(nextRoads.size()));

			if(onRoad.isMonitored()){
				DataBase.getInstance().addEnteredToll(plate,onRoad);
			}
			onRoad.addVehicle(this);
		}
	}
	
	boolean roadEnded(){
		return onRoad.getLength() <= distanceOnRoad;
	}
	
	public Road getOnRoad() {
		return this.onRoad;
	}

	public void setOnRoad(Road onRoad) {
		this.onRoad = onRoad;
	}

	public double getMaxSpeed() {
		return this.maxSpeed;
	}

	public double getCurrentSpeed() {
		return this.currentSpeed;
	}

	public void setCurrentSpeed(double currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public String getPlate() {
		return this.plate;
	}

	public double getDistanceOnRoad() {
		return distanceOnRoad;
	}

	public void setDistanceOnRoad(double distanceOnRoad) {
		this.distanceOnRoad = distanceOnRoad;
	}

}
