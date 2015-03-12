package monopoly;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

/**
 * La classe che rappresenta il tabellone.
 */
public class Tabellone {

	/** La costante VIA. */
	public static final int VIA = 0;

	/** La costante PRIGIONE. */
	public static final int PRIGIONE = 10;
	
	public static final int IN_PRIGIONE = 30;
	
	public static final int T_PATRIMONIALE = 4;
	public static final int T_LUSSO= 38;

	public static final int T_LUSSO_I = 10;
	public static final int T_PATRIMONIALE_I = 250;

	/** Le caselle. */
	private LinkedList<Casella> caselle;

	/** Il database. */
	private DBManager db;

	/**
	 * Tabellone deve essere istanziata passandogli il database generale
	 * dell'applicazione.
	 *
	 * @param db
	 *            l'istanza corrente del database
	 * @throws SQLException
	 *             l'eccezione SQL lanciata
	 */
	public Tabellone(DBManager db) throws SQLException {
		this.db = db;
		this.caselle = db.getCaselle();
		for(Casella c:caselle){
			c.setTabellone(this);
		}
	}

	/**
	 * Metodo che muove un giocatore di una quantitˆ intera di caselle. 
	 *
	 * @param g
	 *            Il giocatore
	 * @param avanzamento
	 *            Avanzamento in caselle. Se negativo retrocessione.
	 */
//	protected void avanza(Giocatore g, int avanzamento) {
//
//		int posCorrente = g.getNumeroCasella();
//		int newPos = posCorrente + avanzamento;
//		newPos = newPos > 40 ? newPos - 40 : newPos;
//		sposta(g, newPos);
//
//	}
	public void avanza(Giocatore g, int avanzamento) {

		int posCorrente = g.getNumeroCasella();
		int newPos = posCorrente + avanzamento;
		if(newPos >=40){
			newPos=newPos-40;
		}
		sposta(g, newPos);

	}
	
	public void setMovementListener(MovementListener l){
		for(Casella casella:caselle){
			casella.setMovementListener(l);
		}
	}
	/**
	 * Sposta il giocatore ad una nuova casella.
	 *
	 * @param g
	 *            Il giocatore da spostare
	 * @param newCasella
	 *            L'id della casella di destinazione 
	 */
	private void sposta(Giocatore g, int newCasella) {

		boolean rimosso = false;
		boolean inserito = false;
		int id = g.getNumeroCasella();
		getCasella(id).rimuovi(g);
		while(id != newCasella ){
			id++;
			getCasella(id).hop(g);
			if(id==40)
				id =0;
		}
		getCasella(newCasella).stop(g);
		/*for (Casella casella : this.caselle) {

			if (casella.staziona(g)) {
				casella.rimuovi(g);

			}
		}

		for (Casella casella : this.caselle) {

			if (casella.getId() == newCasella) {
				casella.inserisci(g);
			}

		}
		*/
		
		

	}
public void spostaDiretto(Giocatore g, int newCasella) {	
	
	
	for (Casella casella : this.caselle) {

		if (casella.staziona(g)) {
			casella.rimuovi(g);

		}
	}

	for (Casella casella : this.caselle) {

		if (casella.getId() == newCasella) {
			//casella.inserisci(g);
			casella.stop(g);
		}

	}
	
	
	

}
	public void posiziona(Giocatore g, int pos) {

		Iterator<Casella> iterator = this.caselle.iterator();

		boolean inserito = false;

		for (Casella casella : this.caselle) {

			if (casella.staziona(g)) {
				casella.rimuovi(g);

			}
		}

		for (Casella casella : this.caselle) {

			casella = iterator.next();

			if (casella.getId() == pos) {
				casella.inserisci(g);

			}

		}
		g.setNumeroCasella(pos);

	}

	/**
	 * Getter di casella.
	 *
	 * @param g
	 *            il giocatore
	 * @return la casella
	 */
	public Casella getCasella(Giocatore g) {

		for (Casella casella : caselle) {

			if (casella.staziona(g)) {
				return casella;

			}

		}
		return caselle.getFirst();
	}
	public Casella getCasella(int id) {

		for (Casella casella : caselle) {

			if (casella.getId() == id) {
				return casella;

			}

		}
		return caselle.getFirst();
	}
	
	public Vector<Proprieta> getProprieta(){
		Vector<Proprieta> proprieta = new Vector<Proprieta>();
		for(Casella c: this.caselle){
			proprieta.add(c.getProprieta());
		}
		return proprieta;
	}

}
