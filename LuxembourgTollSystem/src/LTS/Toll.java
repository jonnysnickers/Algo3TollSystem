package LTS;

import java.util.Date;

import Map.Road;

public class Toll {
	/**
	 * Object of this class store information about one Toll. road,gold,plate and
	 * enterDate are set in constructor and can not be changed. It is possible to
	 * change exitDate only if it was null before. To easily write information
	 * about Toll use toString() function.
	 */

	Road 		road;
	double 	gold;
	String 	plate;
	Date 		enterDate;
	Date 		exitDate;

	Toll(Road road, String plate) {
		this.road = road;
		this.gold = road.getCost();
		this.plate = plate;
		this.enterDate = new Date();
		this.exitDate = null;
	}

	@Override
	public String toString() {
		return road.getId() + "\t" + Math.round(gold * 100) / 100.0 + "\t" + plate + "\t" + enterDate + "\t" + exitDate + "\n";
	}

	public void setExitDate(Date date) {
		if (this.exitDate == null) {
			this.exitDate = date;
		}
	}

	public Road getRoad() {
		return this.road;
	}

	public double getGold() {
		return this.gold;
	}

	public String getPlate() {
		return this.plate;
	}

	public Date getEnterDate() {
		return this.enterDate;
	}

	public Date getExitDate() {
		return this.exitDate;
	}
}
