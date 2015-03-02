import java.util.Vector;

public class Casella {

	private int id;
	private String nome;
	private int prezzo;
	private Vector<Giocatore> giocatori;



	public Casella(int id, String nome, int prezzo) {
		this.id = id;
		this.nome = nome;
		this.prezzo = prezzo;
		this.giocatori = new Vector<Giocatore>();
		
	}
	
	public boolean staziona(Giocatore g){
		for(Giocatore giocatore:giocatori){
			if(giocatore.equals(g)){
				return true;
			}
		}
		return false;
	}
	
	public void rimuovi(Giocatore g){
		giocatori.remove(g);
	}
	
	public void inserisci(Giocatore g){
		giocatori.add(g);
	}
	
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}
	public Vector<Giocatore> getGiocatori() {
		return giocatori;
	}

	public void setGiocatori(Vector<Giocatore> giocatori) {
		this.giocatori = giocatori;
	}

}
