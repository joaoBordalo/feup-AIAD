package agents;



import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.environment.space2d.Space2D;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import perceptions.Perception;
import perceptions.ReinforceArmyPerception;
import game.Player;



/*
 * This class is a template for non BDI Agents
 * 
 */
public abstract class  PlayerAgentBase extends Player {

	protected int currentRound=0;
	protected ArrayList<Perception> perceptions= new ArrayList<Perception>();
	private  Space2D myEnvironment;
	protected Player player;
	
	
	
	//---------Constructor---------
	public PlayerAgentBase()
	{
		currentRound=0;
	}
	
	//---------Agent Perceptions Methods---------
	public void initPerceptions(Space2D myEnvironment )
	{
		player=new Player();
		player.setColor(Color.gray);
		this.myEnvironment=myEnvironment;
		//System.out.println("numero cenas: = "+this.myEnvironment.getSpaceObjectsByType("Territory").length);
		System.out.println("color " +player.getColor());
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
		HashMap<String, Integer> myTerritories=new HashMap<String, Integer>();
		ISpaceObject[]  allTerritories = myEnvironment.getSpaceObjectsByType("Territory");

		int numberMyTerritories=0;
		
		//get the Agent's territory and army size by its color identifier
		for(int i = 0; i < allTerritories.length;i++)
		{
			if(allTerritories[i].getProperty("ownerColor")==player.getColor())
			{
				//System.out.println("conteudo map: " + (String)allTerritories[i].getProperty("territoryname") + " - " +(Integer)allTerritories[i].getProperty("armySize") );
				myTerritories.put((String)allTerritories[i].getProperty("territoryname"), (Integer)allTerritories[i].getProperty("armySize"));
				numberMyTerritories++;
			}
		}
		System.out.println("number cinzento territorios: " + numberMyTerritories);
		//Check if Agent Controls continents for bonus reinforcements
		
		//calculate number possible of reinforcements
		
		int nReinforces= 3;//default number
		ReinforceArmyPerception reinforcePerception= new ReinforceArmyPerception(myTerritories, nReinforces);
		//System.out.println(myTerritories.size());
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
