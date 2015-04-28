package monopoly;
/**
 * Interfaccia che definisce un oggetto in grado di gestire con successo la pesca di carte probabilitˆ e imprevisti.
 * 
 * @author Fabrizio Mele
 *
 */
public interface CarteListener {

	/**
	 * Il metodo astratto definisce le azioni da compiere quando una certa carta viene pescata da un tale giocatore.
	 * @param giocatore Il giocatore che ha pescato la carta
	 * @param probabilita La carta pescata
	 * @throws FallimentoException
	 */
	public void onProbabilita(Giocatore giocatore, Probabilita probabilita) throws FallimentoException;
	/**
	 * Il metodo astratto definisce le azioni da compiere quando una certa carata viene pescata da un tale giocatore.
	 * @param giocatore Il giocatore che ha pescato la carta
	 * @param imprevisto La carta pescata
	 * @throws FallimentoException
	 */
	public void onImprevisti(Giocatore giocatore, Imprevisto imprevisto) throws FallimentoException;
}
