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


public class RiskProcess extends SimplePropertyObject implements ISpaceProcess {

    @Override
    public void start(IClockService arg0, IEnvironmentSpace arg1) {

        Space2D space = (Space2D)arg1;

        int spaceHeight = space.getAreaSize().getXAsInteger();
        int spaceWidth = space.getAreaSize().getYAsInteger();

        int territoryIndex=0;
        
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
       
       Vector<String> SANames = new Vector<String>();
       Vector<IVector2> SABoardCoords = new Vector<IVector2>();
       Vector<IVector2> SASizes = new Vector<IVector2>();
       
       Vector<String> EUNames = new Vector<String>();
       Vector<IVector2> EUBoardCoords = new Vector<IVector2>();
       Vector<IVector2> EUSizes = new Vector<IVector2>();
       
       Vector<String> AFNames = new Vector<String>();
       Vector<IVector2> AFBoardCoords = new Vector<IVector2>();
       Vector<IVector2> AFSizes = new Vector<IVector2>();
       
       Vector<String> ASNames = new Vector<String>();
       Vector<IVector2> ASBoardCoords = new Vector<IVector2>();
       Vector<IVector2> ASSizes = new Vector<IVector2>();
       
       
       Vector<String> AUNames = new Vector<String>();
       Vector<IVector2> AUBoardCoords = new Vector<IVector2>();
       Vector<IVector2> AUSizes = new Vector<IVector2>();
       
       
       
       
       //DEFS NORTH AMERICA
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
       NABoardCoords.addElement(new Vector2Double(14.75, 5.25));
       NASizes.addElement(new Vector2Double(2,2));
       
       NANames.addElement("WESTERNUNITEDSTATES");
       NABoardCoords.addElement(new Vector2Double(5.75, 8));
       NASizes.addElement(new Vector2Double(2,2));
       
       
       
       //DEFS SOUTH AMERCIA
       SANames.addElement("ARGENTINA");
       SABoardCoords.addElement(new Vector2Double(14,23.75));
       SASizes.addElement(new Vector2Double(2,2));
       
       SANames.addElement("BRAZIL");
       SABoardCoords.addElement(new Vector2Double(16,19));
       SASizes.addElement(new Vector2Double(2,2));
       
       SANames.addElement("PERU");
       SABoardCoords.addElement(new Vector2Double(13.4,20.5));
       SASizes.addElement(new Vector2Double(1.5,1.5));
       
       SANames.addElement("VENEZUELA");
       SABoardCoords.addElement(new Vector2Double(12.5,15.5));
       SASizes.addElement(new Vector2Double(2,2));
       
       
       
       //DEFS EUROPE
       EUNames.addElement("GREATBRITAIN");
       EUBoardCoords.addElement(new Vector2Double(26,5.25));
       EUSizes.addElement(new Vector2Double(1,1));
             
       EUNames.addElement("ICELAND");
       EUBoardCoords.addElement(new Vector2Double(24.25,3));
       EUSizes.addElement(new Vector2Double(0.75,0.75));

       EUNames.addElement("NORTHERNEUROPE");
       EUBoardCoords.addElement(new Vector2Double(29,6));
       EUSizes.addElement(new Vector2Double(1,1));

       EUNames.addElement("SCANDINAVIA");
       EUBoardCoords.addElement(new Vector2Double(29.25,3.5));
       EUSizes.addElement(new Vector2Double(1,1));

       EUNames.addElement("SOUTHERNEUROPE");
       EUBoardCoords.addElement(new Vector2Double(31,7.5));
       EUSizes.addElement(new Vector2Double(1,1));

       EUNames.addElement("UKRAINE");
       EUBoardCoords.addElement(new Vector2Double(33,5));
       EUSizes.addElement(new Vector2Double(2,2));

       EUNames.addElement("WESTERNEUROPE");
       EUBoardCoords.addElement(new Vector2Double(27,7.5));
       EUSizes.addElement(new Vector2Double(1,1));

       
       //DEFS AFRICA
       
       AFNames.addElement("CONGO");
       AFBoardCoords.addElement(new Vector2Double(31,17));
       AFSizes.addElement(new Vector2Double(2,2));
       
       AFNames.addElement("EASTAFRICA");
       AFBoardCoords.addElement(new Vector2Double(34.75,15.25));
       AFSizes.addElement(new Vector2Double(2,2));
       
       AFNames.addElement("EGYPT");
       AFBoardCoords.addElement(new Vector2Double(31,11));
       AFSizes.addElement(new Vector2Double(1.75,1.75));
       
       AFNames.addElement("MADAGASCAR");
       AFBoardCoords.addElement(new Vector2Double(36,21));
       AFSizes.addElement(new Vector2Double(1,1));
       
       AFNames.addElement("NORTHAFRICA");
       AFBoardCoords.addElement(new Vector2Double(26.5,13));
       AFSizes.addElement(new Vector2Double(2,2));
       
       AFNames.addElement("SOUTHAFRICA");
       AFBoardCoords.addElement(new Vector2Double(31,21));
       AFSizes.addElement(new Vector2Double(2,2));
       
       //DEFS ASIA
       ASNames.addElement("AFGHANISTAN");
       ASBoardCoords.addElement(new Vector2Double(38.8,7));
       ASSizes.addElement(new Vector2Double(2,2));
       
       ASNames.addElement("CHINA");
       ASBoardCoords.addElement(new Vector2Double(46.6,9.8));
       ASSizes.addElement(new Vector2Double(1.5,1.5));
       
       ASNames.addElement("INDIA");
       ASBoardCoords.addElement(new Vector2Double(42,11.8));
       ASSizes.addElement(new Vector2Double(1.5,1.5));
       
       ASNames.addElement("IRKUTSK");
       ASBoardCoords.addElement(new Vector2Double(46.7,5.3));
       ASSizes.addElement(new Vector2Double(2,2));
       
       ASNames.addElement("JAPAN");
       ASBoardCoords.addElement(new Vector2Double(54,9));
       ASSizes.addElement(new Vector2Double(1,1));
       
       ASNames.addElement("KAMCHATKA");
       ASBoardCoords.addElement(new Vector2Double(52,3.2));
       ASSizes.addElement(new Vector2Double(1,1));
       
       ASNames.addElement("MIDDLEEAST");
       ASBoardCoords.addElement(new Vector2Double(35,9.5));
       ASSizes.addElement(new Vector2Double(1,1));
       
       ASNames.addElement("MONGOLIA");
       ASBoardCoords.addElement(new Vector2Double(46.8,7.25));
       ASSizes.addElement(new Vector2Double(1,1));
       
       ASNames.addElement("SLAM");
       ASBoardCoords.addElement(new Vector2Double(47.4,13.2));
       ASSizes.addElement(new Vector2Double(1,1));
       
       ASNames.addElement("SIBERIA");
       ASBoardCoords.addElement(new Vector2Double(41.9,3.4));
       ASSizes.addElement(new Vector2Double(1,1));
       
       ASNames.addElement("URAL");
       ASBoardCoords.addElement(new Vector2Double(38.6,4));
       ASSizes.addElement(new Vector2Double(1.5,1.5));
       
       ASNames.addElement("YAKUTSK");
       ASBoardCoords.addElement(new Vector2Double(46.5,3));
       ASSizes.addElement(new Vector2Double(1,1));
       
       //DEFS AUSTRALIA
       AUNames.addElement("EASTERNAUSTRALIA");
       AUBoardCoords.addElement(new Vector2Double(55.5,22.5));
       AUSizes.addElement(new Vector2Double(2,2));
       
       AUNames.addElement("INDONESIA");
       AUBoardCoords.addElement(new Vector2Double(49.8,17));
       AUSizes.addElement(new Vector2Double(1,1));
       
       AUNames.addElement("NEWGUINEA");
       AUBoardCoords.addElement(new Vector2Double(55.5,18));
       AUSizes.addElement(new Vector2Double(1,1));
       
       AUNames.addElement("WESTERNAUSTRALIA");
       AUBoardCoords.addElement(new Vector2Double(50.8,22.5));
       AUSizes.addElement(new Vector2Double(2,2));
       
       
       Vector<Territory> gameBoard= new Vector<Territory>();
       
       for(int i=0; i<nTerritories.get("NorthAmerica"); i++)
       {
    	   gameBoard.add(new Territory(territoryIndex, NANames.get(i), "NorthAmerica", NASizes.get(i), NABoardCoords.get(i), null));
    	   
    	   territoryIndex++;
       }
       
       territoryIndex=0;
       
       
       for(int i=0; i<nTerritories.get("SouthAmerica"); i++)
       {
       
    	   gameBoard.add(new Territory(territoryIndex, SANames.get(i), "SouthAmerica", SASizes.get(i), SABoardCoords.get(i), null));
    	   
    	   territoryIndex++;
       }
       
       territoryIndex=0;
       
       for(int i=0; i<nTerritories.get("Europe"); i++)
       {
    	   gameBoard.add(new Territory(territoryIndex, EUNames.get(i), "Europe", EUSizes.get(i), EUBoardCoords.get(i), null));
    	   
    	   territoryIndex++;
       }
       
       territoryIndex=0;
       
       for(int i=0; i<nTerritories.get("Africa"); i++)
       {
    	   gameBoard.add(new Territory(territoryIndex, AFNames.get(i), "Africa", AFSizes.get(i), AFBoardCoords.get(i), null));
    	   
    	   territoryIndex++;
       }
       
       territoryIndex=0;
       
       for(int i=0; i<nTerritories.get("Asia"); i++)
       {
    	   gameBoard.add(new Territory(territoryIndex, ASNames.get(i), "Africa", ASSizes.get(i), ASBoardCoords.get(i), null));
    	   
    	   territoryIndex++;
    	   
       }
       
       territoryIndex=0;
       
       for(int i=0; i<nTerritories.get("Australia"); i++)
       {
    	   gameBoard.add(new Territory(territoryIndex, AUNames.get(i), "Australia", AUSizes.get(i), AUBoardCoords.get(i), null));
    	   
    	   territoryIndex++;
       }
       
       territoryIndex=0;
       for(int i = 0; i<gameBoard.size(); i++)
       {
       Map properties = new HashMap();
       properties.put("position", gameBoard.get(i).getBoardCoord());
       properties.put("type", gameBoard.get(i).getShapeType());
       properties.put("size", gameBoard.get(i).getSize());
       properties.put("army", gameBoard.get(i).getArmy());
       properties.put("index", gameBoard.get(i).getIndex());
       properties.put("territoryname", gameBoard.get(i).getTerritoryName());
       properties.put("continentname", gameBoard.get(i).getContinentName());
       
       space.createSpaceObject("Territory", properties, null);
       }
       
       
        
        
        
        
       
        
        
        
        
        /*for(int j = 0 ; j < 7; j++)
        {
	        for(int i = 0; i < 7; i++) 
	        {
	            Map properties = new HashMap();
	            properties.put("position", new Vector2Int(i, j));
	            properties.put("type", territoryIndex);
	            //properties.put("army", new Army(new Player(territoryIndex, "name", new Color(territoryIndex)),0).getArmySize());
	
	            space.createSpaceObject("Territory", properties, null);
	            territoryIndex++;
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
