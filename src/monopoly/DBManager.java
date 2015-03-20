package monopoly;

import java.sql.*;
import java.util.LinkedList;
import java.util.Vector;

/**
 * DB Manager
 * Wrapper per il database SQLite.
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
			
			Casella nuova;
			if(!(rs.getString("nome").equals("Probabilitˆ") || rs.getString("nome").equals("Imprevisti")))
					nuova = new Casella(rs.getInt("id"),rs.getString("nome"), rs.getInt("prezzo"));
			else{
				if(rs.getString("nome").equals("Probabilitˆ"))
					nuova = new CasellaProbabilita(rs.getInt("id"),rs.getString("nome"), rs.getInt("prezzo"));
				else
					nuova = new CasellaImprevisto(rs.getInt("id"),rs.getString("nome"), rs.getInt("prezzo"));
			}
			Proprieta prop;
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
	/**
	 * Metodo che ritorna un oggetto Carte, contenente le probabilitˆ e gli imprevisti estratti dal database.
	 * @return Un oggetto Carte inizializzato.
	 * @throws SQLException
	 */
	public Carte getCarte() throws SQLException{
		
		
		Carte carte = new Carte(getProbabilita(), getImprevisti());
		return carte;
	}
	/**
	 * Estrae gli oggetti Probabilitˆ dal database
	 * @return Un Vector di oggetti Probabilita
	 * @throws SQLException
	 */
	private Vector<Probabilita> getProbabilita() throws SQLException{
		Vector<Probabilita> p = new Vector<Probabilita>();
		ResultSet rs = statement.executeQuery("select * from probabilita");
		while(rs.next()){
			Probabilita nuova = new Probabilita(rs.getInt("id"), rs.getString("descrizione"));
			p.add(nuova);
		}
		
		return p;
	}
	/**
	 * Estrae gli oggetti Imprevisto dal database
	 * @return Un Vector di oggetti Imprevisto
	 * @throws SQLException
	 */
	private Vector<Imprevisto> getImprevisti() throws SQLException{
		Vector<Imprevisto> p = new Vector<Imprevisto>();
		ResultSet rs = statement.executeQuery("select * from imprevisti");
		while(rs.next()){
			Imprevisto nuova = new Imprevisto(rs.getInt("id"), rs.getString("descrizione"));
			p.add(nuova);
		}
		
		return p;
	}
}
