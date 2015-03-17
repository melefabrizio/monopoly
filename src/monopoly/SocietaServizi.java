package monopoly;

public class SocietaServizi extends Proprieta {
	
	

	public SocietaServizi(Casella c, int valore) {
		super(c, valore);
		
		
	}
	
	public double calcolaAffitto(Giocatore e){
		
		//System.out.println(this.getValore());
		//System.out.println(e.getUltimoLancio());
		
		//System.out.println((this.getProprietario().possiedeDueSocieta()?Partita.MOLTIPLICATORE_SOCIETA_DUE
		//		:Partita.MOLTIPLICATORE_SOCIETA_UNA));

		return e.getUltimoLancio()*this.getValore()*
				(this.getProprietario().possiedeDueSocieta()?Partita.MOLTIPLICATORE_SOCIETA_DUE
						:Partita.MOLTIPLICATORE_SOCIETA_UNA);
		
	}

}
