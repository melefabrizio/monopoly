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
	
	/** Attributo giocatori. */
	private Vector<Giocatore> giocatori;



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
		
	}
	
	/**
	 * Fa stazionare il gocatore su una casella.
	 *
	 * @param g il giocatore
	 * @return true, se la condizione dell'if è verificata
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
	 * Rimuove il giocatore da una casella.
	 *
	 * @param g il giocatore
	 */
	public void rimuovi(Giocatore g){
		giocatori.remove(g);
	}
	
	/**
	 * Inserisce il giocatore in una casella.
	 *
	 * @param g il giocatore
	 */
	public void inserisci(Giocatore g){
		giocatori.add(g);
	}
	
	/**
	 * Get dell'id.
	 *
	 * @return l'id
	 */
	public int getId() {
		return id;
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
	 * Get del prezzo.
	 *
	 * @return il prezzo
	 */
	public int getPrezzo() {
		return prezzo;
	}

	/**
	 * Setta l'd.
	 *
	 * @param id il nuovo id
	 */
	public void setId(int id) {
		this.id = id;
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
	 * Setta il prezzo.
	 *
	 * @param prezzo il nuovo prezzo
	 */
	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	
	/**
	 * Get di giocatori.
	 *
	 * @return i giocatori
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

}
