package monopoly;

/**
 * <p>Title: Monopoly</p>
 *
 * <p>Description: Applicazione software che consente di giocare a Monopoly</p>
 *
 * <p>Copyright: Copyright (c) 2015 D. Falleti, F. Mele, F. Cordioli</p>
 *
 * <p>Company: UNIBS</p>
 *
 * @author F. Mele D. Falleti, F. Cordioli
 * @version 1.0
 */

import java.sql.SQLException;

import java.util.*;

/**
 * La classe Monopoly
 */
public class Monopoly {

	protected static final int MAX_PLAYERS = 5;

	protected static final int MIN_PLAYERS = 2;

	/** La costante MSG_BENVENUTO. */
	private final static String MSG_BENVENUTO = "BENVENUTO NEL GIOCO DEL MONOPOLY";

	/** La costante MSG_CHIUSURA. */
	private final static String MSG_CHIUSURA = "GRAZIE PER AVERE USATO IL PROGRAMMA - ARRIVEDERCI";

	/** La costante MSG_INSERISCI_GIOCATORE. */
	private final static String MSG_INSERISCI_GIOCATORE = "Inserisci un nuovo giocatore > ";

	/** La costante Numero Turni */
	private static final int NUMERO_TURNI = 20;
	
	private static final int CAPITALE_INIZIALE = 5000; 

	// Menu

	/** Il Vector di giocatori. */
	private static Vector<Giocatore> players = new Vector<Giocatore>();

	/** La costante SIMBOLO_MESSAGGIO_BENV_USCITA. */
	private final static char SIMBOLO_MESSAGGIO_BENV_USCITA = '~';
	/** La costante TITOLO_MENU. */
	final static String TITOLO_MENU = "Menu' Monopoly";

	/** La costante VOCIMENU. */
	final static String[] VOCIMENU = { "Inserisci Nuovo Giocatore",
			"Stampa Elenco Giocatori", "Gioca", "Rimuovi Giocatore" , "Reset"};

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

		if (players.size() < MIN_PLAYERS) {
			System.out
					.printf("Attualmente ci sono %d giocatori.\nCi devono essere almeno 2 giocatori per giocare!\n",
							players.size());
		}
		else {
			Collections.shuffle(players);
			Partita parta = new Partita(database, players);
			
			
			
			for(Giocatore p : players){
				Banca.prelievo(p, CAPITALE_INIZIALE);
				
			}

			int turno = 0;
			while (turno < NUMERO_TURNI * players.size() && players.size()>1) {
				if (turno %players.size() == 0)
					
					System.out.printf("Turno %d\n", ((turno + 1)/players.size())+1);

				try{
					parta.turno();
				}catch (FallimentoException e){
					
					players.remove(e.getGiocatore());
					parta.rimuoviGiocatore(e.getGiocatore());
				}
				turno += 1;
				try{
					System.in.read();
				}catch(Exception e){
					
				}
			}

		}
		proclamaVincitore();
	}

	/**
	 * Inserisci giocatore.
	 */
	private static void inserisciGiocatore() {

		if (players.size() > MAX_PLAYERS) {
			System.out.println("Raggiunto il numero massimo di giocatori\n");
		
		}else
			players.add(new Giocatore(InputDati
					.leggiStringaNonVuota(MSG_INSERISCI_GIOCATORE)));
	}

	/**
	 * Il metodo Main.
	 *
	 * @param args
	 *            gli argomenti
	 *
	 */
	public static void main(String[] args)  {
		DBManager db;
		db = null;
		try{
			 db = new DBManager();
		}catch (Exception e){
			System.out.println("Si e' verificato un errore nella connessione al Database.");
			System.out.println(e.getMessage());
			System.exit(1);
		}
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
				try{
					gioca(db);
				}catch (SQLException e){
					System.out.println("Si e' verificato un errore nel Database.");
					System.out.println(e.getMessage());

				}catch(ClassNotFoundException e){
					System.out.println("Si e' verificato un errore nella libreria SQL.");
					System.out.println(e.getMessage());

				}
				break;
				
			case 4: rimuoviGiocatore(players);
				break;
				
			case 5: 
				reset();
				break;

			case 0:
				continuaCiclo = false;
				break;
			}

		} while (continuaCiclo);
		
		saluti();

	}

	private static void reset() {
		
		players = new Vector<Giocatore>();
		
		
		
	}

	/**
	 * Il metodo saluti che saluta l'utente che sta per chiudere il programma
	 */
	private static void saluti() {
		UtilityIO.header(MSG_CHIUSURA, SIMBOLO_MESSAGGIO_BENV_USCITA);
		System.out.println();
	}
	
	private static void rimuoviGiocatore(Vector<Giocatore> giocatori){
		
		String[] voci_giocatori = new String[players.size()];
		int i =0;
		for(Giocatore g:giocatori){
			voci_giocatori[i] = g.toString();
			i++;
		}
		MyMenu menuGiocatori = new MyMenu("Rimozione Giocatori", voci_giocatori);
		int scelta = menuGiocatori.scegli();
		if(scelta>0){
			players.remove(scelta-1);
		}
	}
	
	private static void proclamaVincitore(){
		int max =0;
		Vector<Giocatore> vincitori = new Vector<Giocatore>();
		for(Giocatore g:players){
			if(g.getCapitale()>max){
				max = g.getCapitale();
			}
		}
		for(Giocatore g:players){
			if(g.getCapitale() == max){
				vincitori.add(g);
			}
		}
		
		if(vincitori.size()==1){
			System.out.println("Il vincitore e' "+vincitori.get(0).getNome()+
					", con un capitale finale di "+vincitori.get(0).getCapitale()+" euro");
		}else{
			StringBuffer b = new StringBuffer();
			b.append("I vincitori sono ");
			for(Giocatore g:players){
				b.append(g.getNome()+", ");
				
			}
			b.append(", con un capitale finale di "+max+" euro");
			System.out.println(b.toString());
			
		}
	}

	/**
	 * Stampa giocatori.
	 *
	 * @param giocatori
	 *            i giocatori
	 */
	private static void stampaGiocatori(Vector<Giocatore> giocatori) {
		if(giocatori.size()==0){
			System.out.println("\nNon ci sono giocatori\n");
		}
		int i = 0;

		for (Giocatore g : giocatori) {
			i++;
			System.out.print(i + " - " + g.toString() + "\n");
		}
	}

}
