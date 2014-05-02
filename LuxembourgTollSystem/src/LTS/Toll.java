package LTS;

import java.util.Date;

import Map.Road;

public class Toll {
	
	Road road;
	double gold;
	String plate;
	Date date;
	
	Toll(Road road,String plate){
		this.road = road;
		this.gold = road.getCost();
		this.plate = plate;
		this.date = new Date();
	}
	
}
