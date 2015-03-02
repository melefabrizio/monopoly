import java.sql.SQLException;
import java.util.Vector;


public class Tabellone {
	
	private Vector<Casella> caselle;
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

}
