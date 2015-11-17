package jadex.bdiv3.examples.marsworld.carry;

import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanAPI;
import jadex.bdiv3.annotation.PlanBody;
import jadex.bdiv3.annotation.PlanCapability;
import jadex.bdiv3.annotation.PlanReason;
import jadex.bdiv3.examples.marsworld.sentry.ITargetAnnouncementService;
import jadex.bdiv3.runtime.IPlan;
import jadex.commons.future.IFuture;
import jadex.extension.envsupport.environment.ISpaceObject;

import java.util.Collection;

/**
 *  Inform the sentry agent about a new target.
 */
@Plan
public class InformNewTargetPlan 
{
	@PlanCapability
	protected CarryBDI carry;
	
	@PlanAPI
	protected IPlan rplan;
	
	@PlanReason
	protected ISpaceObject target;
	
	//-------- methods --------

	/**
	 *  The plan body.
	 */
	@PlanBody
	public void body()
	{
		try
		{
			IFuture<Collection<ITargetAnnouncementService>> fut = carry.getAgent().getServiceContainer().getRequiredServices("targetser");
			Collection<ITargetAnnouncementService> ansers = fut.get();
			
			for(ITargetAnnouncementService anser: ansers)
			{
				anser.announceNewTarget(target);
			}
		}
		catch(Exception e)
		{
			System.out.println("No target announcement services found");
		}
		
//		System.out.println("Informing sentries: "+getScope().getAgentName());
	}
}
