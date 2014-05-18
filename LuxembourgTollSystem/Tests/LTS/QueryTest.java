package LTS;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Test;

import Map.Checkpoint;
import Map.Road;

public class QueryTest {

	
	@Test
	public void doQueryTest() throws ParseException{
		String res;
		DataBase db = DataBase.getInstance();
		OurMagicGenericQuery OMGQuery = new OurMagicGenericQuery();
		
		
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
		
		for(int i=0;i<1500;i++){
			//System.out.println(RandomHumanQuryGenerator.getRandomQuery());
			res = OMGQuery.doQuery(RandomHumanQuryGenerator.getRandomQuery());
		}
		
		res = OMGQuery.doQuery("SELECT(road=1)");
		System.out.println("SELECT(road=1)\n" + res);
		
		res = OMGQuery.doQuery("SELECT(plate<AA6&plate>AA3)");
		System.out.println("SELECT(plate<AA6&plate>AA3)\n" + res);
		
		res = OMGQuery.doQuery("SELECT(enterDate>2000-10-10T10:10:10&enterDate<2100-10-10T10:10:10)");
		System.out.println("SELECT(enterDate>2000-10-10T10:10:10&enterDate<2100-10-10T10:10:10)\n" + res);
		
		res = OMGQuery.doQuery("SELECT(exitDate>2000-10-10T10:10:10&exitDate<2100-10-10T10:10:10)");
		System.out.println("SELECT(exitDate>2000-10-10T10:10:10&exitDate<2100-10-10T10:10:10)\n" + res);
		
		res = OMGQuery.doQuery("SELECT()GROUP()SUM");
		System.out.println("SELECT()GROUP()SUM\n" + res);
		
		res = OMGQuery.doQuery("SELECT()GROUP()COUNT");
		System.out.println("SELECT()GROUP()COUNT\n" + res);
		
		res = OMGQuery.doQuery("SELECT()GROUP()MIN(road)");
		System.out.println("SELECT()GROUP()MIN(road)\n" + res);
		
		res = OMGQuery.doQuery("SELECT()GROUP()MIN(enterDate)");
		System.out.println("SELECT()GROUP()MIN(enterDate)\n" + res);
		
		res = OMGQuery.doQuery("SELECT()GROUP()MIN(exitDate)");
		System.out.println("SELECT()GROUP()MIN(exitDate)\n" + res);
		
		res = OMGQuery.doQuery("SELECT()GROUP()MIN(plate)");
		System.out.println("SELECT()GROUP()MIN(plate)\n" + res);
	
		res = OMGQuery.doQuery("SELECT()GROUP()MIN(gold)");
		System.out.println("SELECT()GROUP()MIN(gold)\n" + res);
		
		
		res = OMGQuery.doQuery("SELECT()GROUP()MAX(road)");
		System.out.println("SELECT()GROUP()MAX(road)\n" + res);
		
		res = OMGQuery.doQuery("SELECT()GROUP()MAX(enterDate)");
		System.out.println("SELECT()GROUP()MAX(enterDate)\n" + res);
		
		res = OMGQuery.doQuery("SELECT()GROUP()MAX(exitDate)");
		System.out.println("SELECT()GROUP()MAX(exitDate)\n" + res);
		
		res = OMGQuery.doQuery("SELECT()GROUP()MAX(plate)");
		System.out.println("SELECT()GROUP()MAX(plate)\n" + res);
	
		res = OMGQuery.doQuery("SELECT()GROUP()MAX(gold)");
		System.out.println("SELECT()GROUP()MAX(gold)\n" + res);
		
		
		//assertEquals("NoGrouping: ", result);
		
	}
}
