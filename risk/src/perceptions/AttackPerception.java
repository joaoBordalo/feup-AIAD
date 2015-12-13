package perceptions;

import game.Territory;

import java.util.Vector;

public class AttackPerception extends Perception {

	
	private String territoryFrom; // attacker's territory (from where the attacker's army comes)
	private String territoryTo; // defender's territory (to where the attacker's army goes)
	private int armySizeFrom;
	private int armySizeTo;
	//---------Constructor---------
	public AttackPerception(String tFrom, String tTo, int armyFrom, int armyTo) {
		this.territoryFrom=tFrom;
		this.territoryTo=tTo;
		this.armySizeFrom=armyFrom;
		this.armySizeTo=armyTo;
	}
	
	//---------Getters and Setters---------
	public String getTerritoryFrom() {
		return territoryFrom;
	}
	public void setTerritoryFrom(String territoryFrom) {
		this.territoryFrom = territoryFrom;
	}
	public String getTerritoryTo() {
		return territoryTo;
	}
	public void setTerritoryTo(String territoryTo) {
		this.territoryTo = territoryTo;
	}
	public int getArmySizeFrom() {
		return armySizeFrom;
	}
	public void setArmySizeFrom(int armySizeFrom) {
		this.armySizeFrom = armySizeFrom;
	}
	public int getArmySizeTo() {
		return armySizeTo;
	}
	public void setArmySizeTo(int armySizeTo) {
		this.armySizeTo = armySizeTo;
	}
	
}
