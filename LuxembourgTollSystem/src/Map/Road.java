package Map;

import java.util.HashSet;
import java.util.Set;

import Map.Checkpoint.Checkpoint;
import Vehicle.Vehicle;

public class Road {

	private int 					id;
	private double 				cost;
	private boolean				monitored;
	private Checkpoint 		start;
	private Checkpoint 		end;
	private double 				length;
	private Set<Vehicle> 	vehicles;
	private double 				speedlimit;
	
	
	public Road(double cost, Checkpoint start, Checkpoint end,Boolean monitored, double length, double speedlimit){
		this.id = 1;
		this.monitored = monitored;
		this.cost = cost;
		this.start = start;
		this.end = end;
		this.length = length;
		this.vehicles = new HashSet<Vehicle>();
		this.speedlimit = speedlimit;
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
	}
	
	public void removeVehicle(Vehicle vehicle){
		this.vehicles.remove(vehicle);
	}
	
	public Set<Vehicle> getVehicles(){
		return this.vehicles;
	}
	
	public double getSpeedLimit(){
		return this.speedlimit;
	}
	
}
