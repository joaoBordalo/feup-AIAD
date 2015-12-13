package perceptions;


import java.util.HashMap;


public class ReinforceArmyPerception extends Perception {
	
	//<gameBoardIndex, ArmyNumber>
	private HashMap<String,Integer> allocations;
	private int numberReinforcemnts;
	
	
	//---------Constructor---------
	public ReinforceArmyPerception(HashMap<String,Integer> allocs, int nReinforces) {
		super();
		this.allocations=allocs;
		this.numberReinforcemnts=nReinforces;
	}
	

	//---------Getters and Setters---------
	public HashMap<String, Integer> getAllocations() {
		return allocations;
	}

	public void setAllocations(HashMap<String, Integer> allocations) {
		this.allocations = allocations;
	}

	public int getNumberReinforcemnts() {
		return numberReinforcemnts;
	}

	public void setNumberReinforcemnts(int numberReinforcemnts) {
		this.numberReinforcemnts = numberReinforcemnts;
	}

}
