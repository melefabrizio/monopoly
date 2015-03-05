/**
 * <p>Title: Monopoly</p>
 *
 * <p>Description: Applicazione software che consente di giocare a Monopoly</p>
 *
 * <p>Copyright: Copyright (c) 2015 D. Falleti, F. Mele, F. Cordioli</p>
 *
 * <p>Company: UNIBS</p>
 *
 * @author F. Mele, D. Falleti, F. Cordioli
 * @version 1.0
 */

import java.sql.SQLException;

import java.util.*;


// TODO: Auto-generated Javadoc
/**
 * La classe Monopoly
 */
public class Monopoly {

	/** La costante MSG_BENVENUTO. */
	private final static String MSG_BENVENUTO = "BENVENUTO NEL GIOCO DEL MONOPOLY";

	/** La costante MSG_CHIUSURA. */
	private final static String MSG_CHIUSURA = "GRAZIE PER AVERE USATO IL PROGRAMMA - ARRIVEDERCI";

	/** La costante MSG_INSERISCI_GIOCATORE. */
	private final static String MSG_INSERISCI_GIOCATORE = "Inserisci un nuovo giocatore > ";

	/** La costante Numero Turni */
	private static final int NUMERO_TURNI = 20;

	// Menu

	/** Il Vector di giocatori. */
	private static Vector<Giocatore> players = new Vector<Giocatore>();

	/** La costante SIMBOLO_MESSAGGIO_BENV_USCITA. */
	private final static char SIMBOLO_MESSAGGIO_BENV_USCITA = '~';
	/** La costante TITOLO_MENU. */
	final static String TITOLO_MENU = "Menù Monopoly";

	/** La costante VOCIMENU. */
	final static String[] VOCIMENU = { "Inserisci Nuovo Giocatore",
			"Stampa elenco giocatori", "Gioca" };

	/**
	 * Il metodo benvenuto che saluta l'utente.
	 */
	private static void benvenuto() {
		UtilityIO.header(MSG_BENVENUTO, SIMBOLO_MESSAGGIO_BENV_USCITA);
		System.out.println();
	}

	/**
	 * Il metodo che permette di effettuare una partita.
	 *
	 * @param database
	 *            il database
	 * @throws ClassNotFoundException
	 *             la classe not found exception
	 * @throws SQLException
	 *             l'eccezione SQL
	 */
	private static void gioca(DBManager database)
			throws ClassNotFoundException, SQLException {

		if (players.size() < 2) {
			System.out
					.printf("Attualmente ci sono %d giocatori.\nCi devono essere almeno 2 giocatori per giocare!\n",
							players.size());
		}

		else {
			Collections.shuffle(players);
			Partita parta = new Partita(database, players);

			int turno = 0;
			while (turno < NUMERO_TURNI * players.size()) {
				if (turno %players.size() == 0)
					System.out.printf("Turno %d \n", ((turno + 1)/players.size())+1);
				parta.turno();
				turno += 1;
			}

		}
	}

	/**
	 * Inserisci giocatore.
	 */
	private static void inserisciGiocatore() {

		if (players.size() > 5) {
			System.out.println("Raggiunto il numero massimo di giocatori\n");
		} else
			players.add(new Giocatore(InputDati
					.leggiStringa(MSG_INSERISCI_GIOCATORE)));
	}

	/**
	 * Il metodo Main.
	 *
	 * @param args
	 *            gli argomenti
	 * @throws ClassNotFoundException
	 *             la classe not found exception
	 * @throws SQLException
	 *             l'eccezione SQL
	 */
	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {

		DBManager db = new DBManager();

		int scelta;
		MyMenu menuMain = new MyMenu(TITOLO_MENU, VOCIMENU);
		boolean continuaCiclo = true;

		benvenuto();

		do {
			System.out.println();
			scelta = menuMain.scegli();

			switch (scelta) {
			case 1:
				inserisciGiocatore();
				break;

			case 2:
				stampaGiocatori(players);
				break;
			case 3:
				gioca(db);
				break;

			case 0:
				continuaCiclo = false;
				break;
			}

		} while (continuaCiclo);

		saluti();

	}

	/**
	 * Il metodo saluti che saluta l'utente che sta per chiudere il programma
	 */
	private static void saluti() {
		UtilityIO.header(MSG_CHIUSURA, SIMBOLO_MESSAGGIO_BENV_USCITA);
		System.out.println();
	}

	/**
	 * Stampa giocatori.
	 *
	 * @param giocatori
	 *            i giocatori
	 */
	private static void stampaGiocatori(Vector<Giocatore> giocatori) {

		int i = 0;

		for (Giocatore g : giocatori) {
			i++;
			System.out.print(i + " - " + g.toString() + "\n");
		}
	}

}
