package game;
import jadex.bridge.service.types.clock.IClockService;
import jadex.commons.SimplePropertyObject;
import jadex.extension.envsupport.environment.IEnvironmentSpace;
import jadex.extension.envsupport.environment.ISpaceProcess;
import jadex.extension.envsupport.environment.ISpaceProcess;
import jadex.extension.envsupport.environment.space2d.Space2D;
import jadex.extension.envsupport.math.Vector2Int;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import game.*;


public class ForestProcess extends SimplePropertyObject implements ISpaceProcess {

    @Override
    public void start(IClockService arg0, IEnvironmentSpace arg1) {

        Space2D space = (Space2D)arg1;

        int spaceHeight = space.getAreaSize().getXAsInteger();
        int spaceWidth = space.getAreaSize().getYAsInteger();

        int actualIndex=0;
        
        //Random r = new Random();

        
        for(int j = 0 ; j < 7; j++)
        {
	        for(int i = 0; i < 7; i++) 
	        {
	            Map properties = new HashMap();
	            properties.put("position", new Vector2Int(i, j));
	            properties.put("type", actualIndex);
	            //properties.put("army", new Army(new Player(actualIndex, "name", new Color(actualIndex)),0).getArmySize());
	
	            space.createSpaceObject("Territory", properties, null);
	            actualIndex++;
	        }
        }

    }

    @Override
    public void shutdown(IEnvironmentSpace iEnvironmentSpace) {

    }

    @Override
    public void execute(IClockService iClockService, IEnvironmentSpace iEnvironmentSpace) {

    }


}
