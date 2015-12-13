package agents;


import perceptions.ReinforceArmyPerception;
import jadex.bridge.modelinfo.IExtensionInstance;
import jadex.commons.future.DefaultResultListener;
import jadex.commons.future.IFuture;
import jadex.extension.envsupport.environment.space2d.Space2D;
import jadex.micro.MicroAgent;
import jadex.micro.annotation.*;;


@Agent
@Description("A Reactive Agent")
public class PlayerReactiveAgent extends PlayerAgentBase {
	
	@Agent
	protected MicroAgent reactAgent;
	protected Space2D space;
	
	public PlayerReactiveAgent()
	{
		super();
		
	}
	
@AgentBody
public void body()
{
	
	IFuture<IExtensionInstance> fut = reactAgent.getParentAccess().getExtension("2dspace");
	fut.addResultListener(new DefaultResultListener<IExtensionInstance>() {
		public void resultAvailable(IExtensionInstance cs) {
			space = (Space2D) cs;
		
		}
	});
	
	initPerceptions(space);
	System.out.println("João TESTE INIT!!!");
	System.out.println("number Perceptions= "+ perceptions.size());
	ReinforceArmyPerception re= (ReinforceArmyPerception) perceptions.get(0);
	
	System.out.println("number my territories= "+re.getAllocations().size());
	
}

	
}

