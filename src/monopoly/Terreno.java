package monopoly;

public class Terreno extends Proprieta {
	
	private Colori colore;
	public Terreno(Casella c, int valore, Colori colore) {
		super(c, valore);
		this.colore = colore;
		
	}
	public Colori getColore() {
		return colore;
	}
	public void setColore(Colori colore) {
		this.colore = colore;
	}

}
