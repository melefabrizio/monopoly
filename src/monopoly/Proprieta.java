package monopoly;

public class Proprieta {
	
	private Casella casella;
	
	private int valore;
	private int affitto;
	
	public Casella getCasella() {
		return casella;
	}

	public void setCasella(Casella casella) {
		this.casella = casella;
	}

	public int getValore() {
		return valore;
	}

	public void setValore(int valore) {
		this.valore = valore;
	}

	public int calcolaAffitto(Giocatore g, int dadi) {
		return affitto;
	}

	public void setAffitto(int affitto) {
		this.affitto = affitto;
	}

	public Proprieta(Casella c, int valore){
		casella = c;
		this.valore = valore;
		affitto =  valore*(int)Partita.PERCENTUALE_AFFITTO;
	}

}
