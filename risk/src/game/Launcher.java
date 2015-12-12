package game;

import java.util.HashMap;
import java.util.Map;

import jadex.base.Starter;
import jadex.bridge.IComponentIdentifier;
import jadex.bridge.IExternalAccess;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.search.SServiceProvider;
import jadex.bridge.service.types.cms.CreationInfo;
import jadex.bridge.service.types.cms.IComponentManagementService;
import jadex.commons.future.ThreadSuspendable;

public class Launcher {

	public static void main(String[] args) {

		ThreadSuspendable sus = new ThreadSuspendable();
		 
	    /**
	    * The interface for accessing components from the outside.
	    */
		String[] defargs = new String[]
				{
				  "-gui", "false",
				  "-welcome", "false",
				  "-cli", "false",
				  "-printpass", "false"
				};
		
		
		String[] newargs = new String[defargs.length+args.length];
		System.arraycopy(defargs, 0, newargs, 0, defargs.length);
		System.arraycopy(args, 0, newargs, defargs.length, args.length);
		
	    IExternalAccess pl = Starter.createPlatform(newargs).get(sus);
	 
	    /**
	    * General interface for components that the container can execute.
	    */
	    IComponentManagementService cms = SServiceProvider.getService(pl.getServiceProvider(),
	    IComponentManagementService.class, RequiredServiceInfo.SCOPE_PLATFORM).get(sus);
	 




	      /**
	      * Interface for component identifiers.
	      */
	      String classPath = "Risk.application.xml";
	      
	      Map<String, Object> gameArgs = new HashMap<String, Object>();
	      gameArgs.put("numberofplayers", new Integer(2));
	      CreationInfo infoArgs = new CreationInfo(gameArgs);
	      IComponentIdentifier hw = cms.createComponent("riskgame", classPath, infoArgs).getFirstResult(sus);

	      
	    
	  }

	}


