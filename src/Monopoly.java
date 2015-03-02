import java.sql.SQLException;
import java.util.*;

import com.google.common.collect.Iterators;

public class Monopoly {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		DBManager db = new DBManager();
		System.out.println("Inizia");
		Vector<Giocatore> players = new Vector<Giocatore>();
		players.add(new Giocatore("Fab"));
		players.add(new Giocatore("Dab"));
		players.add(new Giocatore("Cioppo"));
		System.out.println(players);

		Partita parta = new Partita(db, players);
		
		int turno =0;
		while(turno<20){
			System.out.println(turno);
			parta.turno();
			turno +=1;
			
		}

		
	}

}
