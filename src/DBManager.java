import java.sql.*;
import java.util.Vector;


public class DBManager {
	
	Statement statement;
	
	public DBManager() throws ClassNotFoundException, SQLException{
		Class.forName("org.sqlite.JDBC");
		 Connection conn = DriverManager.getConnection("jdbc:sqlite:src/mono.db"); 
		 this.statement = conn.createStatement();
	}
	
	public Vector<Casella> getCaselle() throws SQLException{
		
		Vector<Casella> caselle = new Vector<Casella>();
		ResultSet rs = statement.executeQuery("select * from caselle");
		while(rs.next()){
			caselle.add(new Casella(rs.getInt("id"),rs.getString("nome"), rs.getInt("prezzo")));
			
		}
		return caselle;
	}
}
