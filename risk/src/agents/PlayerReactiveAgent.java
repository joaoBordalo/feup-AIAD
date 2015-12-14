package agents;


import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;

import actions.Action;
import actions.AttackAction;
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
	private ArrayList<Action> actions= new ArrayList<Action>();
	private int currentTurn = -1;
	private static int numberAgents = -1;
	private int agentNumberID;

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

		numberAgents++;
		agentNumberID=numberAgents;

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
		System.out.println("agente a jogar: " +agentNumberID);
		IFuture<IExtensionInstance> fut = reactAgent.getParentAccess().getExtension("2dspace");
		fut.addResultListener(new DefaultResultListener<IExtensionInstance>() {
			public void resultAvailable(IExtensionInstance cs) {
				myEnvironment = (Space2D) cs;

			}
		});

		if(
				(int)myEnvironment.getProperty("playerturn")==agentNumberID)
		{
			currentTurn=(int)myEnvironment.getProperty("turnnumber");
		ISpaceObject[] players=myEnvironment.getSpaceObjectsByType("Player");
		player=new Player((int)players[agentNumberID].getProperty("index"),
				(String)players[agentNumberID].getProperty("name"),
				(Color)players[agentNumberID].getProperty("color"),
				players.length);
		//if(myEnvironment.getProperty(""))
			//-Get info from environment and convert in Perceptions
			initPerceptions();
		//.Place Reinforcements
		Perception perc=perceptions.get(0);
		//Create possible actions to reinforce territories
		createPossibleReinforcements((ReinforceArmyPerception) perc);
		initAttackPerceptions();

		int i;
		for(i = 1; i < perceptions.size(); i++)
		{
			perc=perceptions.get(i);
			//System.out.println("entrei primeiro: "+ perc.getClass()+","+AttackPerception.class);
			//for Reinforce Amry Perception
			if(perc.getClass().equals(AttackPerception.class))
			{
				//create possible attacks
				createAttackTerritory((AttackPerception)perc);
			}
		}
		perfomAttackTeritory();



		cleanAllArrayLists();
		
		myEnvironment.setProperty("playerturn", ((int)myEnvironment.getProperty("playerturn"))+1);
		}
	}


	//---------Agent Perceptions Methods---------
	@Override
	public void initPerceptions() {
		
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

		ISpaceObject[]  allTerritories = myEnvironment.getSpaceObjectsByType("Territory");

		for(int i = 0; i < allTerritories.length;i++)
		{
			if(allTerritories[i].getProperty("ownerColor")==player.getColor())
			{
				//System.out.println("My terr = "+(String)allTerritories[i].getProperty("territoryname") );
				Vector<Territory> adjs = (Vector<Territory>) allTerritories[i].getProperty("adjacentes");
				for(int j = 0; j <adjs.size();j++)
				{

					if(!adjs.get(j).getOwnerColor().equals(player.getColor()))
					{
						//System.out.println("Territorio Inimigo!!");
						//System.out.println("(corAdj,corMinha)= ("+adjs.get(j).getOwnerColor() +"," + player.getColor()+")");

						Territory terrEnemyAdj=adjs.get(j);

						if((Integer)allTerritories[i].getProperty("armySize") +1 > terrEnemyAdj.getArmy().getArmySize()){
							//System.out.println("*****Tenho mais Army do que tu!!!*****");
							AttackPerception attackPerception=new AttackPerception((String)allTerritories[i].getProperty("territoryname"), terrEnemyAdj.getTerritoryName(), (int)allTerritories[i].getProperty("armySize")-1, terrEnemyAdj.getArmy().getArmySize());
							perceptions.add(attackPerception);
						}

					}
				}

			}
		}

		System.out.println("number Perceptions!!!: " + perceptions.size());

		System.out.println("****imprimir perceptions de ataque****");

		for(int i = 1; i < perceptions.size();i++)
		{
			AttackPerception att=(AttackPerception)perceptions.get(i);
			//System.out.println("(terrFRom,terTo)= ("+att.getTerritoryFrom() +"," + att.getTerritoryTo()+")");
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

		ReinforceArmyAction reinforceAction = new ReinforceArmyAction(weakestTerritory,lowestNumber);
		actions.add(reinforceAction);

		ISpaceObject[] terrs= myEnvironment.getSpaceObjectsByType("Territory");
		for(int i = 0; i < terrs.length;i++)
		{
			if(terrs[i].getProperty("territoryname").equals(weakestTerritory))
			{
				terrs[i].setProperty("armySize", lowestNumber);
				break;
			}

		}

	}

	@Override
	public void createAttackTerritory(AttackPerception perception) 
	{
		actions.add(new AttackAction(perception.getTerritoryFrom(), perception.getTerritoryTo(), perception.getArmySizeFrom(), perception.getArmySizeTo()));

	}

	@Override
	public void perfomAttackTeritory() {


		String bestTerritoryToAttack="";
		int remainingArmy=0;
		String myTerritory="";
		boolean goodAttack=false;

		for(int i = 1; i < actions.size();i++ )
		{
			AttackAction attack= (AttackAction)actions.get(i);
			//System.out.println("entrei");
			if(attack.isConquered())
			{
				if(attack.getRemainingArmy()>remainingArmy)
				{	
					remainingArmy=attack.getRemainingArmy();
					bestTerritoryToAttack=attack.getTerritoryTo();
					myTerritory=attack.getTerritoryFrom();
					goodAttack=true;
				}

			}

		}

		if(goodAttack)
		{
			ISpaceObject[] terrs= myEnvironment.getSpaceObjectsByType("Territory");
			for(int i = 0; i < terrs.length;i++)
			{
				if(terrs[i].getProperty("territoryname").equals(bestTerritoryToAttack))
				{
					//System.out.println("antiga cor do territorio: "+terrs[i].getProperty("ownerColor"));
					terrs[i].setProperty("armySize", remainingArmy);
					terrs[i].setProperty("ownerColor",player.getColor());
					//System.out.println("NOVA cor do territorio: "+terrs[i].getProperty("ownerColor"));
				}
				else
					if(terrs[i].getProperty("territoryname").equals(myTerritory))
					{
						//System.out.println("update:" +bestTerritoryToAttack);
						terrs[i].setProperty("armySize", 1);
					}

			}
		}




	}

	private void cleanAllArrayLists()
	{
		perceptions=new ArrayList<Perception>();
		actions= new ArrayList<Action>();
	}


}

