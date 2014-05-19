package LTS;

import java.util.Random;

public class RandomHumanQuryGenerator {

	static Random rand = new Random();
	
	static String[] fields 	= 	{"gold", "road", "plate", "enterDate", "exitDate"};
	static String[] operator =	{"=", ">", "<"};
	static String[] grouping = 	{"","GROUP()","GROUP(gold)","GROUP(road)","GROUP(plate)","GROUP(enterDate)","GROUP(exitDate)"};
	static String[] command = 	{"","SUM","COUNT","MIN(gold)","MAX(gold)","MIN(road)","MAX(road)","MIN(plate)","MAX(plate)","MIN(enterDate)","MAX(enterDate)","MIN(exitDate)","MAX(exitDate)"};
	
	public static String getRandomQuery(){
		StringBuilder query = new StringBuilder("SELECT(");
		
		/*
		int numberOfCon = rand.nextInt(5);
		for(int i=0;i<numberOfCon;i++){
			query.append(fields[rand.nextInt(5)] + operator[rand.nextInt(3)]);
		}
		*/
		
		query.append(")");
		query.append(grouping[rand.nextInt(7)]);
		query.append(command[rand.nextInt(13)]);
		
		return query.toString();
	}
	
}
