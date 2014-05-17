package Map;

import java.util.HashSet;
import java.util.Set;

import LTS.DataBase;
import Map.Checkpoint.Checkpoint;
import Vehicle.Vehicle;

public class Road {

	private int 				id;
	private double 				cost;
	private boolean				monitored;
	private Checkpoint 			start;
	private Checkpoint 			end;
	private double 				length;
	private Set<Vehicle>	 	vehicles;
	private double 				speedlimit;
	
	
	public Road(int id, double cost, Checkpoint start, Checkpoint end,Boolean monitored, double length, double speedlimit){
		this.id = id;
		this.cost = cost;
		this.start = start;
		this.end = end;
		this.monitored = monitored;
		this.length = length;
		this.vehicles = new HashSet<Vehicle>();
		this.speedlimit = speedlimit;
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
		for(Vehicle v : vehicles){
			v.move();
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
			DataBase.getInstance().addEnteredToll(vehicle.getPlate(), this);
		}
	}
	
	public void removeVehicle(Vehicle vehicle){
		this.vehicles.remove(vehicle);
		if(isMonitored()){
			DataBase.getInstance().addExitedToll(vehicle.getPlate());
		}
	}
	
	public Set<Vehicle> getVehicles(){
		return this.vehicles;
	}
	
	public double getSpeedLimit(){
		return this.speedlimit;
	}
	
}
