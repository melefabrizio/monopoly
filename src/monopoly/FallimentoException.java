package monopoly;

public class FallimentoException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -820983946112762844L;
	private Giocatore g;
	private Giocatore rimborsando;
	private int rimborso;
	
	public FallimentoException(Giocatore g, Giocatore r, int rimborso){
		this.g = g; 
		this.rimborsando=r;
		this.rimborso= rimborso;
		
	}
	public Giocatore getRimborsando() {
		return rimborsando;
	}
	public void setRimborsando(Giocatore rimborsando) {
		this.rimborsando = rimborsando;
	}
	public int getRimborso() {
		return rimborso;
	}
	public void setRimborso(int rimborso) {
		this.rimborso = rimborso;
	}
	public Giocatore getGiocatore(){
		return g;
		
	}
	
	public String toString(){
		String r = new String();
		r = "Il giocatore "+g.getNome()+" � fallito \n";
		return r;
	}

}
