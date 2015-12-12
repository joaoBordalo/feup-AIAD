package agents;


import game.Army;
import game.Player;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.*;
import jadex.bdiv3.runtime.ChangeEvent;
import jadex.bdiv3.runtime.IPlan;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.environment.space2d.ContinuousSpace2D;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.extension.envsupport.environment.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

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

//    @Belief
 //   protected ISpaceObject[] allTerritories = space.getSpaceObjectsByType("Territory");

	//@Belief
//	protected ISpaceObject[] myTerritories = findMyTerritories(allTerritories, this);

//	@Belief
//	public int freeTerritories = countFreeTerritories(allTerritories);

	//@Belief
	//public ISpaceObject[] myPossibleTargets = findPossibleTargets(allTerritories,myTerritories, this);
    

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
        System.out.println("agente imprime nome de avatar  " + a.getProperty("name"));


    }
    




}


