package plans;

import java.util.Vector;

import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;


@Plan
public class DummyPlan {
	
	
	protected Vector<String> saudacoes;
	
	public DummyPlan()
	{
		//inicializar variaveis da classe
		
		this.saudacoes= new Vector<String>();
		
		saudacoes.addElement("ola");
		saudacoes.addElement("adeus");
	}
	
	@PlanBody
	public void fala()
	{
		String temp = saudacoes.get(0);
		
		System.out.println("digo: " + temp);
	}

}
