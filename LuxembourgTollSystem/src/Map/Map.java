package Map;

import java.util.Random;
import java.util.Vector;

import Map.Checkpoint.Checkpoint;
import Vehicle.Vehicle;

public class Map {

	private static Map 				instance = null;
	private Vector<Checkpoint> 		checkpoints;	//List of all nodes (crossroads/road connections) on the map
	//private Vector<Road> 			roads;			//list of all roads on the map
	private Vector<Vehicle> 		vehicles; 		//List of all vehicles that are currently on map
	private double 					timestep; 		//Amount of time between two system updates 

	public static Map getInstance(){
		if(instance == null) instance = new Map();
		return instance;
	}
	
	private Map(){
		createGraph();
	};
				
	private void createGraph(){
		checkpoints = new Vector<>();
		int roadCounter = 0;
		Random rand = new Random();
		
		//Add nodes
		for(int i=0; i<15; i++){
			Checkpoint c = new Checkpoint(i,i%3,i/3,i<3 || i >12);
			checkpoints.add(c);
		}
		
		/*
		 * 				0  1  2
		 * 				|  |  |
		 * 				3--4--5
		 * 				|  |  |
		 * 				6--7--8
		 *  			|  |  |
		 * 				9-10-11
		 *  			|  |  |
		 * 			   12  13 14
		 */
		
		//Add roads
		for(int i=0; i<15; i++){
			Checkpoint start, end;
			start = checkpoints.get(i);
			//Roads leading south
			if(i<12){
				end = checkpoints.get(i+3);
				//TODO parameters set to constant: cost = 10, monitored = false, length = 1, speedLimit=1
				Road r = new Road(roadCounter++,10.0,start,end,false,1,1);
			}
			//Roads leading north
			if(i>3){
				end = checkpoints.get(i-3);
				//TODO parameters set to constant: cost = 10, monitored = false, length = 1, speedLimit=1
				Road r = new Road(roadCounter++,10.0,start,end,false,1,1);
			}
			//Roads leading east
			if((i%3-2)!=0){
				end = checkpoints.get(i+1);
				//TODO parameters set to constant: cost = 10, monitored = false, length = 1, speedLimit=1
				Road r = new Road(roadCounter++,10.0,start,end,false,1,1);
			}
			//Roads leading west
			if(i%3!=0){
				end = checkpoints.get(i-1);
				//TODO parameters set to constant: cost = 10, monitored = false, length = 1, speedLimit=1
				Road r = new Road(roadCounter++,10.0,start,end,false,1,1);
			}
		}
		
	}
	
	public double getTimeStep(){
		return timestep;
	}
}
