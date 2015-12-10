package agents;

import jadex.base.Starter;
import jadex.bridge.IComponentIdentifier;
import jadex.bridge.IExternalAccess;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.search.SServiceProvider;
import jadex.bridge.service.types.cms.IComponentManagementService;
import jadex.commons.future.ThreadSuspendable;


////alterar as running  confis para esta class para iniciar n agentes

public class AgentFactory {
    public static void main(String[] args) {
        ThreadSuspendable sus = new ThreadSuspendable();

        /**
         * The interface for accessing components from the outside.
         */
        IExternalAccess pl = Starter.createPlatform(new String[0]).get(sus);

        /**
         * General interface for components that the container can execute.
         */
        IComponentManagementService cms = SServiceProvider.getService(pl.getServiceProvider(),
                IComponentManagementService.class, RequiredServiceInfo.SCOPE_PLATFORM).get(sus);

        for (int i = 0; i < 4; i++) {
            /**
             * Interface for component identifiers.
             */
            String classPath = "agents\\PlayerBDI.class";
            IComponentIdentifier hw = cms.createComponent(classPath, null).getFirstResult(sus);
            System.out.println("started: "+hw);
        }
    }
}