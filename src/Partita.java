import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ListIterator;


public class Partita {
	private DBManager db;
	private LinkedList<Giocatore> giocatori;
	private Tabellone tabellone;
	private ListIterator<Giocatore> iterator;
	
	public Partita( DBManager db, LinkedList<Giocatore> giocatori) throws SQLException{
		this.db = db;
		this.giocatori = giocatori;
		tabellone = new Tabellone(db);
		iterator = (ListIterator<Giocatore>) giocatori.iterator();
		
		ListIterator<Giocatore> iterator2 = (ListIterator<Giocatore>) giocatori.iterator();
		while(iterator2.hasNext()){
			tabellone.sposta(iterator2.next(), Tabellone.VIA);
		}
	}
	
	public void turno(){

		int avanzamento = 0;
		int ripetizione = 0;
		boolean ritira=false;
		Giocatore gCorrente = iterator.next();
		System.out.println("Tocca al giocatore"+gCorrente.getNome());
		System.out.println("Parte dalla casella "+
				tabellone.getCasella(gCorrente).getNome());
		

		do{
			Integer[] dadi = gCorrente.lanciaDadi();
		
			for(int i=0; i<dadi.length; i++){
				avanzamento += dadi[i];
			}
			System.out.println(gCorrente.getNome()+" lancia "+avanzamento);
			tabellone.avanza(gCorrente, avanzamento);
			System.out.println("Arriva alla casella "+
					tabellone.getCasella(gCorrente).getNome());
			if(dadi[0]==dadi[1]){
				ritira = true;
				ripetizione++;
			}
			if(ripetizione == 3){
				tabellone.sposta(gCorrente, Tabellone.PRIGIONE);
				System.out.println("In prigione!");
			}
		}while(ritira);
	}
	
	
	
}
