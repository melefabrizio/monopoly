package monopoly;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

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
	protected void avanza(Giocatore g, int avanzamento) {

		int posCorrente = g.getNumeroCasella();
		int newPos = posCorrente + avanzamento;
		if(newPos >40){
			newPos=newPos-40;
			g.setCapitale(g.getCapitale()+500);
		}
		sposta(g, newPos);

	}
	/**
	 * Sposta il giocatore ad una nuova casella.
	 *
	 * @param g
	 *            Il giocatore da spostare
	 * @param newCasella
	 *            L'id della casella di destinazione 
	 */
	protected void sposta(Giocatore g, int newCasella) {

		boolean rimosso = false;
		boolean inserito = false;

		for (Casella casella : this.caselle) {

			if (casella.staziona(g)) {
				casella.rimuovi(g);

			}
		}

		for (Casella casella : this.caselle) {

			if (casella.getId() == newCasella) {
				casella.inserisci(g);
			}

		}
		g.setNumeroCasella(newCasella);

	}

	protected void posiziona(Giocatore g, int pos) {

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

}
