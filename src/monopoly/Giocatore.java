package monopoly;

import java.util.Vector;



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
	 * Le proprietà in possesso del giocatore.
	 */
	private Vector<Proprieta> possedimenti;
	
	/**
	 * Il risultato dell'ultimo lancio di dadi effettuato.
	 */
	private int ultimoLancio;
	/**
	 * Variabile booleana, vero se il giocatore è correntemente in prigione, falso altrimenti.
	 */
	private boolean inPrigione;
	
	/**
	 * Metodo che ritorna un Vector contenente tutti i possedimenti del giocatore.
	 * @return Un vector di Proprieta.
	 */
	public Vector<Proprieta> getPossedimenti() {
		return possedimenti;
	}
	/**
	 * Metodo per impostare i possedimenti correnti del giocatore.
	 * @param possedimenti I possedimenti del giocatore
	 */ 
	public void setPossedimenti(Vector<Proprieta> possedimenti) {
		this.possedimenti = possedimenti;
	}

	/**
	 * 
	 * @return Il risultato dell'ultimo lancio effettuato dal giocatore.
	 */
	public int getUltimoLancio() {
		return ultimoLancio;
	}

	/**
	 * Imposta il valore dell'ultimo lancio effettuato dal giocatore.
	 * @param ultimoLancio
	 */
	public void setUltimoLancio(int ultimoLancio) {
		this.ultimoLancio = ultimoLancio;
	}

	/**
	 * Il costruttore del giocatore. Inizializza la casella iniziale al Via.
	 * Il giocatore inizialmente non possiede Proprietà.
	 *
	 * @param nome Il nome del nuovo giocatore
	 */
	public Giocatore(String nome){
		
			this.nome=nome;
			this.numeroCasella=0;
			possedimenti = new Vector<Proprieta>();
			inPrigione = false;
	}
	
	/**
	 * Il metodo lancia due dadi da sei facce e ritorna il risultato al chiamante.
	 *
	 * @return Integer[] un vettore di interi, contenente le facce risultanti dal lancio dei dadi.
	 */
	public Integer[] lanciaDadi(){
		
		Integer[] dadiEstratti=new Integer[2];

		dadiEstratti = Util.lanciaDadi(2, 6);
		
		this.ultimoLancio=dadiEstratti[0]+dadiEstratti[1];
		return dadiEstratti;
	}

	/**
	 *
	 * @return Il nome di questo giocatore.
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
	 * 
	 *
	 * @return  L'identificatore della casella su cui staziona il giocatore.
	 */
	public int getNumeroCasella() {
		return numeroCasella;
	}


	/**
	 * Cambia la casella su cui staziona il giocatore.
	 *
	 * @param numeroCasella l'id della nuova casella
	 */
	public void setNumeroCasella(int numeroCasella) {
		this.numeroCasella = numeroCasella;
	}
	
	/**
	 * 
	 * @return Il capitale in possesso del giocatore.
	 */
	public int getCapitale(){
		return capitale;
	}
	/**
	 * Imposta il capitale del giocatore ad un intero predefinito. 
	 * Utilizzato a inizio partita per impostare il capitale iniziale.
	 * @param nc Il nuovo capitale
	 */
	public void setCapitale(int nc){
		this.capitale = nc;
	}
	
	public String toString(){
		return nome+"["+capitale+"e]";
//		StringBuffer output=new StringBuffer();
//			
//			output.append("Il giocatore "+this.nome+" possiede "+ this.getCapitale()+"‚Ç¨");
//			
//			return output.toString();
//		
		
	}
	/**
	 * Aggiunge una Proprietà all'elenco dei possedimenti
	 * del giocatore.
	 * @param p La proprietà da aggiungere.
	 */
	public void aggiungiProprieta(Proprieta p){
		this.possedimenti.add(p);
	}
	/**
	 * 
	 * @return Il Vector di Proprietà possedute dal giocatore.
	 */
	public Vector<Proprieta> getProprieta(){
		return this.possedimenti;
	}
	/**
	 * Determina se il giocatore possiede una certa proprietà
	 * @param proprieta La proprietà da verificare
	 * @return Vero se la proprietà è in possesso del giocatore, falso altrimenti.
	 */
	public boolean possiede(Proprieta proprieta){
		for(Proprieta p:possedimenti){
			if(p.equals(proprieta))
				return true;
		}
		return false;
	}
	/**
	 * Determina se il giocatore possiede una certa proprietà
	 * @param nomeProprieta La stringa rappresentante il nome della proprietà da verificare
	 * @return Vero se la proprietà è in possesso del giocatore, falso altrimenti.
	 */
	public boolean possiede(String nomeProprieta){
		for(Proprieta p:possedimenti){
			if(p.getCasella().getNome().equals(nomeProprieta))
				return true;
		}
		return false;
	}
	
	/**
	 * Determina se il giocatore possiede tutte le proprietà di un colore.
	 * @param t Il tabellone corrente.
	 * @param c Il colore da verificare.
	 * @return Vero s il giocatore possiede tutte le proprietà del colore, falso altrimenti.
	 */
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
	
	/**
	 * Verifica se il giocatore possiede sia la Società Elettrica, sia la Società Acqua Potabile.
	 * @return Vero se il giocatore possiede entrambe le società di servizi, falso altrimenti.
	 */
	public boolean possiedeDueSocieta(){
		return possiede("Societa Elettrica") && possiede("Societa Acqua Potabile");
		 
	}
	/**
	 * Verifica se il giocatore è in prigione.
	 * @return Vero se il giocatore è in prigione, falso altrimenti.
	 */
	public boolean inPrigione(){
		return inPrigione;
	}
	/**
	 * Imposta lo stato in prigione/non in prigione.
	 * @param inPrigione Falso se si vuole far uscire di prigione il giocatore, vero altrimenti.
	 */
	public void inPrigione(boolean inPrigione){
		this.inPrigione = inPrigione;
	}
	
	
	

}
