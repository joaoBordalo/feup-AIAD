package bdi;

import services.*;
import jadex.bridge.service.annotation.Service;
import jadex.commons.future.Future;
import jadex.commons.future.IFuture;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.ProvidedService;
import jadex.micro.annotation.ProvidedServices;
 
@Agent
@Service
@ProvidedServices(@ProvidedService(type=IMathsService.class))
public class MathsBDI implements IMathsService {
 
	@Override
	public IFuture<Integer> factorial(int n) {
		int i=0, t=1;
		while(i<n) {
			i++;
			t *= i;
		}
		return new Future<Integer>(t);
	}
 
}
