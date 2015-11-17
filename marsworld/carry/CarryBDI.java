package jadex.bdiv3.examples.marsworld.carry;

import jadex.bdiv3.annotation.Body;
import jadex.bdiv3.annotation.Deliberation;
import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalDropCondition;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Plans;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.examples.marsworld.BaseBDI;
import jadex.bdiv3.examples.marsworld.movement.MovementCapability.WalkAround;
import jadex.bdiv3.examples.marsworld.sentry.ITargetAnnouncementService;
import jadex.bridge.service.annotation.Reference;
import jadex.bridge.service.annotation.Service;
import jadex.commons.future.IFuture;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.Implementation;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;
import jadex.micro.annotation.RequiredService;
import jadex.micro.annotation.RequiredServices;

/**
 * 
 */
@Agent
@Service
@ProvidedServices(@ProvidedService(type=ICarryService.class, implementation=@Implementation(expression="$pojoagent")))
@RequiredServices(@RequiredService(name="targetser", type=ITargetAnnouncementService.class, multiple=true))
@Plans({
	@Plan(trigger=@Trigger(goals=CarryBDI.CarryOre.class), body=@Body(CarryOrePlan.class)),
	@Plan(trigger=@Trigger(factaddeds="movecapa.mytargets"), body=@Body(InformNewTargetPlan.class))
})
public class CarryBDI extends BaseBDI implements ICarryService
{
	/**
	 * 
	 */
	@Goal(deliberation=@Deliberation(inhibits=WalkAround.class, cardinalityone=true))
	public class CarryOre
	{
		/** The target. */
		protected ISpaceObject target;

		/**
		 *  Create a new CarryOre. 
		 */
		public CarryOre(ISpaceObject target)
		{
			this.target = target;
		}
		
		/**
		 * 
		 */
		@GoalDropCondition(beliefs="movecapa.missionend")
		public boolean checkDrop()
		{
			return movecapa.isMissionend();
		}

		/**
		 *  Get the target.
		 *  @return The target.
		 */
		public ISpaceObject getTarget()
		{
			return target;
		}
		
	}
	
	/**
	 * 
	 */
	public IFuture<Void> doCarry(@Reference ISpaceObject target)
	{
		agent.dispatchTopLevelGoal(new CarryOre(target));
		return IFuture.DONE;
	}
}



