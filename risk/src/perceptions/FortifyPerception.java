package perceptions;

import game.Territory;

import java.util.Vector;

public class FortifyPerception extends Perception {

	private Territory territoryFrom;
	private Territory teritoryTo;
	
	//---------Constructor---------
	public FortifyPerception(Territory tFrom, Territory tTo) {
		this.territoryFrom=tFrom;
		this.teritoryTo=tTo;
	}
	
	
	//---------Getters and Setters---------
	public Territory getTerritoryFrom() {
		return territoryFrom;
	}

	public void setTerritoryFrom(Territory territoryFrom) {
		this.territoryFrom = territoryFrom;
	}

	public Territory getTeritoryTo() {
		return teritoryTo;
	}

	public void setTeritoryTo(Territory teritoryTo) {
		this.teritoryTo = teritoryTo;
	}
}
