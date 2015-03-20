package monopoly;

public interface CarteListener {

	public void onProbabilita(Giocatore g, Probabilita p) throws FallimentoException;
	public void onImprevisti(Giocatore g, Imprevisto i) throws FallimentoException;
}
