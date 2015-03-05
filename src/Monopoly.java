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

import com.google.common.collect.Iterators;


public class Monopoly {
	
	private final static char SIMBOLO_MESSAGGIO_BENV_USCITA='~';
	private final static String MSG_BENVENUTO = "BENVENUTO NEL GIOCO DEL MONOPOLY";
	private final static String MSG_CHIUSURA = "GRAZIE PER AVERE USATO IL PROGRAMMA - ARRIVEDERCI";
	private final static String MSG_INSERISCI_GIOCATORE = "INSERISCI UN NUOVO GIOCATORE";
	
	//Menu 
	
	final static String [] VOCIMENU={"Inserisci Nuovo Giocatore", "Stampa elenco giocatori", "Gioca", "Esci"};
	final static String TITOLO_MENU = "Menù Monopoly";
	
	// vettore di giocatori
	
	private static Vector<Giocatore> players = new Vector<Giocatore>();

	
	private static void benvenuto()
	{
		UtilityIO.header(MSG_BENVENUTO,SIMBOLO_MESSAGGIO_BENV_USCITA);
		System.out.println();
	}

	private static void saluti()
	{
		UtilityIO.header(MSG_CHIUSURA,SIMBOLO_MESSAGGIO_BENV_USCITA);
		System.out.println();
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		DBManager db = new DBManager();
		
		int scelta;	
		MyMenu menuMain = new MyMenu (TITOLO_MENU, VOCIMENU);
		boolean continuaCiclo=true;
		
			benvenuto();
		
			do
			{
				System.out.println();
				scelta=menuMain.scegli();
				
				switch(scelta)
				{
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
						continuaCiclo=false;
						break;
				}
		
			}while(continuaCiclo);

			saluti();
			
		}
		
		private static void inserisciGiocatore(){
		
			players.add(new Giocatore(InputDati.leggiStringa(MSG_INSERISCI_GIOCATORE)));
		}
		
		private static void stampaGiocatori(Vector<Giocatore> giocatori){
			
			int i=0;
			
			for(Giocatore g: giocatori)
			{
				i++;
				System.out.print(i+" - " + g.toString()+ "\n");
			}
		}
		
		private static void gioca(DBManager database) throws ClassNotFoundException, SQLException{
			
			Collections.shuffle(players);
			Partita parta = new Partita(database, players);
			
			int turno =0;
			while(turno<20){
				System.out.printf("Turno %d \n",turno+1);
				parta.turno();
				turno +=1;
				
		}}
		
	}


