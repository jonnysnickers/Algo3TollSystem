package Vehicle;

import java.util.ArrayList;
import java.util.Random;

import utilsLTS.Utils;

import LTS.DataBase;
import Map.Map;
import Map.Road;
import Map.Checkpoint.Checkpoint;

public class Vehicle {

	private Road 		onRoad;
	private double 		maxSpeed;
	private double  	distanceOnRoad;
	private double 		currentSpeed;
	private String 		plate;
	
	public Vehicle(double currentSpeed, double maxSpeed) {
		this.maxSpeed = maxSpeed;
		this.currentSpeed = currentSpeed;
		this.plate = Utils.getRandomString(7);
	}
	
	public Vehicle(double maxSpeed, double currentSpeed, String plate) {
		this(maxSpeed,currentSpeed);
		this.plate = plate;
	}
	
	public String toString(){
		return plate;
	}
	
	/**
	 * Function activated every time when global state is updated
	 */
	public void move(){
		System.out.print(distanceOnRoad + " ");
		distanceOnRoad += currentSpeed * Map.getInstance().getTimeStep();
		System.out.println(distanceOnRoad);
		while(roadEnded()){
			chooseNewRoad();
		}
	}

	/**
	 * Method chooses new road for vehicle when vehicle reaches end of the road
	 */
	void chooseNewRoad(){
		distanceOnRoad -= onRoad.getLength();
		Checkpoint end = onRoad.getEnd();
		onRoad.removeVehicle(this);
		
		if(end.isEndNode()){
			System.out.println("Node " + end.getId() + " phagocyted vehicle " + this.getPlate() + "\n");			
		}
		
		else{
			if( onRoad.isMonitored() ){
				DataBase.getInstance().addExitedToll(plate);
			}
			ArrayList <Road> nextRoads = end.getRoads();
			Random rand = new Random();
			Road newRoad = nextRoads.get(rand.nextInt(nextRoads.size()));
			System.out.println("Vehicle " + this.getPlate() + " changed road "
					+ onRoad.getStart().getId() + "->" + onRoad.getEnd().getId() + " to "
					+ newRoad.getStart().getId() + "->" + newRoad.getEnd().getId() + "\n");
			onRoad = newRoad; 

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
