package monopoly;



/**
 * La classe che rappresenta un Giocatore.
 */
public class Giocatore {
	
	/** Il nome del giocatore. */
	private String nome;
	
	/** Il numero della casella in cui è il giocatore. */
	private int numeroCasella;
	
	/** Il capitale nelle tasche del giocatore. */
	
	private int capitale;
	
	/**
	 * Il costruttore del giocatore.
	 *
	 * @param nome il nome passato al costruttore
	 */
	public Giocatore(String nome){
		
			this.nome=nome;
			this.numeroCasella=0;
	}
	
	/**
	 * Il metodo che lancia i dadi.
	 *
	 * @return Integer[] un vettore di interi, contenente i numeri dei dadi lanciati
	 */
	public Integer[] lanciaDadi(){
		
		Integer[] dadiEstratti=new Integer[2];

		dadiEstratti = Util.lanciaDadi(2, 6);
		
		return dadiEstratti;
	}

	/**
	 * Get del nome.
	 *
	 * @return il nome
	 */
	public String getNome() {
		return nome;
	}


	/**
	 * Setta il nome.
	 *
	 * @param nome il nuovo nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}


	/**
	 * Get del numero della casella.
	 *
	 * @return il numero della casella
	 */
	public int getNumeroCasella() {
		return numeroCasella;
	}


	/**
	 * Setta il numero della casella.
	 *
	 * @param numeroCasella il nuovo numero della casella
	 */
	public void setNumeroCasella(int numeroCasella) {
		this.numeroCasella = numeroCasella;
	}
	
	public int getCapitale(){
		return capitale;
	}
	
	public void setCapitale(int nc){
		this.capitale = nc;
	}
	
	public String toString(){
		return this.nome;
	}
	
	

}
