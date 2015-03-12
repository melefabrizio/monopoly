package monopoly;
import java.util.Vector;


/**
 * La classe che rappresenta una Casella.
 */
public class Casella {

	/** Attributo id. */
	private int id;
	
	/** Attributo nome. */
	private String nome;
	
	/** Attributo prezzo. */
	private int prezzo;
	
	private MovementListener listener;
	
	/** Attributo giocatori. */
	private Vector<Giocatore> giocatori;

	private Proprieta proprieta;


	/**
	 * Costruttore di casella.
	 *
	 * @param id l'id della casella
	 * @param nome il nome della casella
	 * @param prezzo il prezzo della proprietà
	 */
	public Casella(int id, String nome, int prezzo) {
		this.id = id;
		this.nome = nome;
		this.prezzo = prezzo;
		this.giocatori = new Vector<Giocatore>();
		this.listener = null;
	}
	
	public void hop(Giocatore g){
		
		listener.onHop(g, this);
		
	}
	public void stop(Giocatore g){
		
		giocatori.add(g);
		g.setNumeroCasella(this.id);
		listener.onStop(g, this);
		
	}
	
	public void setMovementListener(MovementListener l){
		this.listener = l;
	}
	
	/**
	 * Ritorna vero se il giocatore è presente sulla casella.
	 *
	 * @param g il giocatore
	 * @return Un boolean, true se il giocatore è presente sulla casella, false altrimenti.
	 */
	public boolean staziona(Giocatore g){
		for(Giocatore giocatore:giocatori){
			if(giocatore.getNome().equals(g.getNome())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Rimuove il giocatore dalla casella.
	 *
	 * @param g il giocatore
	 */
	public void rimuovi(Giocatore g){
		giocatori.remove(g);
	}
	
	/**
	 * Inserisce il giocatore nella casella.
	 *
	 * @param g il giocatore
	 */
	public void inserisci(Giocatore g){
		giocatori.add(g);
	}
	
	/**
	 * Getter dell'id.
	 *
	 * @return l'id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Getter del nome.
	 *
	 * @return il nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Getter del prezzo.
	 *
	 * @return il prezzo
	 */
	public int getPrezzo() {
		return prezzo;
	}

	/**
	 * Setter dell'id.
	 *
	 * @param id il nuovo id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Setter del nome.
	 *
	 * @param nome il nuovo nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Setter del prezzo.
	 *
	 * @param prezzo il nuovo prezzo
	 */
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	
	/**
	 * Getter dei giocatori presenti.
	 *
	 * @return i giocatori presenti sulla casella
	 */
	public Vector<Giocatore> getGiocatori() {
		return giocatori;
	}

	/**
	 * Setta i giocatori.
	 *
	 * @param giocatori i nuovi giocatori
	 */
	public void setGiocatori(Vector<Giocatore> giocatori) {
		this.giocatori = giocatori;
	}

	public Proprieta getProprieta() {
		return proprieta;
	}

	public void setProprieta(Proprieta proprieta) {
		this.proprieta = proprieta;
	}

}
