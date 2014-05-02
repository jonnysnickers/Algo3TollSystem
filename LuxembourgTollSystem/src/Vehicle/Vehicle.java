package Vehicle;

import Map.Road;

public class Vehicle {

	private Road onRoad;
	private double maxSpeed;
	private double currentSpeed;
	private String plate;
	
	public Vehicle(double max,double curr,String plate){
		this.maxSpeed = max;
		this.setCurrentSpeed(curr);
		this.plate = plate;
	}

	public Road getOnRoad() {
		return this.onRoad;
	}

	public void setOnRoad(Road onRoad) {
		this.onRoad = onRoad;
	}
	
	public double getMaxSpeed(){
		return this.maxSpeed;
	}

	public double getCurrentSpeed() {
		return this.currentSpeed;
	}
	
	public void setCurrentSpeed(double currentSpeed) {
		this.currentSpeed = currentSpeed;
	}
	
	public String getPlate(){
		return this.plate;
	}
	
	
	
}