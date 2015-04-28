package monopoly;
import java.util.*;
public class Carte {

	private Vector<Probabilita> probabilita;
	private Vector<Imprevisto> imprevisti;
	private CarteListener listener;
	private int lastProbabilita;
	private int lastImprevisto;
	public Carte(Vector<Probabilita> p, Vector<Imprevisto> i){
		probabilita = p;
		imprevisti = i;
		lastProbabilita = -1;
		lastImprevisto = -1;
		Collections.shuffle(probabilita);
		Collections.shuffle(imprevisti);

	}
	
	public void setListener(CarteListener listener){
		this.listener=listener;
	}
	
	public void pescaProbabilita(Giocatore g) throws FallimentoException{
		
		Probabilita pescata = null;
		try{
			pescata = probabilita.get(lastProbabilita+1);
		}catch(Exception e){
			lastProbabilita = -1;
			pescata = probabilita.get(lastProbabilita+1);
		}
		lastProbabilita++;
		
		listener.onProbabilita(g, pescata);
	}
	
	public void pescaImprevisto(Giocatore g) throws FallimentoException{
			
			Imprevisto pescata = null;
			try{
				pescata = imprevisti.get(lastImprevisto+1);
			}catch(Exception e){
				lastImprevisto = -1;
				pescata = imprevisti.get(lastImprevisto+1);
			}
			
			lastImprevisto++;
			
			listener.onImprevisti(g, pescata);
	}
	

}
