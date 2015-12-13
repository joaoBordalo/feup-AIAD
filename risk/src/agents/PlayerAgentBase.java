package agents;

import actions.AttackAction;
import perceptions.AttackPerception;
import perceptions.ReinforceArmyPerception;
import game.Player;



/*
 * This class is a template for non BDI Agents
 * 
 */
public abstract class  PlayerAgentBase extends Player {

	
	
	
	
	//---------Constructor---------
	public PlayerAgentBase()
	{
	}
	
	//---------Agent Perceptions Methods---------
	public abstract void initPerceptions();
	
	/*
	 * Finds the Agent's Territory (by color)
	 * Counts number of territories that belongs to the Agent
	 * Calculate number of possible reinforcements
	 */
	public abstract void initReinforceArmyPerceptions();
	
	
	public abstract void initAttackPerceptions(); 
	
	
	public void initFortifyPerceptions()
	{
		
	}
	
	//---------Agent Actions Methods---------
	
	public abstract void createPossibleReinforcements(ReinforceArmyPerception perception);
	
	public abstract void createAttackTerritory(AttackPerception perception);
	
	public abstract void perfomAttackTeritory();
}
