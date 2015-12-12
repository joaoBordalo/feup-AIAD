package agents;



import jadex.extension.envsupport.environment.IEnvironmentSpace;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.environment.space2d.Space2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import perceptions.Perception;
import perceptions.ReinforceArmyPerception;
import game.Player;



/*
 * This class is a template for non BDI Agents
 * 
 */
public abstract class  PlayerAgentBase extends Player {

	protected int currentRound;
	protected ArrayList<Perception> perceptions;
	private  Space2D myEnvironment;
	
	
	
	//---------Constructor---------
	public PlayerAgentBase()
	{
		currentRound=0;
	}
	
	//---------Agent Perceptions Methods---------
	public void initPerceptions(Space2D myEnvironment )
	{
		this.myEnvironment=myEnvironment;
		initReinforceArmyPerceptions();
		//initAttackPerceptions();
		//initFortifyPerceptions();
	}
	
	/*
	 * Finds the Agent's Territory (by color)
	 * Counts number of territories that belongs to the Agent
	 * Calculate number of possible reinforcements
	 */
	public void initReinforceArmyPerceptions()
	{
		HashMap<Integer, Integer> myTerritories=new HashMap<Integer, Integer>();
		ISpaceObject[]  allTerritories = myEnvironment.getSpaceObjectsByType("Teritory");

		int numberMyTerritories=0;
		
		//get the Agent's territory and army size by its color identifier
		for(int i = 0; i < allTerritories.length;i++)
		{
			if(allTerritories[i].getProperty("color")==getColor())
			{
				numberMyTerritories++;
				myTerritories.put((Integer)allTerritories[i].getProperty("index"), (Integer)allTerritories[i].getProperty("armySize"));
			}
		}
		
		//Check if Agent Controls continents for bonus reinforcements
		
		//calculate number possible of reinforcements
		
		int nReinforces= 3;//default number
		ReinforceArmyPerception reinforcePerception= new ReinforceArmyPerception(myTerritories, nReinforces);
		
		perceptions.add(reinforcePerception);
	}
	
	public void initAttackPerceptions()
	{
		
	}
	
	public void initFortifyPerceptions()
	{
		
	}
	
	

	
	public void nextTurn()
	{
		currentRound++;
	}
	
	//---------Getters and Setters---------
	public int getCurrentRound()
	{
		return currentRound;
	}
	
	public void setCurrentRound(int currentRound)
	{
		this.currentRound=currentRound;
	}
	
	public Space2D getMyEnvironment() 
	{
		return myEnvironment;
	}
	
	public void setMyEnvironment(Space2D myEnvironment) 
	{
		this.myEnvironment = myEnvironment;
	}
}
