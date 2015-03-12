package monopoly;

import java.sql.*;
import java.util.LinkedList;

/**
 * DB Manager
 * Wrapper per SQLite
 * 
 * 
 * @author Fabrizio Mele
 *
 */
public class DBManager {
	
	Statement statement;
	/**
	 * Costruttore di DBManager, inizializza la connessione al database
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public DBManager() throws ClassNotFoundException, SQLException{
		Class.forName("org.sqlite.JDBC");
		 Connection conn = DriverManager.getConnection("jdbc:sqlite:src/mono.db"); 
		 this.statement = conn.createStatement();
	}
	/**
	 * Ritorna un vector di caselle facendo il parsing della tabella "caselle" nel database
	 * @return Vector<Casella>
	 * @throws SQLException
	 */
	public LinkedList<Casella> getCaselle() throws SQLException{
		
		LinkedList<Casella> caselle = new LinkedList<Casella>();
		ResultSet rs = statement.executeQuery("select * from caselle");
		while(rs.next()){
			
			Casella nuova = new Casella(rs.getInt("id"),rs.getString("nome"), rs.getInt("prezzo"));
			Proprieta prop;
			if(rs.getBoolean("terreno"))
				prop = new Terreno(nuova, nuova.getPrezzo(), Colori.valueOf(rs.getString("colore")));
			else if(rs.getBoolean("stazione"))
				prop = new Stazione(nuova, nuova.getPrezzo(), Cardinali.valueOf(rs.getString("cardinale")));
			else if(rs.getBoolean("societa"))
				prop = new SocietaServizi(nuova, nuova.getPrezzo());
			else
				prop = new Proprieta(nuova, nuova.getPrezzo());
			
			nuova.setProprieta(prop);
			caselle.add(nuova);
			
		}
		rs.close();
		return caselle;
	}
}
