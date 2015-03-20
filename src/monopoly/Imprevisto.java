package monopoly;

public class Imprevisto {
	private int id;
	private String descrizione;
	
	public int getId() {
		return id;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public Imprevisto(int id, String descrizione){
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

}
