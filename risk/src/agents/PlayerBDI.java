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
    protected ISpaceObject[] allTerritories = space.getSpaceObjectsByType("Territory");

	//@Belief
//	protected ISpaceObject[] myTerritories = findMyTerritories(allTerritories, this);

//	@Belief
//	public int freeTerritories = countFreeTerritories(allTerritories);

	//@Belief
	//public ISpaceObject[] myPossibleTargets = findPossibleTargets(allTerritories,myTerritories, this);
    
	public Vector<Territory> SpaceObject2Territory (ISpaceObject[] allTerritories){
		Vector<Territory> allTerr = new Vector<Territory>();
		for (int i = 0; i < allTerritories.length; i++) {
			//Territory(int index, String territoryName, String continentName, IVector2 size, IVector2 boardCoord)
			Territory t = new Territory();
			int index = (int) allTerritories[i].getProperty("index");
			String territoryName = (String) allTerritories[i].getProperty("territoryname");
			String continentName = (String) allTerritories[i].getProperty("continentname");
			IVector2 size = (IVector2) allTerritories[i].getProperty("size");
			IVector2 boardCoord = (IVector2) allTerritories[i].getProperty("position");
			int shape = (int) allTerritories[i].getProperty("type");
			int testsize = (int) allTerritories[i].getProperty("textSize");
			Color owner = (Color) allTerritories[i].getProperty("ownerColor");

			t.setOwnerColor(owner);
			t.setIndex(index);
			t.setTextSize(testsize);
			t.setShapeType(shape);
			t.setTerritoryName(territoryName);
			t.setContinentName(continentName);
			t.setSize(size);
			t.setBoardCoord(boardCoord);

		}

		return allTerr;
	}

    public ISpaceObject[] findMyTerritories (ISpaceObject[] allTerritories, Player player)
    {
    	ISpaceObject[] foundTerritories= new ISpaceObject[]{};
    	
    	int nfound=0;
    	
    	for(int i = 0; i< allTerritories.length; i++)
    	{

    		//System.out.println(i);


    		Player owner = (Player) allTerritories[i].getProperty("Player");
    		if(owner!=null && owner.getName().equals(this.getName()))
    		{
    			foundTerritories[nfound] = allTerritories[i];
    		}
    	}
    		
    	return foundTerritories;
    }

    public int countFreeTerritories (ISpaceObject[] allTerritories){
		ISpaceObject[] freeTerritories= new ISpaceObject[]{};
		    	
		    	int nfound=0;
		    	
		    	for(int i = 0; i< allTerritories.length; i++)
		    	{
		    		Player owner = (Player) allTerritories[i].getProperty("Player");
		    		if(owner==null )
		    		{
		    			nfound ++ ;
		    		}
		    	}
		    		
		    	return nfound;
    }

	//public ISpaceObject[] findPossibleTargets(ISpaceObject[] allTerritories,ISpaceObject[] myTerritories, Player player){return ;}

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
       /* ISpaceObject[] arvoresNoEspaco = space.getSpaceObjectsByType("Territory");
        Random r = new Random();
		
        myself.setProperty("position", new Vector2Int(r.nextInt(spaceWidth), r.nextInt(spaceHeight)));*/
    	//System.out.println("tou vivo");
    //	System.out.println("meus territorios: "+ myTerritories.length);
    	//System.out.println("numero de territorios vazios: "+ freeTerritories );
    	//agent.adoptPlan(new Attack());
    	
    	
        
        if(agent.getProperty("name")!= null)
        {
        	a = space.getAvatar(agent.getComponentDescription());
        	
        System.out.println("agente imprime nome de avatar  " + agent.getProperty("name"));
        }
        
        


    }
    




}


