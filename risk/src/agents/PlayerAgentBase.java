package agents;



import game.Territory;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.environment.space2d.Space2D;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import perceptions.AttackPerception;
import perceptions.Perception;
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

}
