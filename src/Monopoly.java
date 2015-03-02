import java.sql.SQLException;
import java.util.*;

public class Monopoly {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		DBManager db = new DBManager();
		
		Integer[] ints = Util.lanciaDadi(3,6);
		System.out.println(ints[0]);
		System.out.println(ints[1]);

		
	}

}
