package monopoly;

import java.util.Vector;



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
	
	private Vector<Proprieta> possedimenti;
	
	private int ultimoLancio;
	public Vector<Proprieta> getPossedimenti() {
		return possedimenti;
	}

	public void setPossedimenti(Vector<Proprieta> possedimenti) {
		this.possedimenti = possedimenti;
	}

	public int getUltimoLancio() {
		return ultimoLancio;
	}

	public void setUltimoLancio(int ultimoLancio) {
		this.ultimoLancio = ultimoLancio;
	}

	/**
	 * Il costruttore del giocatore.
	 *
	 * @param nome Il nome del nuovo giocatore
	 */
	public Giocatore(String nome){
		
			this.nome=nome;
			this.numeroCasella=0;
			possedimenti = new Vector<Proprieta>();
	}
	
	/**
	 * Metodo per lanciare i dadi.
	 *
	 * @return Integer[] un vettore di interi, contenente i numeri dei dadi lanciati
	 */
	public Integer[] lanciaDadi(){
		
		Integer[] dadiEstratti=new Integer[2];

		dadiEstratti = Util.lanciaDadi(2, 6);
		
		this.ultimoLancio=dadiEstratti[0]+dadiEstratti[1];
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
	
	public void aggiungiProprieta(Proprieta p){
		this.possedimenti.add(p);
	}
	public Vector<Proprieta> getProprieta(){
		return this.possedimenti;
	}
	
	public boolean possiede(Proprieta c){
		for(Proprieta p:possedimenti){
			if(p.equals(c))
				return true;
		}
		return false;
	}
	public boolean possiede(String s){
		for(Proprieta p:possedimenti){
			if(p.getCasella().getNome().equals(s))
				return true;
		}
		return false;
	}
	
	
	public boolean possiede (Tabellone t, Colori c){
		
		Vector<Terreno> terreniColore = new Vector<Terreno>();
		for(Proprieta p: t.getProprieta()){
			if(p instanceof Terreno){
			if(((Terreno)p).getColore() == c){
				terreniColore.add((Terreno)p);
			}
			}
		}
		
		for(Terreno terreno: terreniColore){
			if(! possiede(terreno))
				return false;
		}
		return true;
	}
	public boolean possiedeDueSocieta(){
		return possiede("Societa Elettrica") && possiede("Societa Acqua Potabile");
		 
	}

	
	
	

}
