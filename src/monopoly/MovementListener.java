package monopoly;

public interface MovementListener {
	
	abstract void onHop(Giocatore g, Casella c);
	abstract void onStop(Giocatore g, Casella c);

}
