package LTS;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import Map.Road;



public class DataBase {

	private static DataBase instance = null;
	
	Map<String,Toll> unfinished;
	Map<String,Toll> finished;
	
	
	private DataBase(){
		unfinished = new HashMap<String,Toll>();
		finished = new HashMap<String,Toll>();
	}
	
	public static DataBase getInstance(){
		if(instance == null) instance = new DataBase();
		return instance;
	}
	
	public void addEnteredToll(String plate, Road road){
		Toll toll = new Toll(road,plate);
		
		if(unfinished.containsKey(plate)){
			JOptionPane.showMessageDialog(null, "HUEHUE");
			//TODO
		}
		
		unfinished.put(toll.getPlate(), toll);
	}
	
	public void addExitedToll(String plate){
		Toll toll = unfinished.get(plate);
		unfinished.remove(plate);
		toll.setExitDate(new Date());
		finished.put(toll.getPlate(),toll);
	}
	
}
