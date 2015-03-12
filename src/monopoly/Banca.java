/**
 * 
 */
package monopoly;

import java.util.Vector;

/**
 * Classe statica che permette di gestire le transazioni monetarie tra giocatori e banca.
 * @author Fabrizio Mele
 *
 */
public class Banca {
	
	/**
	 * Metodo per eseguire un versamento alla banca da parte del giocatore
	 * 
	 * @param g 
	 * 			Il giocatore dal quale prelevare l'importo 
	 * @param importo
	 * 			L'importo da versare alla banca.
	 * @throws FallimentoException 
	 */
	public static void versamento(Giocatore g, int importo) throws FallimentoException{
		g.setCapitale(g.getCapitale()-importo);
		checkFallimento(g);
	}
	/**
	 * Metodo per eseguire un prelievo dalla banca da parte del giocatore
	 * 
	 * @param g 
	 * 			Il giocatore al quale accreditare l'importo 
	 * @param importo
	 * 			L'importo da prelevare dalla banca.
	 */
	public static void prelievo(Giocatore g, int importo){
		g.setCapitale(g.getCapitale()+importo);
	}
	
	/**
	 * Metodo per effettuare un trasferimento di denaro tra due giocatori.
	 * @param mittente
	 * 			Il giocatore dal quale prelevare
	 * @param destinatario
	 * 			Il giocatore al quale versare
	 * @param importo
	 * 			L'ammontare del trasferimento
	 * @throws FallimentoException 
	 */
	public static void trasferimento(Giocatore mittente, Giocatore destinatario, int importo) throws FallimentoException{
		versamento(mittente, importo);
		prelievo(destinatario, importo);
		checkFallimento(mittente);

	}
	
	/**
	 * Metodo statico per assegnare a ciascun giocatore l'importo iniziale stabilito dalla Partita. 
	 * Utilizza un campo statico nella classe Partita.
	 * @param giocatori
	 * 			I giocatori interessati dall'operazione 
	 */
	public static void inizializzaGiocatori(Vector<Giocatore> giocatori){
		for(Giocatore g:giocatori){
			g.setCapitale(Partita.IMPORTO_INIZIALE);
		}
	}
	
	public static void checkFallimento(Giocatore g) throws FallimentoException{
		if(g.getCapitale()<=0)
			throw new FallimentoException(g);
	}

}
