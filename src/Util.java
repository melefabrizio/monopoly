import java.util.Random;
import java.util.Vector;


public class Util {
	
	public static Integer[] lanciaDadi(int n, int f){
		
		Vector<Integer> dadi = new Vector<Integer>();
		Random r = new Random();
	
		for(int i = 0;i<n;i++){
			dadi.add(r.nextInt(f)+1);
		}
		return dadi.toArray(new Integer[0]);
	}

}
