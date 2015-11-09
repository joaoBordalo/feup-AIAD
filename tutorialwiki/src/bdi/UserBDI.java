package bdi;

import jadex.bdiv3.BDIAgent;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.search.SServiceProvider;
import jadex.commons.future.DefaultResultListener;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import services.IMathsService;
 
@Agent
public class UserBDI {
	@Agent
	protected BDIAgent agent;
 
	private int n = 0;
 
	public UserBDI() {
		readValue();
	}
 
	private void readValue() {
		System.out.println("Insert positive value: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			n = Integer.parseInt(br.readLine());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
 
	@AgentBody
	public void body() {
		IMathsService ms = SServiceProvider.getService(agent.getServiceProvider(), IMathsService.class, RequiredServiceInfo.SCOPE_PLATFORM).get();
		ms.factorial(n)
		.addResultListener(new DefaultResultListener<Integer>() {
			public void resultAvailable(Integer f) {
				System.out.println("And the factorial is " + f);
			}
		});
		System.out.println("Doing something else...");
	}
 
}