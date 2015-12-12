package perceptions;


import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

public class ReinforceArmyPerception extends Perception {
	
	//<gameBoardIndex, ArmyNumber>
	private HashMap<Integer,Integer> allocations;
	private int numberReinforcemnts;
	
	
	//---------Constructor---------
	public ReinforceArmyPerception(HashMap<Integer,Integer> allocs, int nReinforces) {
		this.allocations=allocs;
		this.numberReinforcemnts=nReinforces;
	}
	

	//---------Getters and Setters---------
	public HashMap<Integer, Integer> getAllocations() {
		return allocations;
	}

	public void setAllocations(HashMap<Integer, Integer> allocations) {
		this.allocations = allocations;
	}

	public int getNumberReinforcemnts() {
		return numberReinforcemnts;
	}

	public void setNumberReinforcemnts(int numberReinforcemnts) {
		this.numberReinforcemnts = numberReinforcemnts;
	}

}
