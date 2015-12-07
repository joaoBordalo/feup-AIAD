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
	
	private HashMap<String, Territory> territories;
	
	public Player () {};
	
	public Player( int index, String name, Color color ) 
	{
		this.index = index;
		this.name = name;
		this.color = color;
		territories = new HashMap<String, Territory>();
		alive = true;

	}
	
	public void surrender() 
	{
		alive = false;
	}
	
	public HashMap<String, Territory> getTerritories() 
	{
		return territories;
	}
	
	
	//numero total de exercitos do jogador
	public int getTotalArmies() 
	{
		int totalArmies = 0;
		Iterator<String> iterator = getTerritories().keySet().iterator();
		while(iterator.hasNext()) 
		{
			totalArmies += territories.get(iterator.next()).getArmy().getArmySize();
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

	public void setTerritories(HashMap<String, Territory> territories) 
	{
		this.territories = territories;
	}

	//adiciona um territorio ao jogador
	public void addTerritory(Territory territory) 
	{
		territories.put(territory.getTerritoryName(), territory);
	}
	
	//remove o territorio do controlo do jogador
	public boolean removeTerritory(Territory territory) 
	{
		if(territories.containsKey(new Integer(territory.getIndex()))) 
		{
			territories.remove(new Integer(territory.getIndex()));
			return true;
		}
		return false;
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
		Object[] values = territories.values().toArray();
		return (Territory) (values[generator.nextInt(values.length)]);
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
