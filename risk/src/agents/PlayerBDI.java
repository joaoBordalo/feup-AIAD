package agents;

import game.Player;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.environment.space2d.ContinuousSpace2D;
import jadex.extension.envsupport.environment.space2d.Grid2D;
import jadex.extension.envsupport.math.Vector2Int;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;

import java.util.Random;
import java.util.Vector;

/**
 * Created by Leonel Araújo on 22/11/2014.
 */

@Agent
public class PlayerBDI extends Player{
	
	 
	
	
    @Agent
    protected BDIAgent agent;

    @Belief
    protected ContinuousSpace2D space = (ContinuousSpace2D)agent.getParentAccess().getExtension("2dspace").get();

    ISpaceObject[] allTerritories = space.getSpaceObjectsByType("Territory");
    
    public ISpaceObject[] findMyTerritories (ISpaceObject[] allTerritories, Player player)
    {
    	ISpaceObject[] foundTerritories= new ISpaceObject[]{};
    	
    	int nfound=0;
    	
    	for(int i = 0; i< allTerritories.length; i++)
    	{
    		Player owner = (Player) allTerritories[i].getProperty("Player");
    		if(owner!=null && owner.getName().equals(this.getName()))
    		{
    			foundTerritories[nfound] = allTerritories[i];
    		}
    	}
    		
    	return foundTerritories;
    }
    
    
    @Belief
    protected ISpaceObject[] myTerritories = findMyTerritories(allTerritories, this);


    @AgentBody
    public void body(){
       /* ISpaceObject[] arvoresNoEspaco = space.getSpaceObjectsByType("Territory");

        Random r = new Random();

        int spaceHeight = space.getAreaSize().getXAsInteger();
        int spaceWidth = space.getAreaSize().getYAsInteger();

        myself.setProperty("position", new Vector2Int(r.nextInt(spaceWidth), r.nextInt(spaceHeight)));*/
    	System.out.println("tou vivo");
    	System.out.println("meus territorios: "+ myTerritories);

    }

}
