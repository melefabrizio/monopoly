package monopoly;

public class FallimentoException extends Exception {
	
	private Giocatore g;
	
	public FallimentoException(Giocatore g){
		this.g = g; 
		
	}
	public Giocatore getGiocatore(){
		return g;
		
	}
	
	public String toString(){
		String r = new String();
		r = "Il giocatore "+g.getNome()+" � fallito \n";
		return r;
	}

}
