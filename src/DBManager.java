
import java.sql.*;
import java.util.LinkedList;
import java.util.Vector;

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
	 * @return Vector di Caselle
	 * @throws SQLException
	 */
	public LinkedList<Casella> getCaselle() throws SQLException{
		
		LinkedList<Casella> caselle = new LinkedList<Casella>();
		ResultSet rs = statement.executeQuery("select * from caselle");
		while(rs.next()){
			caselle.add(new Casella(rs.getInt("id"),rs.getString("nome"), rs.getInt("prezzo")));
			
		}
		rs.close();
		return caselle;
	}
}
