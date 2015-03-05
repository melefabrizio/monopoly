package monopoly;

import java.util.Random;
import java.util.Vector;



/**
 * La classe Util.
 */
public class Util {
	
	/**
	 * Metodo che lancia i dadi
	 *
	 * @param n il numero di dadi
	 * @param f il numero di facce
	 * @return integer[] il vettore di interi che contiene i risultati del lancio
	 */
	public static Integer[] lanciaDadi(int n, int f){
		
		Vector<Integer> dadi = new Vector<Integer>();
		Random r = new Random();
	
		for(int i = 0;i<n;i++){
			dadi.add(r.nextInt(f)+1);
		}
		return dadi.toArray(new Integer[0]);
	}
	
	

}
