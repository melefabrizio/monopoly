package monopoly;

public class SocietaServizi extends Proprieta {
	
	

	public SocietaServizi(Casella c, int valore) {
		super(c, valore);
		
		
	}
	
	public int calcolaAffitto(Giocatore e){
		
		return e.getUltimoLancio()*this.getValore()*
				(this.getProprietario().possiedeDueSocieta()?Partita.MOLTIPLICATORE_SOCIETA_DUE
						:Partita.MOLTIPLICATORE_SOCIETA_UNA);
		
	}

}
