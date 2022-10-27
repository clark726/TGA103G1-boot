
package TGA103G1boot.common;

import java.util.Random;

public class Ramdon {
	
	public String getRandom() {
		
		Random rnd = new Random();
		int number = rnd.nextInt(999999);
		
		return String.format("%04d", number);
	}
	

}