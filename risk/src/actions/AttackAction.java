package actions;

public class AttackAction extends Action {
	
	private String territoryFrom; // attacker's territory (from where the attacker's army comes)
	private String territoryTo; // defender's territory (to where the attacker's army goes)
	private int armySizeFrom;
	private int armySizeTo;
	private boolean conquered;
	private int remainingArmy;
	
	public AttackAction(String tFrom, String tTo, int armyFrom, int armyTo) {
		this.territoryFrom=tFrom;
		this.territoryTo=tTo;
		this.armySizeFrom=armyFrom;
		this.armySizeTo=armyTo;
		
		battle();
	}
	
	public int battle()
	{
		remainingArmy=armySizeFrom-armySizeTo;
		if(remainingArmy>0)
			conquered=true;
		else
			conquered=false;
		
		return remainingArmy;
	}
	
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

	public boolean isConquered() {
		return conquered;
	}

	public void setConquered(boolean conquered) {
		this.conquered = conquered;
	}

	public int getRemainingArmy() {
		return remainingArmy;
	}

	public void setRemainingArmy(int remainingArmy) {
		this.remainingArmy = remainingArmy;
	}

	


}
