package monopoly;
import java.util.Vector;


/**
 * La classe che rappresenta una Casella.
 */
public class Casella {

	/** L'identificatore numerico della casella. */
	private int id;
	
	/** Il nome della casella. */
	private String nome;
	
	/** Il valore della casella in euro. */
	private int prezzo;
	/**
	 * L'oggetto MovementListener delegato della gestione di passaggi e fermate sulla casella.
	 */
	private MovementListener listener;
	
	/** I giocatori presenti sulla casella. */
	private Vector<Giocatore> giocatori;
	/**
	 * L'oggetto Proprietà corrispondente alla casella. Null se la casella non è un Terreno, una Stazione o una SocietàServizi.
	 */
	private Proprieta proprieta;
	/**
	 * Il tabellone su cui esiste la casella.
	 */
	private Tabellone tabellone;


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
	
	/**
	 * Metodo che cura il passaggo di un giocatore dalla casella.
	 * 
	 * @param g Il giocatore di passaggio
	 * @throws FallimentoException
	 */
	public void hop(Giocatore g){
		
		listener.onHop(g, this);
		
	}
	
	/**
	 * Metodo che cura la fermata di un giocatore sulla casella.
	 * 
	 * @param g Il giocatore da fermare.
	 * @throws FallimentoException
	 */
	
	public void stop(Giocatore g) throws FallimentoException{
		
		giocatori.add(g);
		g.setNumeroCasella(this.id);
		listener.onStop(g, this);
		
	}
	/**
	 * Imposta l'oggetto MovementListener per questa casella.
	 * @param l L'oggetto MovementListener
	 */
	public void setMovementListener(MovementListener l){
		this.listener = l;
	}
	
	/**
	 * Ritorna vero se il giocatore è presente sulla casella.
	 *
	 * @param g il giocatore
	 * @return Vero se il giocatore è presente sulla casella, falso altrimenti.
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
	 * ì
	 *
	 * @return I giocatori presenti sulla casella
	 */
	public Vector<Giocatore> getGiocatori() {
		return giocatori;
	}

	/**
	 * Imposta i giocatori che stazionano sulla casella.
	 *
	 * @param giocatori I giocatori
	 */
	public void setGiocatori(Vector<Giocatore> giocatori) {
		this.giocatori = giocatori;
	}
	/**
	 * 
	 * @return L'oggetto Proprietà corrispondente alla casella.
	 */
	public Proprieta getProprieta() {
		return proprieta;
	}
	/**
	 * Imposta l'oggetto Proprietà corrispondente alla casella.
	 * @param proprieta
	 */
	public void setProprieta(Proprieta proprieta) {
		this.proprieta = proprieta;
	}
	/**
	 * Imposta il tabellone su cui risiete la casella.
	 * @param t Il tabellone
	 */
	public void setTabellone(Tabellone t){
		this.tabellone = t;
	}
	/**
	 * 
	 * @return Il tabellone su cui risiede la casella.
	 */
	public Tabellone getTabellone(){
		return this.tabellone;
	}

}
