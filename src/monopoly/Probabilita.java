package monopoly;

public class Probabilita {
	
	private int id;
	private String descrizione;
	
	public Probabilita(int id, String descrizione){
		this.id=id;
		this.descrizione=descrizione;
	}
	
	
	public String toString(){
		StringBuffer b = new StringBuffer();
		b.append("******\n");
		b.append(descrizione);
		b.append("\n******");
		return b.toString();
	}


	public int getId() {
		return id;
	}


	public String getDescrizione() {
		return descrizione;
	}

}
