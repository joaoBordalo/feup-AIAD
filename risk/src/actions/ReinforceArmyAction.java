package actions;

public class ReinforceArmyAction extends Action{
	
	private int numberSoldiers;
	private String territoryName;
	
	//---------Constructor---------
	public ReinforceArmyAction(String territoryName, int numberSoldieres) {
		this.numberSoldiers=numberSoldieres;
		this.territoryName=territoryName;
	}
	
	//---------Getters and Setters---------
	public int getNumberSoldiers() {
		return numberSoldiers;
	}
	public void setNumberSoldiers(int numberSoldiers) {
		this.numberSoldiers = numberSoldiers;
	}
	public String getTerritoryName() {
		return territoryName;
	}
	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}

}
