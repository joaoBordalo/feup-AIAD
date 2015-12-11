package agents;

import java.util.Vector;

import game.Territory;
import jadex.micro.MicroAgent;;


/*
 * This class is a template for non BDI Agents
 * 
 */
public class PlayerAgent extends MicroAgent {

	protected Vector<Territory> gameBoard;
	protected int currentRound;
	
	
	public PlayerAgent()
	{
		currentRound=0;
	}
	
	public void nextTurn()
	{
		currentRound++;
	}
	
	public int getCurrentRound()
	{
		return currentRound;
	}
	
	public void setCurrentRound(int currentRound)
	{
		this.currentRound=currentRound;
	}
}
