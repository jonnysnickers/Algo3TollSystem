package Map;

import java.util.Random;
import java.util.Vector;

import LTS.GraphicalUserInterface;

public class Map implements Runnable {

	private static Map 					instance = null;
	private boolean 						END_SIMULATION;
	private Vector<Checkpoint> 	checkpoints; // List of all nodes (crossroads/road connections) on the map
	private double 							timestep; // Amount of time between two system updates

	public static Map getInstance() {
		if (instance == null)
			instance = new Map();
		return instance;
	}

	private Map() {
		setEND_SIMULATION(false);
		checkpoints = new Vector<>();
		timestep = 1.0 / 60; // 1 second
		createGraph();
	};

	@Override
	public void run() {
		// System.out.println(Map.getInstance().toString());
		long startTime = System.currentTimeMillis();
		// int numCars;
		while (!isEND_SIMULATION()) {
			long endTime = System.currentTimeMillis();
			if (endTime - startTime > 1000) {
				startTime = endTime;
				Map.getInstance().updateState();
				/*
				 * numCars = 0; for(Checkpoint c :
				 * Map.getInstance().checkpoints){ for(Road r : c.getRoads()){
				 * numCars += r.getVehicles().size(); } }
				 * System.out.println("NUM CARS: " + numCars);
				 */
			}
		}
	}

	private void createGraph() {

		int roadCounter = 0;
		Random rand = new Random();

		// Add nodes
		for (int i = 0; i < 15; i++) {
			Checkpoint c = new Checkpoint(i, i % 3, i / 3, i < 3 || i > 12);
			checkpoints.add(c);
		}

		/*
		 * 0 1 2 | | | 3--4--5 | | | 6--7--8 | | | 9-10-11 | | | 12 13 14
		 */

		// Add roads
		for (int i = 0; i < 15; i++) {
			Checkpoint start, end;
			start = checkpoints.get(i);

			// Roads leading south
			if (i < 12) {
				end = checkpoints.get(i + 3);
				Road r = new Road(roadCounter++, start, end);
				start.addRoad(r);
			}
			// Roads leading north
			if (i > 3) {
				end = checkpoints.get(i - 3);
				Road r = new Road(roadCounter++, start, end);
				start.addRoad(r);
			}
			// Roads leading east
			if ((i % 3 - 2) != 0 && i > 2 && i < 12) {
				end = checkpoints.get(i + 1);
				Road r = new Road(roadCounter++, start, end);
				start.addRoad(r);
			}
			// Roads leading west
			if (i % 3 != 0 && i > 2 && i < 12) {
				end = checkpoints.get(i - 1);
				Road r = new Road(roadCounter++, start, end);
				start.addRoad(r);
			}
		}

	}

	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("MAP: \n");
		for (Checkpoint c : checkpoints) {
			res.append(c.toString() + "\n");
		}
		return res.toString();
	}

	// private void startClock() {
	// long startTime = System.currentTimeMillis();
	// while(true){
	// long endTime = System.currentTimeMillis();
	// if(endTime - startTime > 1000){
	// startTime = endTime;
	// updateState();
	// }
	// }
	// }

	private void updateState() {
		for (Checkpoint c : checkpoints) {
			c.updateState();
		}
	}

	public double getTimeStep() {
		return timestep;
	}

	private boolean isEND_SIMULATION() {
		return END_SIMULATION;
	}
	
	private void setEND_SIMULATION(boolean eND_SIMULATION) {
		END_SIMULATION = eND_SIMULATION;
	}

	public static void main(String[] args) {
		Thread t = new Thread( Map.getInstance() );
		t.start();
		new GraphicalUserInterface();
	}


}
