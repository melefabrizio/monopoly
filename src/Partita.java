import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Vector;

import com.google.common.collect.Iterators;


/**
 * La classe che rappresenta una Partita.
 */
public class Partita {
	
	/** Il Database */
	private DBManager db;
	
	/** Il vector di giocatori. */
	private Vector<Giocatore> giocatori;
	
	/** Il tabellone. */
	private Tabellone tabellone;
	
	/** L'iterator dei giocatori. */
	private Iterator<Giocatore> iterator;
	
	/**
	 * Costruttore di partita.
	 *
	 * @param db il database
	 * @param giocatori i giocatori
	 * @throws SQLException l'eccezione SQL lanciata
	 */
	public Partita( DBManager db, Vector<Giocatore> giocatori) throws SQLException{
		this.db = db;
		this.giocatori = giocatori;
		tabellone = new Tabellone(db);
		iterator = Iterators.cycle(this.giocatori);
		
	}
	
	/**
	 * Il metodo turno, che rappresenta il turno di un giocatore.
	 * Fa lanciare i dadi al giocatore e lo fa avanzare della quantità corrispondente.
	 * Controlla se i dadi sono uguali, e in caso di risposta affermativa fa rilanciare i dadi.
	 * Se si tirano dadi doppi per tre volte il metodo sposta il giocatore in prigione.
	 * 
	 */
	public void turno(){
		
		

		int avanzamento = 0;
		int ripetizione = 0;
		boolean ritira=false;
		Giocatore gCorrente = iterator.next();
		System.out.println("Tocca al giocatore "+gCorrente.getNome());
		System.out.println("Parte dalla casella "+
				tabellone.getCasella(gCorrente).getNome());
		

		do{
			Integer[] dadi = gCorrente.lanciaDadi();
		
			
			avanzamento += dadi[0];
			avanzamento += dadi[1];
			
			System.out.println(gCorrente.getNome()+" lancia "+avanzamento);
			tabellone.avanza(gCorrente, avanzamento);
			System.out.println("Arriva alla casella "+
					tabellone.getCasella(gCorrente).getNome());
			if(dadi[0]==dadi[1]){
				ritira = true;
				ripetizione++;
				avanzamento = 0;
			}
			if(ripetizione == 3){
				tabellone.sposta(gCorrente, Tabellone.PRIGIONE);
				System.out.println("In prigione!");
			}
		}while(ritira);
	}
	
	
	
}
