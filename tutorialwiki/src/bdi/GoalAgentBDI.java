package bdi;

import goals.*;
import jadex.bdi.runtime.PlanFailureException;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Description;
 
@Agent
@Description("An agent with a goal.")
public class GoalAgentBDI {
 
	@Agent
	protected BDIAgent agent;
 
	@AgentBody
	public void body() {
		int result = (int) agent.dispatchTopLevelGoal(new AGoal("important goal")).get();
		System.out.println("Finished with " + result + "!");
	}
 
	@Plan(trigger=@Trigger(goals=AGoal.class))
	protected void basicPlan() {
		System.out.println("Executing basic plan.");
		//throw new PlanFailureException();
	}
	
	@Plan(trigger=@Trigger(goals=AGoal.class))
	protected void goalPlan(AGoal goal) {
		System.out.println("Executing goal plan for " + goal.p);
		goal.r = 1;
	}
 
	@Plan(trigger=@Trigger(goals=AGoal.class))
	protected void argumentPlan(String s) {
		System.out.println("Executing argument plan for " + s);
	}
 
	@Plan(trigger=@Trigger(goals=AGoal.class))
	protected int returnPlan() {
		System.out.println("Executing return plan");
		return 2;
	}
 
	@Plan(trigger=@Trigger(goals=AGoal.class))
	protected int signaturePlan(String s) {
		System.out.println("Executing signature plan for " + s);
		return 3;
	}
 
}
