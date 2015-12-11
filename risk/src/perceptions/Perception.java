package perceptions;

import java.util.Vector;

public abstract class Perception {
	
	public Perception(){
		
	}
	
	/*
	 * in each perception, get all players except the player itself (method param)
	 */
	public abstract Vector<String> allPlayersInvolvedinPerception(String player);
}
