package goals;

import jadex.bdiv3.annotation.Goal;
import jadex.bdiv3.annotation.GoalParameter;
import jadex.bdiv3.annotation.GoalResult;


@Goal
public class AGoal {

	
	@GoalParameter
	public String p;
 
	@GoalResult
	public int r;
 
	public AGoal(String p) {
		this.p = p;
	}
}
