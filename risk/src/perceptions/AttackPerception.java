package perceptions;

import game.Territory;

import java.util.Vector;

public class AttackPerception extends Perception {

	
	private Territory territoryFrom; // attacker's territory (from where the attacker's army comes)
	private Territory territoryTo; // defender's territory (to where the attacker's army goes)
	private boolean territoryConquerd;

	//---------Constructor---------
	public AttackPerception(Territory tFrom, Territory tTo, boolean tConquerd) {
		this.territoryFrom=tFrom;
		this.territoryTo=tTo;
		this.territoryConquerd=tConquerd;
	}


	//---------Override---------
	@Override
	public Vector<String> allPlayersInvolvedinPerception(String player) {
		// TODO Auto-generated method stub
		return null;
	}

	//---------Getters and Setters---------
	public Territory getTerritoryFrom() {
		return territoryFrom;
	}

	public void setTerritoryFrom(Territory territoryFrom) {
		this.territoryFrom = territoryFrom;
	}

	public Territory getTerritoryTo() {
		return territoryTo;
	}

	public void setTerritoryTo(Territory territoryTo) {
		this.territoryTo = territoryTo;
	}

	public boolean isTerritoryConquerd() {
		return territoryConquerd;
	}

	public void setTerritoryConquerd(boolean territoryConquerd) {
		this.territoryConquerd = territoryConquerd;
	}



}
