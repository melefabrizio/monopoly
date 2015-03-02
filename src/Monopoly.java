import java.sql.SQLException;
import java.util.*;

public class Monopoly {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		DBManager db = new DBManager();
		Vector<Casella> caselle = db.getCaselle();
		for(Casella casella:caselle){
			System.out.println(casella.getNome());
		}
		
	}

}
