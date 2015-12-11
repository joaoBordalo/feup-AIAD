package perceptions;


import java.util.Hashtable;
import java.util.Vector;

public class ReceiveArmyPerception extends Perception {
	
	//<TerritoryName, ArmyNumber>
	private Hashtable<String,Integer> allocations;
	
	
	//---------Constructor---------
	public ReceiveArmyPerception(Hashtable<String,Integer> allocs) {
		this.allocations=allocs;
	}
	
	//---------Override---------
	@Override
	public Vector<String> allPlayersInvolvedinPerception(String player) {
		// TODO Auto-generated method stub
		return null;
	}
	//---------Getters and Setters---------
	public Hashtable<String, Integer> getAllocations() {
		return allocations;
	}

	public void setAllocations(Hashtable<String, Integer> allocations) {
		this.allocations = allocations;
	}

}
