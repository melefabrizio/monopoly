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
	 * Il tabellone deve essere istanziato passandogli il database generale
	 * dell'applicazione.
	 *
	 * @param db
	 *            il database generale dell'applicazione
	 * @throws SQLException
	 *             l'eccezione SQL lanciata
	 */
	public Tabellone(DBManager db) throws SQLException {
		this.db = db;
		this.caselle = db.getCaselle();
	}

	/**
	 * Il metodo che fa avanzare un giocatore, prende la posizione corrente del
	 * giocatore, lo rimuove da quella e lo inserisce nella nuova posizione.
	 *
	 * @param g
	 *            il giocatore
	 * @param avanzamento
	 *            quantitˆ di avanzamento del giocatore
	 */
	protected void avanza(Giocatore g, int avanzamento) {

		int posCorrente = g.getNumeroCasella();
		int newPos = posCorrente + avanzamento;
		newPos = newPos > 40 ? newPos - 40 : newPos;
		sposta(g, newPos);

	}

	/**
	 * Metodo che sposta il giocatore.
	 *
	 * @param g
	 *            the g
	 * @param newCasella
	 *            the new casella
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
	 * Get di casella.
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
