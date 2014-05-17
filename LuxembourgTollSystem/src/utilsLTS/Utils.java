package utilsLTS;

import java.util.Random;

public abstract class Utils {

	/**
	 * Generates random string of given length
	 * @param len - length of string
	 * @return
	 */
	public static String getRandomString(int len){
		StringBuilder string = new StringBuilder();
		Random rand = new Random();
		
		char letter;
		for(int i=0; i<len; i++){
			letter = (char)( (int)'A' + rand.nextInt(26) );
			string.append(letter);
		}
		
		return string.toString();
	}
	
}