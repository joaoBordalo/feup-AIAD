package agents;


import game.Army;
import game.Player;
import game.Territory;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.*;
import jadex.bdiv3.runtime.ChangeEvent;
import jadex.bdiv3.runtime.IPlan;
import jadex.bridge.modelinfo.IExtensionInstance;
import jadex.commons.future.DefaultResultListener;
import jadex.commons.future.IFuture;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.environment.space2d.ContinuousSpace2D;
import jadex.extension.envsupport.environment.space2d.Space2D;
import jadex.extension.envsupport.math.IVector2;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.extension.envsupport.environment.*;
import jadex.micro.annotation.AgentCreated;
import perceptions.AttackPerception;
import perceptions.Perception;
import perceptions.ReinforceArmyPerception;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

@Agent
public class PlayerBDI extends Player{


    @Agent
    protected BDIAgent agent;

	protected ArrayList<Perception> perceptions= new ArrayList<Perception>();
	private  Space2D myEnvironment;
	protected Player player;

/*
	@Belief
	protected Vector<Territory> allTerritories ;
	//= SpaceObject2Territory (space.getSpaceObjectsByType("Territory") );

	@Belief
	protected Vector<Territory> myTerritories;
	//= findMyTerritories(allTerritories, this);

	@Belief
	public Vector<Territory> myPossibleTargets;
	//= findPossibleTargets(allTerritories,myTerritories, this);

*/
	@AgentCreated
	public void init()
	{


		IFuture<IExtensionInstance> fut = agent.getParentAccess().getExtension("2dspace");
		fut.addResultListener(new DefaultResultListener<IExtensionInstance>() {
			public void resultAvailable(IExtensionInstance cs) {
				myEnvironment = (Space2D) cs;
				ISpaceObject[] i = myEnvironment.getSpaceObjectsByType("Territory");
				//allTerritories = SpaceObject2Territory(i);

			}
		});

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
			//
			if(allTerritories[i].getProperty("ownerColor")==player.getColor())
			{
				System.out.println("conteudo map: " + (String)allTerritories[i].getProperty("territoryname") + " - " +(Integer)allTerritories[i].getProperty("armySize") );
				myTerritories.put((String)allTerritories[i].getProperty("territoryname"), (Integer)allTerritories[i].getProperty("armySize"));
				numberMyTerritories++;
			}
		}
		//System.out.println("number cinzento territorios: " + numberMyTerritories);
		//Check if Agent Controls continents for bonus reinforcements

		//calculate number possible of reinforcements

		int nReinforces= 3;//default number
		ReinforceArmyPerception reinforcePerception= new ReinforceArmyPerception(myTerritories, nReinforces);
		//System.out.println(myTerritories.size());
		perceptions.add(reinforcePerception);


	}

	public void initAttackPerceptions()
	{
		HashMap<String, Integer> myTerritories=new HashMap<String, Integer>();
		ISpaceObject[]  allTerritories = myEnvironment.getSpaceObjectsByType("Territory");

		int numberMyTerritories=0;

		//get the Agent's territory and army size by its color identifier
		for(int i = 0; i < allTerritories.length;i++)
		{
			if(allTerritories[i].getProperty("ownerColor")==player.getColor())
			{
				myTerritories.put((String)allTerritories[i].getProperty("territoryname"), (Integer)allTerritories[i].getProperty("armySize"));
				numberMyTerritories++;
			}
		}


		// for each territory
		for (int i = 0; i < allTerritories.length; i++) {
			if(myTerritories.containsKey(allTerritories[i].getProperty("territoryname"))); // se territorio é do jogador
			{
				Territory t = new Territory(allTerritories[i]);
				Vector<Territory> v = new Vector<Territory>();
				v = t.getAdjacentTerr(); // vai buscar adjacents
				for (int j = 0; j < v.size(); j++) {
					//AttackPerception a = new AttackPerception(t, v.get(j), false);
					//perceptions.add(a);
				}
			}
		}
	}

	public void initFortifyPerceptions()
	{

	}





	/*
    @Belief
    protected ISpaceObject a = space.getAvatar(agent.getComponentDescription());
*/
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
        for (int i = 0; i < myTerritories.size(); i++) {
            Vector<Territory> temp = new Vector<Territory>();
            temp = myTerritories.get(i).getAdjacentTerr();
            for (int j = 0; j < temp.size(); j++) {
                if( !targets.contains(temp.get(j)))
                    targets.add(temp.get(j));
            }
        }
        return targets;}

    
    @AgentBody
    public void body(){

    	System.out.println("tou vivo");
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


