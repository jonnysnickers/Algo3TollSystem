package LTS;

import java.text.ParseException;

import org.junit.Test;

import Map.Checkpoint;
import Map.Road;

public class QueryTest {

	
	@Test
	public void doQueryTest() throws ParseException{
		
		DataBase db = DataBase.getInstance();
		
		db.addEnteredToll("AA1", new Road(1,new Checkpoint(1, 1, 1, false),new Checkpoint(1, 1, 1, false)));
		db.addExitedToll("AA1");
		db.addEnteredToll("AA2", new Road(1,new Checkpoint(1, 1, 1, false),new Checkpoint(1, 1, 1, false)));
		db.addExitedToll("AA2");
		db.addEnteredToll("AA3", new Road(1,new Checkpoint(1, 1, 1, false),new Checkpoint(1, 1, 1, false)));
		db.addExitedToll("AA3");
		db.addEnteredToll("AA4", new Road(1,new Checkpoint(1, 1, 1, false),new Checkpoint(1, 1, 1, false)));
		db.addExitedToll("AA4");
		db.addEnteredToll("AA4", new Road(1,new Checkpoint(1, 1, 1, false),new Checkpoint(1, 1, 1, false)));
		db.addExitedToll("AA4");
		db.addEnteredToll("AA4", new Road(1,new Checkpoint(1, 1, 1, false),new Checkpoint(1, 1, 1, false)));
		db.addExitedToll("AA4");
		db.addEnteredToll("AA5", new Road(1,new Checkpoint(1, 1, 1, false),new Checkpoint(1, 1, 1, false)));
		db.addExitedToll("AA5");
		db.addEnteredToll("AA6", new Road(1,new Checkpoint(1, 1, 1, false),new Checkpoint(1, 1, 1, false)));
		db.addExitedToll("AA6");
		db.addEnteredToll("AA6", new Road(1,new Checkpoint(1, 1, 1, false),new Checkpoint(1, 1, 1, false)));
		db.addExitedToll("AA6");
		db.addEnteredToll("AA6", new Road(1,new Checkpoint(1, 1, 1, false),new Checkpoint(1, 1, 1, false)));
		db.addExitedToll("AA6");
		db.addEnteredToll("AA7", new Road(1,new Checkpoint(1, 1, 1, false),new Checkpoint(1, 1, 1, false)));
		db.addExitedToll("AA7");
		db.addEnteredToll("AA8", new Road(1,new Checkpoint(1, 1, 1, false),new Checkpoint(1, 1, 1, false)));
		db.addExitedToll("AA8");		
		db.addEnteredToll("AA8", new Road(1,new Checkpoint(1, 1, 1, false),new Checkpoint(1, 1, 1, false)));
		db.addExitedToll("AA8");	
		
		
		OurMagicGenericQuery OMGQuery = new OurMagicGenericQuery();
		OMGQuery.doQuery("SELECT(plate>A&gold<1000)GROUP()MAX(gold)");
		
	}
}
