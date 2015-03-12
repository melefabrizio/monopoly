package monopoly;

public class Proprieta {
	
	private Casella casella;
	private Giocatore proprietario;
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

	public int calcolaAffitto(Giocatore g){
		return this.getValore()*Partita.PERCENTUALE_AFFITTO.intValue();
	}
	
	public void setAffitto(int affitto) {
		this.affitto = affitto;
	}

	public Proprieta(Casella c, int valore){
		casella = c;
		this.valore = valore;
		//affitto =  valore*Partita.PERCENTUALE_AFFITTO;
	}
	
	public void setProprietario(Giocatore g){
		this.proprietario=g;
	}
	public Giocatore getProprietario(){
		return proprietario;
	}

	public Tabellone getTabellone(){
		return casella.getTabellone();
	}
}
