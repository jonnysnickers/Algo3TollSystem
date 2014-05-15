package LTS;

import java.util.Date;

import Map.Road;

public class Toll {
	
	Road 		road;
	double 	gold;
	String 	plate;
	Date 		enterDate;
	Date		exitDate;
	
	Toll(Road road,String plate){
		this.road = road;
		this.gold = road.getCost();
		this.plate = plate;
		this.enterDate = new Date();
		this.exitDate = null;
	}
	
	public void setExitDate(Date date){
		this.exitDate = date;
	}
	
}
