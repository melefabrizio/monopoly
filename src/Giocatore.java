

public class Giocatore {
	
	private String nome;
	private int numeroCasella;
	
	
	public Giocatore(String nome){
		
			this.nome=nome;
			this.numeroCasella=0;
	}
	
	public Integer[] lanciaDadi(){
		
		Integer[] dadiEstratti=new Integer[2];

		dadiEstratti = Util.lanciaDadi(2, 6);
		
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
