/**
 * 
 */
package monopoly;

import java.util.Vector;

/**
 * @author Fabrizio Mele
 *
 */
public class Banca {
	
	public static void versamento(Giocatore g, int importo){
		g.setCapitale(g.getCapitale()-importo);
	}
	
	public static void prelievo(Giocatore g, int importo){
		g.setCapitale(g.getCapitale()+importo);
	}
	
	public static void trasferimento(Giocatore m, Giocatore d, int importo){
		prelievo(m, importo);
		versamento(d, importo);
	}
	
	public static void inizializzaGiocatori(Vector<Giocatore> giocatori){
		for(Giocatore g:giocatori){
			g.setCapitale(Partita.IMPORTO_INIZIALE);
		}
	}

}
