public class Casella {

	private int id;
	private String nome;
	private int prezzo;

	public Casella(int id, String nome, int prezzo) {
		this.id = id;
		this.nome = nome;
		this.prezzo = prezzo;
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

}
