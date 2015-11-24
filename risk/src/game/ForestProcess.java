package game;
import jadex.bridge.service.types.clock.IClockService;
import jadex.commons.SimplePropertyObject;
import jadex.extension.envsupport.environment.IEnvironmentSpace;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.environment.ISpaceProcess;
import jadex.extension.envsupport.environment.ISpaceProcess;
import jadex.extension.envsupport.environment.SpaceObject;
import jadex.extension.envsupport.environment.space2d.Space2D;
import jadex.extension.envsupport.evaluation.SpaceObjectSource;
import jadex.extension.envsupport.math.IVector2;
import jadex.extension.envsupport.math.Vector2Double;
import jadex.extension.envsupport.math.Vector2Int;
import jadex.extension.envsupport.observer.graphics.drawable3d.Object3d;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import javax.print.attribute.standard.MediaSize.NA;

import game.*;


public class ForestProcess extends SimplePropertyObject implements ISpaceProcess {

    @Override
    public void start(IClockService arg0, IEnvironmentSpace arg1) {

        Space2D space = (Space2D)arg1;

        int spaceHeight = space.getAreaSize().getXAsInteger();
        int spaceWidth = space.getAreaSize().getYAsInteger();

        int actualIndex=0;
        
        //Random r = new Random();

        HashMap<String, Integer> nTerritories = new HashMap<String, Integer>(6);
        
        nTerritories.put("NorthAmerica",9 );
        nTerritories.put("SouthAmerica",4 );
        nTerritories.put("Europe", 7 );
        nTerritories.put("Africa", 6);
        nTerritories.put("Asia", 12);
        nTerritories.put("Australia", 4);
        
       Vector<String> NANames = new Vector<String>();
       Vector<IVector2> NABoardCoords = new Vector<IVector2>();
       Vector<IVector2> NASizes = new Vector<IVector2>();
       
       Vector<String> SAnames = new Vector<String>();
       Vector<IVector2> SABoardCoords = new Vector<IVector2>();
       Vector<IVector2> SASizes = new Vector<IVector2>();
       
       Vector<String> ERNames = new Vector<String>();
       Vector<IVector2> ERBoardCoords = new Vector<IVector2>();
       Vector<IVector2> ERSizes = new Vector<IVector2>();
       
       Vector<String> AFNames = new Vector<String>();
       Vector<IVector2> AFBoardCoords = new Vector<IVector2>();
       Vector<IVector2> AFSizes = new Vector<IVector2>();
       
       Vector<String> ASNames = new Vector<String>();
       Vector<IVector2> ASBoardCoords = new Vector<IVector2>();
       Vector<IVector2> ASSizes = new Vector<IVector2>();
       
       
       Vector<String> AUNames = new Vector<String>();
       Vector<IVector2> AUBoardCoords = new Vector<IVector2>();
       Vector<IVector2> AUSizes = new Vector<IVector2>();
       
       
       NANames.addElement("ALASKA");
       NABoardCoords.addElement(new Vector2Double(3.5, 3.0));
       NASizes.addElement(new Vector2Double(2,2));
       
       NANames.addElement("ALBERTA");
       NABoardCoords.addElement(new Vector2Double(6.50, 5.25));
       NASizes.addElement(new Vector2Double(2,2));
       
       NANames.addElement("CENTRALAMERICA");
       NABoardCoords.addElement(new Vector2Double(6.25, 11.5));
       NASizes.addElement(new Vector2Double(2,2));
       
       NANames.addElement("EASTERNUNITEDSTATES");
       NABoardCoords.addElement(new Vector2Double(10, 8.5));
       NASizes.addElement(new Vector2Double(2,2));
       
       NANames.addElement("GREENLAND");
       NABoardCoords.addElement(new Vector2Double(20.75, 1.50));
       NASizes.addElement(new Vector2Double(2,2));
       
       NANames.addElement("NORTWESTTERRITORY");
       NABoardCoords.addElement(new Vector2Double(8,3));
       NASizes.addElement(new Vector2Double(2,2));
       
       NANames.addElement("ONTARIO");
       NABoardCoords.addElement(new Vector2Double(11,5));
       NASizes.addElement(new Vector2Double(2,2));
       
       NANames.addElement("QUEBEC");
       NABoardCoords.addElement(new Vector2Double(14.75, 5));
       NASizes.addElement(new Vector2Double(2,2));
       
       NANames.addElement("WESTERNUNITEDSTATES");
       NABoardCoords.addElement(new Vector2Double(5.75, 8));
       NASizes.addElement(new Vector2Double(2,2));
       
       
       
       
       SAnames.addElement("ARGENTINA");
       SAnames.addElement("BRAZIL");
       SAnames.addElement("PERU");
       SAnames.addElement("VENEZUELA");
       
       ERNames.addElement("GREATBRITAIN");
       ERNames.addElement("ICELAND");
       ERNames.addElement("NORTHERNEUROPE");
       ERNames.addElement("SCANDINAVIA");
       ERNames.addElement("SOUTHERNEUROPE");
       ERNames.addElement("UKRAINE");
       ERNames.addElement("WESTERNEUROPE");
       
       AFNames.addElement("CONGO");
       AFNames.addElement("EASTAFRICA");
       AFNames.addElement("EGYPT");
       AFNames.addElement("MADAGASCAR");
       AFNames.addElement("NORTHAFRICA");
       AFNames.addElement("SOUTHAFRICA");
       
       ASNames.addElement("AFGHANISTAN");
       ASNames.addElement("CHINA");
       ASNames.addElement("INDIA");
       ASNames.addElement("IRKUTSK");
       ASNames.addElement("JAPAN");
       ASNames.addElement("KAMCHATKA");
       ASNames.addElement("MIDDLEEAST");
       ASNames.addElement("MONGOLIA");
       ASNames.addElement("SLAM");
       ASNames.addElement("SIBERIA");
       ASNames.addElement("URAL");
       ASNames.addElement("YAKUTSK");
       
       AUNames.addElement("EASTERNAUSTRALIA");
       AUNames.addElement("INDONESIA");
       AUNames.addElement("NEWGUINEA");
       AUNames.addElement("WESTERNAUSTRALIA");
       
       Vector<Territory> gameBoard= new Vector<Territory>();
       
       for(int i=0; i<nTerritories.get("NorthAmerica"); i++)
       {
    	   gameBoard.add(new Territory(actualIndex, NANames.get(i), "NorthAmerica", NASizes.get(i), NABoardCoords.get(i)));
    	   
    	   actualIndex++;
       }
       
       for(int i=0; i<nTerritories.get("SouthAmerica"); i++)
       {
       
       }
       
       for(int i=0; i<nTerritories.get("Europe"); i++)
       {
       
       }
       
       for(int i=0; i<nTerritories.get("Asia"); i++)
       {
       
       }
       
       for(int i=0; i<nTerritories.get("Africa"); i++)
       {
       
       }
       
       for(int i=0; i<nTerritories.get("Australia"); i++)
       {
       
       }
       
       for(int i = 0; i<gameBoard.size(); i++)
       {
       Map properties = new HashMap();
       properties.put("position", gameBoard.get(i).getBoardCoord());
       properties.put("type", gameBoard.get(i).getShapeType());
       properties.put("size", gameBoard.get(i).getSize());
       properties.put("army", gameBoard.get(i).getArmy());
       
       space.createSpaceObject("Territory", properties, null);
       }
       
       
        
        
        
        
       
        
        
        
        
        /*for(int j = 0 ; j < 7; j++)
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
        }*/

    }

    @Override
    public void shutdown(IEnvironmentSpace iEnvironmentSpace) {

    }

    @Override
    public void execute(IClockService iClockService, IEnvironmentSpace iEnvironmentSpace) {

    }


}
