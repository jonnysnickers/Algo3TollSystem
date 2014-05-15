package LTS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OurMagicGenericQuery {
	
	
	public void readQuery(){
		
		String query = "";
		//wczytywanie
		
		
	}
	
	public Set<Toll> select(String con) throws ParseException{
		
		String[] conditions = con.split("|");
		
		Set<Toll> result = new HashSet<Toll>();
		
		for (Map.Entry<String, Toll> entry : DataBase.getInstance().finished.entrySet()) {
			Toll toll = entry.getValue();
			
			Boolean pass = true;
			for(String condition : conditions){
				
				pass = true;
				char operation;
				String[] tmp;
				
				if(condition.contains("=")){
					operation = '=';
					 tmp = condition.split("=");
				} else if(condition.contains(">")){
					operation = '>';
					 tmp = condition.split(">");
				} else {
					operation = '<';
					 tmp = condition.split("<");
				}
				
				if(tmp[0].equals("road")){
					if(!tmp[1].equals(((Integer)toll.getRoad().getId()).toString())){
						pass = false;
					}
				} else if(tmp[0].equals("gold")){
					if(operation == '>' && Double.parseDouble(tmp[1]) < toll.getGold()){
						pass = false;
					}
					if(operation == '<' && Double.parseDouble(tmp[1]) > toll.getGold()){
						pass = false;
					}
					if(operation == '=' && Double.parseDouble(tmp[1]) != toll.getGold()){
						pass = false;
					}
				} else if(tmp[0].equals("plate")){
					if(operation == '>' && (tmp[1].compareTo(toll.getPlate()) < 0 )){
						pass = false;
					}
					if(operation == '<' && (tmp[1].compareTo(toll.getPlate()) > 0 )){
						pass = false;
					}
					if(operation == '=' && !tmp[1].equals(toll.getPlate())){
						pass = false;
					}
				}	else if(tmp[0].equals("enterDate")){
					if(operation == '>' && (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(tmp[1]).compareTo(toll.getEnterDate()) < 0) ){
						pass = false;
					}
					if(operation == '<' && (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(tmp[1]).compareTo(toll.getEnterDate()) > 0)){
						pass = false;
					}
					if(operation == '=' && (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(tmp[1]).compareTo(toll.getEnterDate()) != 0)){
						pass = false;
					}
				} else if(tmp[0].equals("exitDate")){
					if(operation == '>' && (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(tmp[1]).compareTo(toll.getExitDate()) < 0) ){
						pass = false;
					}
					if(operation == '<' && (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(tmp[1]).compareTo(toll.getExitDate()) > 0)){
						pass = false;
					}
					if(operation == '=' && (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(tmp[1]).compareTo(toll.getExitDate()) != 0)){
						pass = false;
					}
				}
			}
			
			if(pass){
			result.add(toll);
			}
		}
		return result;
	}
	
}
