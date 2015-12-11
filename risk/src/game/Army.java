package game;
import java.util.Random;


public class Army {
	
	private Player player;
	
	private int armySize;



	public Army(Player player, int armySize) 
	{
		this.player = player;
		this.armySize = armySize;
	}

	
	public boolean addArmy(int numberOfUnits) 
	{
		this.armySize += numberOfUnits;
		if(this.armySize < 0) {
			return false;
		}else {
			return true;
		}
	}
	
	
	//quem ataca lanca um dado a menos
	public int getRoll( boolean attack ) 
	{
		int armyCount = getArmySize();
		if(attack) 
		{
			armyCount--;
		}
		
		int totalRoll = 0;
		for(int i=0; i<armyCount; i++) 
		{
			Random generator = new Random();
			int diceRoll = generator.nextInt(6)+1;
			totalRoll += diceRoll;
		}
		return totalRoll;
	}


	public Player getPlayer() 
	{
		return player;
	}


	public void setPlayer(Player player) 
	{
		this.player = player;
	}


	public int getArmySize() 
	{
		return armySize;
	}


	public void setArmySize(int armySize) 
	{
		this.armySize = armySize;
	}
}