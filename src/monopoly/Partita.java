package monopoly;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;


/**
 * La classe che rappresenta una Partita.
 */
public class Partita implements MovementListener{
	
	public static final int IMPORTO_INIZIALE = 5000;

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
	 * @param db il database corrente dell'applicazione
	 * @param giocatori i giocatori partecipanti
	 * @throws SQLException l'eccezione SQL lanciata
	 */
	public Partita( DBManager db, Vector<Giocatore> giocatori) throws SQLException{
		this.db = db;
		this.giocatori = giocatori;
		tabellone = new Tabellone(db);
		iterator = this.giocatori.iterator(); 
		for(Giocatore giocatore:this.giocatori){
			tabellone.posiziona(giocatore, 0);
		}
		
	}
	
	/**
	 * Il metodo turno, che rappresenta il turno di un giocatore.
	 * Fa lanciare i dadi al giocatore e lo fa avanzare della quantitˆ corrispondente.
	 * Controlla se i dadi sono uguali, e in caso di risposta affermativa fa rilanciare i dadi.
	 * Se si tirano dadi doppi per tre volte il metodo sposta il giocatore in prigione.
	 * 
	 */
	public void turno(){
		
		

		int avanzamento = 0;
		int ripetizione = 0;
		boolean ritira=false;
		Giocatore gCorrente;
		try{
			gCorrente = iterator.next();
		}catch (Exception e){
			this.iterator = this.giocatori.iterator();
			gCorrente = iterator.next();
		}


		do{
			Integer[] dadi = gCorrente.lanciaDadi();
			ritira = false;
			avanzamento = 0;
			avanzamento += dadi[0];
			avanzamento += dadi[1];
			
			
			System.out.print("[" +gCorrente.getNome()+"]"+" lancia i dadi ed escono:  ");
			System.out.print(+dadi[0]+" e "+dadi[1]+". ");
			System.out.print(dadi[0] == dadi[1]?"Dadi doppi!\n":"\n");

			System.out.print("Si muove da ");
			System.out.print(
					tabellone.getCasella(gCorrente).getNome()+"["+tabellone.getCasella(gCorrente).getId()+"]");
			
			tabellone.avanza(gCorrente, avanzamento);
			
			System.out.print(" a ");
			System.out.print(
					tabellone.getCasella(gCorrente).getNome()+"["+tabellone.getCasella(gCorrente).getId()+"]. \n\n");
			
			if(dadi[0]==dadi[1]){
				ritira = true;
				ripetizione++;
				
				System.out.println("Ritira!");
			}
			if(ripetizione == 3){
				tabellone.sposta(gCorrente, Tabellone.PRIGIONE);
				System.out.println("In prigione!");
				ritira=false;
			}
		}while(ritira);
	}

	@Override
	public void onHop(Giocatore g, Casella c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStop(Giocatore g, Casella c) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
