package services;

import jadex.commons.future.IFuture;

public interface IMathsService {
 
	public IFuture<Integer> factorial(int n);
 
}