import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Vector;

import com.google.common.collect.Iterators;


/**
 * La classe che rappresenta il tabellone.
 */
public class Tabellone {
	
	/** La costante VIA. */
	public static final int VIA = 0;
	
	/** La costante PRIGIONE. */
	public static final int PRIGIONE = 10;

	/** Le caselle. */
	private LinkedList<Casella> caselle;
	
	/** Il database. */
	private DBManager db;
	
	/**
	 * Il tabellone deve essere istanziato passandogli il database generale dell'applicazione.
	 *
	 * @param db il database generale dell'applicazione
	 * @throws SQLException l'eccezione SQL lanciata
	 */
	public Tabellone(DBManager db) throws SQLException{
		this.db = db;
		this.caselle = db.getCaselle();
	}
	
	/**
	 * Il metodo che fa avanzare un giocatore, prende la posizione corrente del giocatore, lo rimuove da quella 
	 * e lo inserisce nella nuova posizione.
	 *
	 * @param g il giocatore
	 * @param avanzamento quantità di avanzamento del giocatore
	 */
	protected void avanza(Giocatore g, int avanzamento){
		
		int posCorrente = g.getNumeroCasella();
		int newPos = posCorrente+avanzamento;
		Iterator<Casella> iterator = caselle.iterator();
		while(iterator.hasNext()){
			Casella casella = iterator.next();
			if(casella.staziona(g)){
				casella.rimuovi(g);
				
				
			}
			if(casella.getId()==newPos){
				casella.inserisci(g);
			}
			
		}
		
		g.setNumeroCasella(newPos);
		
		
	}
	
	/**
	 * Metodo che sposta il giocatore.
	 *
	 * @param g the g
	 * @param newCasella the new casella
	 */
	protected void sposta(Giocatore g, int newCasella){
		
		g.setNumeroCasella(newCasella);
		Iterator<Casella> iterator = Iterators.cycle(caselle);
		while(iterator.hasNext()){
			Casella casella = iterator.next();
			if(casella.staziona(g)){
				casella.rimuovi(g);
				
				
			}
			if(casella.getId()==newCasella){
				casella.inserisci(g);
			}
			
		}
		
	}
	
	/**
	 * Get di casella.
	 *
	 * @param g il giocatore
	 * @return la casella
	 */
	public Casella getCasella(Giocatore g){
		
		for(Casella casella:caselle){
			
			if(casella.staziona(g)){
				return casella;
				
				
			}
			
			
		}
		return caselle.getFirst();
	}

}
