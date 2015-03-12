package monopoly;



/**
 * La classe che rappresenta un Giocatore.
 */
public class Giocatore {
	
	/** Il nome del giocatore. */
	private String nome;
	
	/** Il numero della casella in cui � il giocatore. */
	private int numeroCasella;
	
	/** Il capitale nelle tasche del giocatore. */
	
	private int capitale;
	
	/**
	 * Il costruttore del giocatore.
	 *
	 * @param nome Il nome del nuovo giocatore
	 */
	public Giocatore(String nome){
		
			this.nome=nome;
			this.numeroCasella=0;
	}
	
	/**
	 * Metodo per lanciare i dadi.
	 *
	 * @return Integer[] un vettore di interi, contenente i numeri dei dadi lanciati
	 */
	public Integer[] lanciaDadi(){
		
		Integer[] dadiEstratti=new Integer[2];

		dadiEstratti = Util.lanciaDadi(2, 6);
		
		return dadiEstratti;
	}

	/**
	 * Geter del nome.
	 *
	 * @return il nome
	 */
	public String getNome() {
		return nome;
	}


	/**
	 * Setter il nome.
	 *
	 * @param nome il nuovo nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}


	/**
	 * Geter dell'id della casella.
	 *
	 * @return  l'id della casella
	 */
	public int getNumeroCasella() {
		return numeroCasella;
	}


	/**
	 * Setter del numero della casella.
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
		
		StringBuffer output=new StringBuffer();
			
			output.append("Il giocatore "+this.nome+" possiede "+ this.getCapitale()+"€");
			
			return output.toString();
		
		
	}
	
	

}
