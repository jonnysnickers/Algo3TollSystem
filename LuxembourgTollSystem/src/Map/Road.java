package Map;

import java.util.ArrayList;
import java.util.Random;

import LTS.DataBase;
import Vehicle.Vehicle;

public class Road {

	private int 								id;
	private double 							cost;
	private boolean							monitored;
	private Checkpoint 					start;
	private Checkpoint 					end;
	private double 							length;
	private ArrayList <Vehicle>	vehicles;
	private int									speedlimit;
	
	
	public Road(int id, Checkpoint start, Checkpoint end){
		Random rand = new Random();
		this.id = id;
		//Cost of using road - double between 0 and 30
		this.cost = (int)( rand.nextDouble()* 100)/100.0;
		this.start = start;
		this.end = end;
		//Road can be monitored with probability 0.3
		this.monitored = rand.nextDouble()<0.3;
		//Length of road between 10 and 30 km
		this.length = rand.nextInt(21)+10;
		this.vehicles = new ArrayList<>();
		//Speed limit equal to 90 or 120 or 150
		this.speedlimit = rand.nextInt(3) * 30 + 90;
	}
	
	public String toString(){
		StringBuilder res = new StringBuilder();
		res.append(start.getId() + "->" + end.getId());
		res.append("Contains vehicles: ");
		for(Vehicle v : vehicles){
			res.append(v.toString() + " ");
		}
		res.append("\n");
		return res.toString();
	}
	
	public void update(){
		for(int i=0; i<vehicles.size();i++){
			vehicles.get(i).move();
		}
	}
	
	public Boolean isMonitored(){
		return this.monitored;
	}
	
	public int getId(){
		return this.id;
	}
	
	public double getCost(){
		return this.cost;
	}
	
	public Checkpoint getStart(){
		return this.start;
	}
	
	public Checkpoint getEnd(){
		return this.end;
	}
	
	public double getLength(){
		return this.length;
	}
	
	public void addVehicle(Vehicle vehicle){
		this.vehicles.add(vehicle);
		if(isMonitored()){
			System.out.println("Generated Enter Toll: " + vehicle.getPlate());
			DataBase.getInstance().addEnteredToll(vehicle.getPlate(), this);
		}
	}
	
	public void removeVehicle(Vehicle vehicle){
		this.vehicles.remove(vehicle);
		if(isMonitored()){
			System.out.println("Generated Exit Toll: " + vehicle.getPlate());
			DataBase.getInstance().addExitedToll(vehicle.getPlate());
		}
	}
	
	public ArrayList<Vehicle> getVehicles(){
		return this.vehicles;
	}
	
	public int getSpeedLimit(){
		return this.speedlimit;
	}
	
}
