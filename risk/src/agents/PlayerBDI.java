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
    protected ContinuousSpace2D space = (ContinuousSpace2D)agent.getParentAccess().getExtension("2dspace").get();
    
    @Belief
    protected ISpaceObject[] allTerritories = space.getSpaceObjectsByType("Territory");
    

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
    
    @Belief
    protected ISpaceObject[] myTerritories = findMyTerritories(allTerritories, this);

    
    @Belief
    public int freeTerritories = countFreeTerritories(allTerritories);

    @Plan(trigger= @Trigger (factchangeds="freeTerritories"))
    public void teste(ChangeEvent event)
    {
    	int v = (int) event.getValue();
		System.out.println("New value: " + v);
    }
    
    
    
    @AgentBody
    public void body(){
    	
       /* ISpaceObject[] arvoresNoEspaco = space.getSpaceObjectsByType("Territory");
        Random r = new Random();
		
        myself.setProperty("position", new Vector2Int(r.nextInt(spaceWidth), r.nextInt(spaceHeight)));*/
    	System.out.println("tou vivo");
    	System.out.println("meus territorios: "+ myTerritories.length);
    	System.out.println("numero de territorios vazios: "+ freeTerritories );
    	agent.adoptPlan(new Attack());

    }
    
    @Plan
    public class Attack{
		private long startingTime;
		 
		@PlanAPI
		protected IPlan plan;
 
		@PlanContextCondition(beliefs="")
		public boolean checkCondition() {
			System.out.println("check condition");
			return true;
		}
 
		@PlanBody
		public void body() {
			System.out.println("body");
		}
 
		@PlanPassed
		public void passed() {

			////como alterar um territorio
			System.out.println("plan passed");
			Player z = new Player(7,"ZE", new Color(4 ,6 ,8));  ///Como referenciar o Player ?
			Army ze_army = new Army(z , 10);
			System.out.println(allTerritories[0].getId() );
			System.out.println(allTerritories[0].getType() );
			System.out.println(allTerritories[0].getPropertyNames());
			
			
			
			
			
			allTerritories[0].setProperty("army", ze_army) ;    ///Suponho que isto faz o set...
			allTerritories[0].setProperty("armySize", 11); //descomenta isto e ve o que acontece, zezao
			System.out.println(allTerritories[0].getProperty("army"));  /// mas como confirmar? territoryname
			allTerritories[0].setProperty("territoryname", "ZeLandia") ;  //  defacto altera

			
			
			Army test = (Army) allTerritories[0].getProperty("army");
			System.out.println(test.getPlayer() );
			System.out.println(test.getArmySize() );
			
           
			System.out.println(space.getSpaceObjectsByType("Territory")[0].getProperty("armySize"));
			///? e esta hein   :)
		}
 
		@PlanAborted
		public void aborted() {
			System.out.println("plan aborted");
		}
 
		@PlanFailed
		public void failed(Exception e) {
			System.out.println("plan failed");
		}
    }
   

}


