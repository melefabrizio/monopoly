

public class Giocatore {
	
	private String nome;
	private int numeroCasella;
	
	
	public Giocatore(String nome,int numeroCasella){
		
			this.nome=nome;
			this.numeroCasella=numeroCasella;
	}
	
	public Integer[] lanciaDadi(int numeroDadi){
		
		Integer[] dadiEstratti=new Integer[2];

		Util.lanciaDadi(2, 6);
		
		return dadiEstratti;
	}

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getNumeroCasella() {
		return numeroCasella;
	}


	public void setNumeroCasella(int numeroCasella) {
		this.numeroCasella = numeroCasella;
	}
	
	

}
