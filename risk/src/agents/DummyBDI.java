package agents;


import game.Territory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import plans.DummyPlan;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Body;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalCreationCondition;
import jadex.bdiv3.annotation.GoalParameter;
import jadex.bdiv3.annotation.GoalResult;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanAPI;
import jadex.bdiv3.annotation.PlanAborted;
import jadex.bdiv3.annotation.PlanBody;
import jadex.bdiv3.annotation.PlanContextCondition;
import jadex.bdiv3.annotation.PlanFailed;
import jadex.bdiv3.annotation.PlanPassed;
import jadex.bdiv3.annotation.Plans;
import jadex.bdiv3.runtime.ChangeEvent;
import jadex.bdiv3.runtime.IPlan;
import jadex.bdiv3.runtime.impl.PlanFailureException;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.AgentCreated;
import jadex.micro.annotation.Description;
import jadex.bdiv3.annotation.Trigger;
import jadex.bridge.modelinfo.IExtensionInstance;
import jadex.commons.future.DefaultResultListener;
import jadex.commons.future.Future;
import jadex.commons.future.IFuture;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.environment.space2d.Space2D;




@Agent
//@Plans(@Plan(body=@Body(DummyPlan.class)))
public class DummyBDI {
	
	@Agent protected BDIAgent bdi;
	
	  @Belief
	  protected boolean context = true;
	 
	  @Belief
	  protected String saudacaoi;
	  
	  @Belief
	  protected Space2D space;
	  


	@Belief
	  protected ISpaceObject vision;
	@Belief
	protected List<String> saudacoesAgente= new ArrayList<String>();
	
	@AgentCreated
	public void init()
	{
		
		
		IFuture<IExtensionInstance> fut = bdi.getParentAccess().getExtension("2dspace");
		fut.addResultListener(new DefaultResultListener<IExtensionInstance>() {
			public void resultAvailable(IExtensionInstance cs) {
				space = (Space2D) cs;
			
			}
		});
		
		//saudacoesAgente = new Vector<String>();
		ISpaceObject[] vision= new ISpaceObject[]{};
		saudacoesAgente.add("ola");
		
		saudacoesAgente.add("adeus");
	}
	
	
	@AgentBody
	public void body()
	{
		for(int i =0; i<space.getSpaceObjectsByType("Territory").length; i++)
		{
			
		System.out.println("Time for a chat, buddy: "+ space.getSpaceObjectsByType("Territory")[i].getProperty("continentname"));
		ISpaceObject territo = space.getSpaceObjectsByType("Territory")[i];
		territo.setProperty("armySize", 1);
		
		
		
		}
			
		
	  /*bdi.adoptPlan(new DummyPlan());
	  
	  try
	  {
	  bdi.adoptPlan("dizerbomdia");

	  }catch(Exception e)
	  {
		  e.printStackTrace();
	  }
	  
	  try
	  {
		  
	    bdi.adoptPlan(new innerPlan());
		  bdi.waitForDelay(1000).get();
		  context=false;
		  System.out.println("mudei o contexto");
	  }
	  catch(Exception e)
	  {
	    e.printStackTrace();
	  }*/
		
		//bdi.adoptPlan("checkSaudacao");
		
		/*String saudacao= "ola";
		
		String resposta= (String)bdi.dispatchTopLevelGoal(new Saudar(saudacao)).get();
		System.out.println("resposta: " + resposta);*/
		
		saudacaoi = "ola";
		saudacaoi = "cenas";
		System.out.println("resposta: " + saudacaoi);
	}
	
	
	@Plan(trigger=@Trigger(factaddeds="saudacoesAgente"))
	public void checkSaudacao(String saudacao)
	{
		/*for(int i = 0; i< saudacoesAgente.size(); i++)
		{
			if(saudacoesAgente.containsValue(saudacao))
			{
			System.out.println("ja existe a saudacao");
			}
			else{
				System.out.println("saudacao nova!");
			}
		}*/
		
		if(saudacoesAgente.contains(saudacao))
		{
		System.out.println("existe a saudacao saudacao: !!");
		}
		else
		{
			System.out.println("saudacao nova !!");
		}
			
		
		
	  
	}
	
	@Plan(trigger=@Trigger(goals=Saudar.class))
	protected String saudar(String saudacaoInicial)
	{
	  
	  /*for(int i=0; i< saudacoesAgente.size(); i++)
	  {
		  if(saudacoesAgente.get(i)==saudacaoInicial)
		  {
			  return saudacoesAgente.get(i);
		  }
	  }*/
	  System.out.println("plano 1");
	  throw new PlanFailureException();
	  
	 
	}
	
	@Plan(trigger=@Trigger(goals=Saudar.class))
	protected String saudar2 (String saudacaoInicial)
	{
		System.out.println("plano 2");
	  for(int i=0; i< saudacoesAgente.size(); i++)
	  {
		  if(saudacoesAgente.get(i)==saudacaoInicial)
		  {
			  return saudacoesAgente.get(i);
		  }
	  }
	  
	  throw new PlanFailureException();
	  
	 
	}
	
	
	@Goal
	public class Saudar
	{
	  
		
		
	@GoalParameter
	protected String saudacaoi;
	
	@GoalResult
	protected String saudacaof;

	@GoalCreationCondition(beliefs="saudacaoi")
	  public Saudar(String saudacaoi)
	  {
	    this.saudacaoi = saudacaoi;
	  }
	  
	  
	  
	}
	
	@Plan public void dizerbomdia()
	{
		System.out.println("bom dia!");
		System.out.println(saudacoesAgente.get(0) + " outra vez");
	}
	
	@Plan
	public class innerPlan {
		
		
		protected Vector<String> saudacoes;
		
		@PlanAPI
		protected IPlan plan;

		@PlanContextCondition(beliefs="context")
		public boolean checkCondition()
		{
		  return context;
		}
		
		public innerPlan()
		{
			//inicializar variaveis da classe
			
			this.saudacoes= new Vector<String>();
			
			saudacoes.addElement("ola");
			saudacoes.addElement("adeus");
		}
		
		@PlanBody
		public String fala()
		{
			String temp = saudacoes.get(1);
			
			
			System.out.println("digo: " + temp);
			plan.waitFor(10000).get();
			
			System.out.println("cansei de esperar");
			//throw new PlanFailureException();
			return temp;
		}
		
		@PlanPassed
		public void passed()
		{
		  System.out.println("Plan finished successfully.");
		}
		  
		@PlanAborted
		public void aborted()
		{
		  System.out.println("Plan aborted.");
		}
		  
		@PlanFailed
		public void failed(Exception e)
		{
		  System.out.println("Plan failed: "+e);
		}

	}

}
