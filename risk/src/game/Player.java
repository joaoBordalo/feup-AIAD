package game;
import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;


public class Player {
	
	private int index;
	private String name;
	private boolean alive;
	
	private Color color;
	
	private Vector<Territory> territories;
	
	private int availableSoldierNumber;
	
	public Player () {};
	
	public Player( int index, String name, Color color , int totalNumberOfPlayers) 
	{
		this.index = index;
		this.name = name;
		this.color = color;
		territories = new Vector <Territory>();
		alive = true;
		
		switch (totalNumberOfPlayers) {
		case 2:
			availableSoldierNumber=40;
			break;

		default:
			availableSoldierNumber=20;
			break;
		}

	}
	
	public int getAvailableSoldierNumber() {
		return availableSoldierNumber;
	}

	public void setAvailableSoldierNumber(int availableSoldierNumber) {
		this.availableSoldierNumber = availableSoldierNumber;
	}

	public void surrender() 
	{
		alive = false;
	}
	

	
	
	//numero total de exercitos do jogador
	public int getTotalArmies() 
	{
		int totalArmies = 0;
		for(int i= 0; i<territories.size();i++)
		{
			totalArmies += territories.get(i).getArmy().getArmySize();
		}
		return totalArmies;
	}

	public int getIndex() 
	{
		return index;
	}

	public void setIndex(int index) 
	{
		this.index = index;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public boolean getAlive() 
	{
		return alive && getTerritories().size() > 0;
	}

	public void setAlive(boolean alive) 
	{
		this.alive = alive;
	}

	public Color getColor() 
	{
		return color;
	}

	public void setColor(Color color) 
	{
		this.color = color;
	}



	public Vector<Territory> getTerritories() {
		return territories;
	}

	public void setTerritories(Vector<Territory> territories) {
		this.territories = territories;
	}

	//adiciona um territorio ao jogador
	public void addTerritory(Territory territory) 
	{
		territories.add(territory);
	}
	

	
	public int getNumTerritories() 
	{
		return territories.size();
	}
	
	public Territory getTerritory(int index) 
	{
		return territories.get(new Integer(index));
	}
	
	//pode ser fixe
	public Territory getRandomTerritory() 
	{
		Random generator = new Random();
		
		return (Territory) (territories.get(generator.nextInt(territories.size())));
	}


	//this function runs all owned territories and for each one it runs over its adjacent territories,
	// adding to the return vector only if its not there yet or it bellongs to an enemy player
	// NOT TESTED YET
	public Vector<Territory> getTargetableTerritories(){
		Vector<Territory> targetable = new Vector<Territory>();
		int key =0;
		for (int i = 0; i < territories.size(); i++) {
			Vector<Territory> temp = new Vector<Territory>();
			temp = territories.get(i).getAdjacentTerr();
			for (int j = 0; j < temp.size() ; j++) {
				if( (!targetable.contains(temp.get(j))) && (temp.get(j).getOwner() != this) ) {
					targetable.add(temp.get(j));
					System.out.println("added  \n" + temp.get(j).getTerritoryName());
				}
				else
				{
				System.out.println("discarded  \n" +  temp.get(j).getTerritoryName() );
				}
			}
		}
		return targetable;
	}
	
	public String toString() {
		return getName() + ":     " + getTotalArmies();
	}
}
