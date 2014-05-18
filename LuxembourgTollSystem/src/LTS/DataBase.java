package LTS;


import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import Map.Road;



public class DataBase {

	private static DataBase instance = null;
	
	Map<String,Toll> unfinished;
	Set<Toll> finished;
	
	
	private DataBase(){
		unfinished = new HashMap<String,Toll>();
		finished = new HashSet<Toll>();
	}
	
	public static DataBase getInstance(){
		if(instance == null) instance = new DataBase();
		return instance;
	}
	
	public void addEnteredToll(String plate, Road road){
		Toll toll = new Toll(road,plate);
		
		if(unfinished.containsKey(plate)){
			JOptionPane.showMessageDialog(null, "HUEHUE: Car with plate: " + plate + " appear more than once!1!one!1two");
			//TODO
		}
		
		unfinished.put(toll.getPlate(), toll);
	}
	
	public void addExitedToll(String plate){
		
		try {
			Toll toll = unfinished.get(plate);
			unfinished.remove(plate);
			toll.setExitDate(new Date());
			finished.add(toll);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "HUEHUE: Car with plate: " + plate + " tried to exit even though there is no such car!!1!1onwtwo!1!");
			//TODO
		}
	}
	
}
