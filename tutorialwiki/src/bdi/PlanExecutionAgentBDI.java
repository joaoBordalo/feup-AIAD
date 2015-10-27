package bdi;

import plans.*;
import jadex.bdiv3.runtime.IPlan;
import jadex.bdiv3.BDIAgent;
import jadex.bdiv3.annotation.Body;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;
import jadex.bdiv3.annotation.Plans;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Description;
 
@Agent
@Description("An agent executing plans directly.")
@Plans(@Plan(body=@Body(SleepingPlan.class)))
public class PlanExecutionAgentBDI {
 
	@Agent
	protected BDIAgent agent;
 
	@AgentBody
	public void body() {
		agent.adoptPlan(new SleepingPlan(3));
		agent.adoptPlan("methodPlan");
		agent.adoptPlan(new InnerPlan());
	}
	
	@Plan
	public void methodPlan(IPlan plan) {
		System.out.println("Executing a method plan.");
		plan.waitFor(5000);
		System.out.println("Method plan done waiting");
	}
 
	@Plan
	public class InnerPlan {
 
		@PlanBody
		public void innerPlanBody() {
			System.out.println("Executing an inner class plan.");
		}
 
	}
 
	
	
 
}
