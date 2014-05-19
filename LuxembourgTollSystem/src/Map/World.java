package Map;

import java.util.Vector;

import LTS.GraphicalUserInterface;

/**
 * Singleton class representing simulation of real world 
 *
 */
public class World implements Runnable {

	private static World 					instance = null;
	private Vector<Checkpoint> 				checkpoints; // List of all nodes (crossroads/road connections) on the map
	private double 							timestep; // Amount of time between two system updates

	public static World getInstance() {
		if (instance == null)
			instance = new World();
		return instance;
	}

	private World() {
		checkpoints = new Vector<>();
		timestep = 1.0 / 60; // 1 second
		createGraph();
	};

	/**
	 * World simulates real world, running in background, allowing user to ask queries in real time
	 */
	@Override
	public void run() {
		// System.out.println(Map.getInstance().toString());
		long startTime = System.currentTimeMillis();
		// int numCars;
		while (true) {
			long endTime = System.currentTimeMillis();
			if (endTime - startTime > 1000) {
				startTime = endTime;
				World.getInstance().updateState();
				/*
				 * numCars = 0; for(Checkpoint c :
				 * Map.getInstance().checkpoints){ for(Road r : c.getRoads()){
				 * numCars += r.getVehicles().size(); } }
				 * System.out.println("NUM CARS: " + numCars);
				 */
			}
		}
	}
	/**
	 * Method responsible for generating graph representing roads and crossroads (checkpoints)
	 */
	private void createGraph() {

		int roadCounter = 0;

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

	private void updateState() {
		for (Checkpoint c : checkpoints) {
			c.updateState();
		}
	}

	public double getTimeStep() {
		return timestep;
	}

	public static void main(String[] args) {
		Thread t = new Thread( World.getInstance() );
		t.start();
		new GraphicalUserInterface();
	}


}
