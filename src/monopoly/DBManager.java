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
			System.out.println(rs.getString("terreno")+ " "+rs.getString("nome")+" "+rs.getString("colore"));
			if(rs.getString("terreno").equals("true"))		
					prop = new Terreno(nuova, nuova.getPrezzo(), Colori.valueOf(Colori.class, rs.getString("colore").trim()));		
			else if(rs.getString("stazione").equals("true"))
				prop = new Stazione(nuova, nuova.getPrezzo(), Cardinali.valueOf(Cardinali.class, rs.getString("cardinale")));
			else if(rs.getString("societa").equals("true"))
				prop = new SocietaServizi(nuova, nuova.getPrezzo());
			else
				prop = null;
			
			nuova.setProprieta(prop);
			caselle.add(nuova);
			
		}
		rs.close();
		return caselle;
	}
}
