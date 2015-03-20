package monopoly;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;


/**
 * La classe che rappresenta una Partita.
 */
public class Partita implements MovementListener, CarteListener{
	
	public static final int IMPORTO_INIZIALE = 5000;

	private static final int IMPORTO_VIA = 500;

	public static final Double PERCENTUALE_AFFITTO = 0.1;

	public static final int MOLTIPLICATORE_SOCIETA_UNA = 4;

	public static final int MOLTIPLICATORE_SOCIETA_DUE = 10;

	private static final int CAUZIONE_PRIGIONE = 0;

	/** Il Database */
	private DBManager db;
	
	/** Il vector di giocatori. */
	private Vector<Giocatore> giocatori;
	
	/** Il tabellone. */
	private Tabellone tabellone;
	
	private Carte carte;
	/** L'iterator dei giocatori. */
	private Iterator<Giocatore> iterator;
	
	private StringBuffer outputBuffer;
	
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
		carte = db.getCarte();
		carte.setListener(this);
		iterator = this.giocatori.iterator(); 
		for(Giocatore giocatore:this.giocatori){
			tabellone.posiziona(giocatore, 0);
		}
		
		outputBuffer = new StringBuffer();
		
	}
	
	/**
	 * Il metodo turno, che rappresenta il turno di un giocatore.
	 * Fa lanciare i dadi al giocatore e lo fa avanzare della quantitÀÜ corrispondente.
	 * Controlla se i dadi sono uguali, e in caso di risposta affermativa fa rilanciare i dadi.
	 * Se si tirano dadi doppi per tre volte il metodo sposta il giocatore in prigione.
	 * 
	 */
	public void turno() throws FallimentoException{
		
		

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
			if(gCorrente.inPrigione()){
				Banca.versamento(gCorrente, CAUZIONE_PRIGIONE);
				gCorrente.inPrigione(false);
			}
			
			Integer[] dadi = gCorrente.lanciaDadi();
			ritira = false;
			avanzamento = 0;
			avanzamento += dadi[0];
			avanzamento += dadi[1];
			
			StringBuffer out = new StringBuffer();
			
			out.append("[" +gCorrente.getNome()+"]"+" lancia i dadi ed escono:  "+dadi[0]+" e "+dadi[1]+". ");
			
			out.append(dadi[0] == dadi[1]?"Dadi doppi!\n":"\n");

			
			out.append("Si muove da ");
			out.append(
					tabellone.getCasella(gCorrente).getNome()+"["+tabellone.getCasella(gCorrente).getId()+"]");
			
			tabellone.avanza(gCorrente, avanzamento);
			
			out.append(" a ");
			out.append(
					tabellone.getCasella(gCorrente).getNome()+"["+tabellone.getCasella(gCorrente).getId()+"]. \n");
			System.out.println(out.toString());
			System.out.println(outputBuffer.toString());
			System.out.println("Il giocatore ha "+gCorrente.getCapitale()+" euro\n");
			
			
			outputBuffer = new StringBuffer();
			if(dadi[0]==dadi[1]){
				ritira = true;
				ripetizione++;
				
				System.out.println("Ritira!");
			}
			if(ripetizione == 3){
				inPrigione(gCorrente);
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
	public void onStop(Giocatore g, Casella c) throws FallimentoException {
		try{
			switch(c.getId()){
		
				case Tabellone.IN_PRIGIONE:
					inPrigione(g);
					break;
				case Tabellone.T_LUSSO:
					Banca.versamento(g, Tabellone.T_LUSSO_I);
					break;
				case Tabellone.T_PATRIMONIALE:
					Banca.versamento(g, Tabellone.T_PATRIMONIALE_I);
					break;
				default:
					if(c.getProprieta() != null){
						outputBuffer.append("La proprieta' e' acquistabile?\n");
						if(c.getProprieta().getProprietario() == null){
								acquistaProprieta(g, c.getProprieta());
						}
						else if(!c.getProprieta().getProprietario().equals(g)){
							
							calcolaPassaggio(g, c);
							
						}
						
					}else if(c.getClass().equals(CasellaImprevisto.class)){
						carte.pescaImprevisto(g);
					}else if(c.getClass().equals(CasellaProbabilita.class)){
						carte.pescaProbabilita(g);
					}
			}
		}catch(FallimentoException f){
			handleFallimento(f);
		}
		
	}

	private void inPrigione(Giocatore g) {
		try{
			tabellone.spostaDiretto(g, Tabellone.PRIGIONE);
			g.inPrigione(true);
		}catch(Exception e){
			
		}
	}

	private void calcolaPassaggio(Giocatore g, Casella c)
			throws FallimentoException {
		Giocatore proprietario = c.getProprieta().getProprietario();
		double affitto = c.getProprieta().calcolaAffitto(g);
		Banca.trasferimento(g, proprietario, (int) affitto);
		outputBuffer.append(g.getNome()+" ha pagato a "+proprietario.getNome()+ " "+affitto);

	}

	private void handleFallimento(FallimentoException f) throws FallimentoException{
		
		f.toString();
		System.out.println(f.getGiocatore().getNome()+" è fallito!");
		if(f.getRimborsando()!=null){
			System.out.println("La banca rimborsa "+f.getRimborso()+ " a "+f.getRimborsando().getNome());
			Banca.prelievo(f.getRimborsando(), f.getRimborso());
		}
		for(Proprieta p:f.getGiocatore().getProprieta()){
			p.setProprietario(null);
		} 
		throw f;
		
	}
	
	private void acquistaProprieta(Giocatore g, Proprieta p){
		try{
			Banca.versamento(g, p.getValore());
		}catch(FallimentoException e){
			outputBuffer.append(" Fondi non sufficienti");
			return;
		}
		g.aggiungiProprieta(p);
		p.setProprietario(g);
		outputBuffer.append("["+g.getNome()+"]"+" ha acquistato "+p.getCasella().getNome());
		
		
		
	}
	
	public void rimuoviGiocatore(Giocatore g){
		this.giocatori.remove(g);
	}

	@Override
	public void onProbabilita(Giocatore g, Probabilita p) throws FallimentoException {
		outputBuffer.append(g+" pesca una carta Probabilita'\n");
		outputBuffer.append(p);
		switch(p.getId()){
			case 1:
				try{
					tabellone.spostaDiretto(g, Tabellone.VICOLO_CORTO);
				}catch(FallimentoException e){
					handleFallimento(e);
				}
				break;
			case 2:
				Banca.prelievo(g, 60);
				break;
			case 3:
				Banca.versamento(g, 125);
				break;
			case 4:
				tabellone.sposta(g, Tabellone.VIA);
				break;
			case 5:
				Banca.prelievo(g, 500);
				break;
			case 6:
				Banca.versamento(g, 250);
				break;
			case 7:
				inPrigione(g);
				break;
			case 8:
				for(Giocatore giocatore: giocatori){
					if(!giocatore.equals(g))
						Banca.trasferimento(giocatore, g, 25);
					
				}
				
		}
		
	}

	@Override
	public void onImprevisti(Giocatore g, Imprevisto i) throws FallimentoException {
		outputBuffer.append(g+" pesca una carta Imprevisti'\n");
		outputBuffer.append(i);
		switch(i.getId()){
			case 1:
				try{
					tabellone.spostaDiretto(g, Tabellone.LARGO_COLOMBO);
				}catch(FallimentoException e){
					handleFallimento(e);
				}
				break;
			case 2:
				inPrigione(g);
				break;
			case 3:
				tabellone.avanza(g, -3);
				break;
			case 4:
				try{
					tabellone.spostaDiretto(g, Tabellone.VIA_ACCADEMIA);
				}catch(FallimentoException e){
					handleFallimento(e);
				}
				break;
			case 5:
				Banca.versamento(g, 50);
				break;
			case 6:
				Banca.prelievo(g, 375);
				break;
			case 7:
				Banca.prelievo(g, 125);
				break;
			case 8:
				tabellone.sposta(g, Tabellone.VIA);
				break;
				
		}
		
	}
	

	
}
