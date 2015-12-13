package agents;


import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Vector;

import actions.Action;
import actions.ReinforceArmyAction;
import game.Player;
import game.Territory;
import perceptions.AttackPerception;
import perceptions.Perception;
import perceptions.ReinforceArmyPerception;
import jadex.bridge.modelinfo.IExtensionInstance;
import jadex.commons.future.DefaultResultListener;
import jadex.commons.future.IFuture;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.environment.space2d.Space2D;
import jadex.micro.MicroAgent;
import jadex.micro.annotation.*;;


@Agent
@Description("A Reactive Agent")
public class PlayerReactiveAgent extends PlayerAgentBase {
	protected ArrayList<Perception> perceptions= new ArrayList<Perception>();
	protected Space2D myEnvironment;
	protected Player player;
	private ArrayList<Action> actions;

	@Agent
	protected MicroAgent reactAgent;
	//protected Space2D space;

	@AgentCreated
	public void initSpace(){
		IFuture<IExtensionInstance> fut = reactAgent.getParentAccess().getExtension("2dspace");
		fut.addResultListener(new DefaultResultListener<IExtensionInstance>() {
			public void resultAvailable(IExtensionInstance cs) {
				myEnvironment = (Space2D) cs;

			}
		});
	}

	/*
	 * Mindset of the Reactive Agent:
	 * -Get info from environment and convert in Perceptions
	 * -From Perceptions make actions in the following order:
	 * 		.Place Reinforcements
	 * 		.Perform Attacks
	 * 		.Fortify
	 */
	@AgentBody
	public void body()
	{
		IFuture<IExtensionInstance> fut = reactAgent.getParentAccess().getExtension("2dspace");
		fut.addResultListener(new DefaultResultListener<IExtensionInstance>() {
			public void resultAvailable(IExtensionInstance cs) {
				myEnvironment = (Space2D) cs;

			}
		});
		//-Get info from environment and convert in Perceptions
		initPerceptions();
		/*System.out.println("João TESTE INIT!!!");
	System.out.println("number Perceptions= "+ perceptions.size());
	ReinforceArmyPerception re= (ReinforceArmyPerception) perceptions.get(0);
	System.out.println("number my territories= "+re.getAllocations().size());*/

		//.Place Reinforcements
		for(int i = 0; i < perceptions.size(); i++)
		{
			Perception perc=perceptions.get(i);
			
			//for Reinforce Amry Perception
			if(perc.getClass().equals(ReinforceArmyPerception.class))
			{
				//Create possible actions to reinforce territories
				createPossibleReinforcements((ReinforceArmyPerception) perc);
			}
			else
				if(perc.getClass().equals((AttackPerception)perc))
				{
					//create possible attacks
				}

		}
	}


	//---------Agent Perceptions Methods---------
	@Override
	public void initPerceptions() {
		player=new Player();
		player.setColor(Color.gray);
		System.out.println("o que guardo: " +this.myEnvironment);
		//System.out.println("numero cenas: = "+this.myEnvironment.getSpaceObjectsByType("Territory").length);
		System.out.println("color " +player.getColor());
		initReinforceArmyPerceptions();
		//initAttackPerceptions();
		//initFortifyPerceptions();
	}

	@Override
	public void initReinforceArmyPerceptions() {
		HashMap<String, Integer> myTerritories=new HashMap<String, Integer>();
		ISpaceObject[]  allTerritories = myEnvironment.getSpaceObjectsByType("Territory");

		int numberMyTerritories=0;

		//get the Agent's territory and army size by its color identifier
		for(int i = 0; i < allTerritories.length;i++)
		{
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

		int nReinforces= 10;//default number
		ReinforceArmyPerception reinforcePerception= new ReinforceArmyPerception(myTerritories, nReinforces);
		//System.out.println(myTerritories.size());
		perceptions.add(reinforcePerception);

	}

	@Override
	public void initAttackPerceptions() {
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
			if(myTerritories.containsKey(allTerritories[i].getProperty("territoryname"))); // se territorio Ã© do jogador
			{
				Territory t = new Territory(allTerritories[i]);
				Vector<Territory> v = new Vector<Territory>();
				v = t.getAdjacentTerr(); // vai buscar adjacents
				for (int j = 0; j < v.size(); j++) {
					AttackPerception a = new AttackPerception(t, v.get(j), false);
					perceptions.add(a);
				}
			}
		}
	}




	//---------Agent Actions Methods---------
	@Override
	public void createPossibleReinforcements(ReinforceArmyPerception perception) {
		
		//find weakest territory
		String weakestTerritory="";
		Integer lowestNumber=1000;
		for(Entry<String, Integer> it : perception.getAllocations().entrySet()){
			if(it.getValue()<lowestNumber)
			{
				lowestNumber=it.getValue();
				weakestTerritory=it.getKey();	
			}	
		}
		System.out.println("weakest terriotry : ("+ weakestTerritory +","+lowestNumber+ ")");
		
		lowestNumber+=perception.getNumberReinforcemnts();
		perception.getAllocations().put(weakestTerritory,lowestNumber);
		ISpaceObject[] terrs= myEnvironment.getSpaceObjectsByType("Territory");
		
		ReinforceArmyAction reinforceAction = new ReinforceArmyAction(weakestTerritory,lowestNumber);
		actions.add(reinforceAction);
		
		for(int i = 0; i < terrs.length;i++)
		{
			if(terrs[i].getProperty("territoryname").equals(weakestTerritory))
			{
				System.out.println("ENTROU??!!!");
				terrs[i].setProperty("armySize", lowestNumber);
			}
					
		}
	
	}


}

