package monopoly;

public class Stazione extends Proprieta {

	private Cardinali cardinale;
	public Stazione(Casella c, int valore, Cardinali cardinale) {
		super(c, valore);
		this.cardinale = cardinale;
	}

}
