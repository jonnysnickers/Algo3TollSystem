package Vehicle;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import LTS.DataBase;
import Map.Checkpoint;
import Map.Map;
import Map.Road;

public class Vehicle {

	private Road 		onRoad;
	private int 		maxSpeed;
	private double  	distanceOnRoad;
	private int			currentSpeed;
	private String 		plate;
	private Random		rand;
	
	public Vehicle(Road onRoad) {
		this.onRoad = onRoad;
		onRoad.addVehicle(this);
		this.maxSpeed = 180;
		this.currentSpeed = 90;
		this.plate = getRandomString(7);
		if(this.plate == null)JOptionPane.showMessageDialog(null, "HUEHUE: null");
		rand = new Random();
	}
	
	/*
	public Vehicle(Road onRoad, String plate) {
		this(onRoad);
		this.plate = plate;
	}
	*/
	public String toString(){
		return plate;
	}
	
	/**
	 * Function activated every time when global state is updated
	 */
	public void move(){
		distanceOnRoad += currentSpeed * Map.getInstance().getTimeStep();
		currentSpeed = currentSpeed + rand.nextInt(7)-3;
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

	public void setCurrentSpeed(int currentSpeed) {
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
	
	/**
	 * Generates random string of given length
	 * @param len - length of string
	 * @return
	 */
	public static String getRandomString(int len){
		StringBuilder string = new StringBuilder();
		Random rand = new Random();
		
		char letter;
		for(int i=0; i<len; i++){
			letter = (char)( (int)'A' + rand.nextInt(26) );
			string.append(letter);
		}
		
		return string.toString();
	}

}
