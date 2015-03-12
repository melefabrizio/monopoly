package monopoly;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;


/**
 * La classe che rappresenta una Partita.
 */
public class Partita implements MovementListener{
	
	public static final int IMPORTO_INIZIALE = 5000;

	private static final int IMPORTO_VIA = 500;

	public static final Double PERCENTUALE_AFFITTO = 0.1;

	public static final int MOLTIPLICATORE_SOCIETA_UNA = 4;

	public static final int MOLTIPLICATORE_SOCIETA_DUE = 10;

	/** Il Database */
	private DBManager db;
	
	/** Il vector di giocatori. */
	private Vector<Giocatore> giocatori;
	
	/** Il tabellone. */
	private Tabellone tabellone;
	
	/** L'iterator dei giocatori. */
	private Iterator<Giocatore> iterator;
	
	/**
	 * Costruttore di partita.
	 *
	 * @param db il database corrente dell'applicazione
	 * @param giocatori i giocatori partecipanti
	 * @throws SQLException l'eccezione SQL lanciata
	 */
	public Partita( DBManager db, Vector<Giocatore> giocatori) throws SQLException{
		this.db = db;
		this.giocatori = giocatori;
		tabellone = new Tabellone(db);
		tabellone.setMovementListener(this);
		iterator = this.giocatori.iterator(); 
		for(Giocatore giocatore:this.giocatori){
			tabellone.posiziona(giocatore, 0);
		}
		
	}
	
	/**
	 * Il metodo turno, che rappresenta il turno di un giocatore.
	 * Fa lanciare i dadi al giocatore e lo fa avanzare della quantità corrispondente.
	 * Controlla se i dadi sono uguali, e in caso di risposta affermativa fa rilanciare i dadi.
	 * Se si tirano dadi doppi per tre volte il metodo sposta il giocatore in prigione.
	 * 
	 */
	public void turno(){
		
		

		int avanzamento = 0;
		int ripetizione = 0;
		boolean ritira=false;
		Giocatore gCorrente;
		try{
			gCorrente = iterator.next();
		}catch (Exception e){
			this.iterator = this.giocatori.iterator();
			gCorrente = iterator.next();
		}


		do{
			Integer[] dadi = gCorrente.lanciaDadi();
			ritira = false;
			avanzamento = 0;
			avanzamento += dadi[0];
			avanzamento += dadi[1];
			
			
			System.out.print("[" +gCorrente.getNome()+"]"+" lancia i dadi ed escono:  ");
			System.out.print(+dadi[0]+" e "+dadi[1]+". ");
			System.out.print(dadi[0] == dadi[1]?"Dadi doppi!\n":"\n");

			System.out.print("Si muove da ");
			System.out.print(
					tabellone.getCasella(gCorrente).getNome()+"["+tabellone.getCasella(gCorrente).getId()+"]");
			
			tabellone.avanza(gCorrente, avanzamento);
			
			System.out.print(" a ");
			System.out.print(
					tabellone.getCasella(gCorrente).getNome()+"["+tabellone.getCasella(gCorrente).getId()+"]. \n");
			System.out.println("Il giocatore ha "+gCorrente.getCapitale()+" euro\n");
			if(dadi[0]==dadi[1]){
				ritira = true;
				ripetizione++;
				
				System.out.println("Ritira!");
			}
			if(ripetizione == 3){
				tabellone.spostaDiretto(gCorrente, Tabellone.PRIGIONE);
				System.out.println("In prigione!");
				ritira=false;
			}
		}while(ritira);
	}

	@Override
	public void onHop(Giocatore g, Casella c) {
		if(c.getId()==Tabellone.VIA){
			Banca.prelievo(g, IMPORTO_VIA);
		}
		
	}

	@Override
	public void onStop(Giocatore g, Casella c) {
		try{
			switch(c.getId()){
		
				case Tabellone.IN_PRIGIONE:
					tabellone.spostaDiretto(g, Tabellone.PRIGIONE);
					break;
				case Tabellone.T_LUSSO:
					Banca.versamento(g, Tabellone.T_LUSSO_I);
					break;
				case Tabellone.T_PATRIMONIALE:
					Banca.versamento(g, Tabellone.T_PATRIMONIALE_I);
					break;
				default:
					if(c.getProprieta() != null){
						System.out.println("La proprietà è acquistabile?");
						if(c.getProprieta().getProprietario() == null){
								acquistaProprieta(g, c.getProprieta());
						}
						else if(!c.getProprieta().getProprietario().equals(g)){
							
							calcolaPassaggio(g, c);
							
						}
						
					}
			}
		}catch(FallimentoException f){
			handleFallimento(f);
		}
		
	}

	private void calcolaPassaggio(Giocatore g, Casella c)
			throws FallimentoException {
		Giocatore proprietario = c.getProprieta().getProprietario();
		double affitto = c.getProprieta().calcolaAffitto(g);
		Banca.trasferimento(g, proprietario, (int) affitto);
		System.out.println(g.getNome()+" ha pagato a "+proprietario.getNome()+ " "+affitto);

	}

	private void handleFallimento(FallimentoException f) {
		f.toString();
		this.giocatori.remove(f.getGiocatore());
		
	}
	
	private void acquistaProprieta(Giocatore g, Proprieta p){
		try{
			Banca.versamento(g, p.getValore());
		}catch(FallimentoException e){
			System.out.println("Fondi non sufficienti");
			return;
		}
		g.aggiungiProprieta(p);
		p.setProprietario(g);
		System.out.println(g.getNome()+" ha acquistato "+p.getCasella().getNome());
		
		
		
	}
	
	
	
}
