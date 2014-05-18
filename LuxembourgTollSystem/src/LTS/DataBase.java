package LTS;


import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import Map.Road;



public class DataBase {

	/**
	 * This singleton is used to store all Toll.
	 * Constructor of this class is private, to get instance use 'DataBase.getInstance()'.
	 * 
	 */
	private static DataBase 	instance = null;
	Map<String,Toll> 					unfinished;
	Set<Toll> 								finished;
	
	
	private DataBase(){
		unfinished = new HashMap<String,Toll>();
		finished = new HashSet<Toll>();
	}
	
	public static DataBase getInstance(){
		if(instance == null) instance = new DataBase();
		return instance;
	}
	
	/**
	 * This function add new Toll to unfinished, with plate of the vehicle as key.
	 * This function inform user of appearance of two identical plates at the same time.
	 * @param plate - Plate of the vehicle.
	 * @param road - Road on which vehicle is.
	 */
	public void addEnteredToll(String plate, Road road){
		Toll toll = new Toll(road,plate);
		
		if(unfinished.containsKey(plate)){
			JOptionPane.showMessageDialog(null, "HUEHUE: Car with plate: " + plate + " appear more than once!1!one!1two");
			//TODO Some kind of action...
		}
		unfinished.put(toll.getPlate(), toll);
	}
	
	/**
	 * This function transfer Toll of given plate from unfinished to finished. 
	 * This function inform user if there is no Toll of given plate in unfinished.
	 * @param plate - Plate of the vehicle.
	 */
	public void addExitedToll(String plate){
		try {
			Toll toll = unfinished.get(plate);
			unfinished.remove(plate);
			toll.setExitDate(new Date());
			finished.add(toll);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "HUEHUE: Car with plate: " + plate + " tried to exit even though there is no such car!!1!1onwtwo!1!");
			//TODO Some kind of action...
		}
	}
	
	public Set<Toll> getFinished(){
		return this.finished;
	}
	
	public Map<String, Toll> getUnfinished(){
		return this.unfinished;
	}
	
}
