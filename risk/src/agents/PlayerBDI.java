package agents;


import game.Army;
import game.Player;
import game.Territory;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.*;
import jadex.bdiv3.runtime.ChangeEvent;
import jadex.bdiv3.runtime.IPlan;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.environment.space2d.ContinuousSpace2D;
import jadex.extension.envsupport.math.IVector2;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.extension.envsupport.environment.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

@Agent
public class PlayerBDI extends Player{

    @Agent
    protected BDIAgent agent;

	@Belief
	protected String name = this.getName();



    @Belief
    protected ContinuousSpace2D space = (ContinuousSpace2D)agent.getParentAccess().getExtension("2dspace").get();

    @Belief
    protected ISpaceObject a = space.getAvatar(agent.getComponentDescription());

    @Belief
    protected Vector<Territory> allTerritories = SpaceObject2Territory (space.getSpaceObjectsByType("Territory") );

	@Belief
	protected Vector<Territory> myTerritories = findMyTerritories(allTerritories, this);

	@Belief
	public Vector<Territory> myPossibleTargets;// = findPossibleTargets(allTerritories,myTerritories, this);

	//aux function
	public Vector<Territory> SpaceObject2Territory (ISpaceObject[] allTerritories){
		Vector<Territory> allTerr = new Vector<Territory>();
		for (int i = 0; i < allTerritories.length; i++) {
			//Territory(int index, String territoryName, String continentName, IVector2 size, IVector2 boardCoord)
			Territory t = new Territory(allTerritories[i]);
			allTerr.add(t);
		}

		return allTerr;
	}

    public Vector<Territory> findMyTerritories (Vector<Territory> allTerritories, Player player)
    {
		Vector<Territory> foundTerritories= new Vector<Territory>();
    	
    	int nfound=0;
    	
    	for(int i = 0; i< allTerritories.size(); i++)
    	{
    		Player owner = (Player) allTerritories.get(i).getOwner();
    		if(owner!=null && owner.getName().equals(this.getName()))
    		{
    			foundTerritories.add(allTerritories.get(i));
    		}
    	}
    		
    	return foundTerritories;
    }



	public Vector<Territory> findPossibleTargets(Vector<Territory> allTerritories,Vector<Territory> myTerritories, Player player){
        Vector<Territory> targets = new Vector<Territory>();
        return targets;}

    /*
    @Plan(trigger= @Trigger (factchangeds="name"))
    public void teste(ChangeEvent event)
    {
    	String v = (String) event.getValue();
		System.out.println("im alive: " + v);

    }
    
    */
    
    @AgentBody
    public void body(){


    //	System.out.println("tou vivo");
	//	System.out.println("meus territorios: "+ myTerritories.length);
    //	System.out.println("meus territorios: "+ myTerritories.length);

    	//agent.adoptPlan(new Attack());
    	
    	
        
        if(agent.getProperty("name")!= null)
        {
        	a = space.getAvatar(agent.getComponentDescription());
        	
        System.out.println("agente imprime nome de avatar  " + agent.getProperty("name"));
        }
        
        


    }
    




}


