import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Vector;


public class Tabellone {
	
	public static final int VIA = 10;
	public static final int PRIGIONE = 10;

	private LinkedList<Casella> caselle;
	private DBManager db;
	/**
	 * Il tabellone deve essere istanziato passandogli il database generale dell'applicazione
	 * @param db
	 * @throws SQLException
	 */
	public Tabellone(DBManager db) throws SQLException{
		this.db = db;
		this.caselle = db.getCaselle();
	}
	
	protected void avanza(Giocatore g, int avanzamento){
		
		int posCorrente = g.getNumeroCasella();
		int newPos = posCorrente+avanzamento;
		ListIterator<Casella> iterator = (ListIterator<Casella>) caselle.iterator();
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
	
	protected void sposta(Giocatore g, int newCasella){
		
		g.setNumeroCasella(newCasella);
		ListIterator<Casella> iterator = (ListIterator<Casella>) caselle.iterator();
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
	public Casella getCasella(Giocatore g){
		ListIterator<Casella> iterator = (ListIterator<Casella>) caselle.iterator();
		while(iterator.hasNext()){
			Casella casella = iterator.next();
			if(casella.staziona(g)){
				return casella;
				
				
			}
			
			
		}
		return iterator.next();
	}

}
