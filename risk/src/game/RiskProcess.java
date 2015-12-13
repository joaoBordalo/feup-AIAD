package game;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import javassist.expr.NewArray;
import agents.PlayerAgentBase;
import jadex.bdi.testcases.misc.GetExternalAccessPlan;
import jadex.bridge.IComponentIdentifier;
import jadex.bridge.modelinfo.IExtensionInstance;
import jadex.bridge.service.types.clock.IClockService;
import jadex.bridge.service.types.cms.IComponentDescription;
import jadex.commons.SimplePropertyObject;
import jadex.commons.future.DefaultResultListener;
import jadex.commons.future.IFuture;
import jadex.extension.envsupport.environment.IEnvironmentSpace;
import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.environment.ISpaceProcess;
import jadex.extension.envsupport.environment.space2d.Space2D;
import jadex.extension.envsupport.math.IVector2;
import jadex.extension.envsupport.math.Vector2Double;


public class RiskProcess extends SimplePropertyObject implements ISpaceProcess {

	
	public class Adj {
		Integer contIndex;
		Integer countryIndex;
		
		public Adj(Integer contI, Integer countI)
		{
			contIndex=contI;
			countryIndex= countI;
		}

		public Integer getContIndex() {
			return contIndex;
		}

		public void setContIndex(Integer contIndex) {
			this.contIndex = contIndex;
		}

		public Integer getCountryIndex() {
			return countryIndex;
		}

		public void setCountryIndex(Integer countryIndex) {
			this.countryIndex = countryIndex;
		}
		
		
	};
	
	int playersNumber;
	
	Vector<Player> players;
	Vector<Territory> gameBoard= new Vector<Territory>();
	
	
    public int getPlayersNumber() {
		return playersNumber;
	}

	public void setPlayersNumber(int playersNumber) {
		this.playersNumber = playersNumber;
	}
	
	 public Vector<Player> getPlayers() {
			return players;
		}

		public void setPlayers(Vector<Player> players) {
			this.players = players;
		}
		private HashMap<Player,PlayerAgentBase> playerRefToAgent;
		
		IComponentIdentifier[] listaret = new IComponentIdentifier[]{};
		
	@Override
    public void start(IClockService arg0, IEnvironmentSpace arg1) {

        Space2D space = (Space2D)arg1;
        
        this.players= new Vector<Player>();
        this.playerRefToAgent = new HashMap<Player,PlayerAgentBase>();
        this.playersNumber = (int) space.getProperty("numberofplayers");
        		
        players.addElement(new Player(0, "zero", Color.cyan, playersNumber));
        players.addElement(new Player(1, "um", Color.gray, playersNumber));
        
     
        
        int spaceHeight = space.getAreaSize().getXAsInteger();
        int spaceWidth = space.getAreaSize().getYAsInteger();

        int territoryIndex=0;
        int continentIndex=0;
        
        Random r = new Random();

        HashMap<String, Integer> nTerritories = new HashMap<String, Integer>(6);
        Vector<Vector<Vector<Adj>>> adjTemp= new Vector<Vector<Vector<Adj>>>();
        
        nTerritories.put("NorthAmerica",9 );
        nTerritories.put("SouthAmerica",4 );
        nTerritories.put("Europe", 7 );
        nTerritories.put("Africa", 6);
        nTerritories.put("Asia", 12);
        nTerritories.put("Australia", 4);
        
       Vector<String> NANames = new Vector<String>();
       Vector<IVector2> NABoardCoords = new Vector<IVector2>();
       Vector<IVector2> NASizes = new Vector<IVector2>();
       Vector<Vector<Adj>> NAAdjs = new Vector<Vector<Adj>>();
       
       Vector<Adj> countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(0, 1));
       countryAdjsTemp.add(new Adj(0, 5));
       countryAdjsTemp.add(new Adj(4, 6));
       NAAdjs.add(0, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
      
       countryAdjsTemp.add(new Adj(0, 0));
       countryAdjsTemp.add(new Adj(0, 5));
       countryAdjsTemp.add(new Adj(0, 8));
       NAAdjs.add(1, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(0, 2));
       countryAdjsTemp.add(new Adj(0, 3));
       countryAdjsTemp.add(new Adj(0, 8));
       countryAdjsTemp.add(new Adj(1, 3));
       NAAdjs.add(2, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();

       
       countryAdjsTemp.add( new Adj(0, 2));
       countryAdjsTemp.add( new Adj(0, 6));
       countryAdjsTemp.add( new Adj(0, 7));
       countryAdjsTemp.add( new Adj(0, 8));
       NAAdjs.add(3, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add( new Adj(0, 7));
       countryAdjsTemp.add( new Adj(0, 6));
       countryAdjsTemp.add( new Adj(0, 5));
       countryAdjsTemp.add( new Adj(2, 1));
       NAAdjs.add(4, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add( new Adj(0, 0));
       countryAdjsTemp.add( new Adj(0, 1));
       countryAdjsTemp.add(new Adj(0, 6));
       countryAdjsTemp.add(new Adj(0, 4));
       NAAdjs.add(5, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(0, 5));
       countryAdjsTemp.add(new Adj(0, 1));
       countryAdjsTemp.add(new Adj(0, 8));
       countryAdjsTemp.add(new Adj(0, 3));
       countryAdjsTemp.add(new Adj(0, 7));
       countryAdjsTemp.add(new Adj(0, 4));
       NAAdjs.add(6, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(0, 3));
       countryAdjsTemp.add(new Adj(0, 6));
       countryAdjsTemp.add(new Adj(0, 4));
       NAAdjs.add(7, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(0, 1));
       countryAdjsTemp.add(new Adj(0, 2));
       countryAdjsTemp.add(new Adj(0, 3));
       countryAdjsTemp.add(new Adj(0, 6));
       NAAdjs.add(8, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       adjTemp.add(NAAdjs);
     
       
      
       
       
       
       
       Vector<String> SANames = new Vector<String>();
       Vector<IVector2> SABoardCoords = new Vector<IVector2>();
       Vector<IVector2> SASizes = new Vector<IVector2>();
       Vector<Vector<Adj>> SAAdjs = new Vector<Vector<Adj>>();
       
       countryAdjsTemp.add(new Adj(1, 1));
       countryAdjsTemp.add(new Adj(1, 2));
       SAAdjs.add(0, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();

       countryAdjsTemp.add(new Adj(1, 0));
       countryAdjsTemp.add(new Adj(1, 2));
       countryAdjsTemp.add(new Adj(1, 3));
       countryAdjsTemp.add(new Adj(3, 4));
       SAAdjs.add(1, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(1, 0));
       countryAdjsTemp.add(new Adj(1, 1));
       countryAdjsTemp.add(new Adj(1, 3));
       SAAdjs.add(2, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(1, 1));
       countryAdjsTemp.add(new Adj(1, 2));
       countryAdjsTemp.add(new Adj(0, 2));
       SAAdjs.add(3, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       adjTemp.add(SAAdjs);
       
      
       
       
       Vector<String> EUNames = new Vector<String>();
       Vector<IVector2> EUBoardCoords = new Vector<IVector2>();
       Vector<IVector2> EUSizes = new Vector<IVector2>();
       Vector<Vector<Adj>> EUAdjs = new Vector<Vector<Adj>>();
       
       countryAdjsTemp.add(new Adj(2, 1));
       countryAdjsTemp.add(new Adj(2, 2));
       countryAdjsTemp.add(new Adj(2, 3));
       countryAdjsTemp.add(new Adj(2, 6));
       EUAdjs.add(0, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(2, 0));
       countryAdjsTemp.add(new Adj(2, 3));
       countryAdjsTemp.add(new Adj(0, 4));
       EUAdjs.add(1, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(2, 6));
       countryAdjsTemp.add(new Adj(2, 3));
       countryAdjsTemp.add(new Adj(2, 0));
       countryAdjsTemp.add(new Adj(2, 4));
       countryAdjsTemp.add(new Adj(2, 5));
       EUAdjs.add(2, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(2, 0));
       countryAdjsTemp.add(new Adj(2, 1));
       countryAdjsTemp.add(new Adj(2, 2));
       countryAdjsTemp.add(new Adj(2, 5));
       EUAdjs.add(3, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(2, 6));
       countryAdjsTemp.add(new Adj(2, 2));
       countryAdjsTemp.add(new Adj(2, 5));
       countryAdjsTemp.add(new Adj(3, 2));
       countryAdjsTemp.add(new Adj(3, 4));
       countryAdjsTemp.add(new Adj(4, 6));
       EUAdjs.add(4, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(2, 3));
       countryAdjsTemp.add(new Adj(2, 2));
       countryAdjsTemp.add(new Adj(2, 4));
       countryAdjsTemp.add(new Adj(4, 10));
       countryAdjsTemp.add(new Adj(4, 0));
       countryAdjsTemp.add(new Adj(4, 6));
       EUAdjs.add(5, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(2, 0));
       countryAdjsTemp.add(new Adj(2, 2));
       countryAdjsTemp.add(new Adj(2, 4));
       countryAdjsTemp.add(new Adj(3, 4));
       EUAdjs.add(6, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       adjTemp.add(EUAdjs);
       
       
       Vector<String> AFNames = new Vector<String>();
       Vector<IVector2> AFBoardCoords = new Vector<IVector2>();
       Vector<IVector2> AFSizes = new Vector<IVector2>();
       Vector<Vector<Adj>> AFAdjs = new Vector<Vector<Adj>>();
       
       countryAdjsTemp.add(new Adj(3, 1));
       countryAdjsTemp.add(new Adj(3, 4));
       countryAdjsTemp.add(new Adj(3, 5));
       AFAdjs.add(0, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(3, 2));
       countryAdjsTemp.add(new Adj(3, 4));
       countryAdjsTemp.add(new Adj(3, 0));
       countryAdjsTemp.add(new Adj(3, 3));
       countryAdjsTemp.add(new Adj(3, 5));
       countryAdjsTemp.add(new Adj(4, 6));
       AFAdjs.add(1, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(3, 1));
       countryAdjsTemp.add(new Adj(3, 4));
       countryAdjsTemp.add(new Adj(2, 4));
       countryAdjsTemp.add(new Adj(4, 6));
       AFAdjs.add(2, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(3, 5));
       countryAdjsTemp.add(new Adj(3, 1));
       AFAdjs.add(3, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(3, 0));
       countryAdjsTemp.add(new Adj(3, 2));
       countryAdjsTemp.add(new Adj(3, 2));
       countryAdjsTemp.add(new Adj(1, 1));
       countryAdjsTemp.add(new Adj(2, 6));
       countryAdjsTemp.add(new Adj(2, 4));
       AFAdjs.add(4, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(3, 0));
       countryAdjsTemp.add(new Adj(3, 1));
       countryAdjsTemp.add(new Adj(3, 3));
       AFAdjs.add(5, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       adjTemp.add(AFAdjs);
       
       
       Vector<String> ASNames = new Vector<String>();
       Vector<IVector2> ASBoardCoords = new Vector<IVector2>();
       Vector<IVector2> ASSizes = new Vector<IVector2>();
       Vector<Vector<Adj>> ASAdjs = new Vector<Vector<Adj>>();
       
       countryAdjsTemp.add(new Adj(4, 6));
       countryAdjsTemp.add(new Adj(4, 10));
       countryAdjsTemp.add(new Adj(4, 1));
       countryAdjsTemp.add(new Adj(4, 2));
       countryAdjsTemp.add(new Adj(2, 5));
       ASAdjs.add(0, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(4, 0));
       countryAdjsTemp.add(new Adj(4, 2));
       countryAdjsTemp.add(new Adj(4, 8));
       countryAdjsTemp.add(new Adj(4, 7));
       countryAdjsTemp.add(new Adj(4, 9));
       countryAdjsTemp.add(new Adj(4, 10));
       ASAdjs.add(1, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(4, 6));
       countryAdjsTemp.add(new Adj(4, 0));
       countryAdjsTemp.add(new Adj(4, 1));
       countryAdjsTemp.add(new Adj(4, 8));
       ASAdjs.add(2, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(4, 7));
       countryAdjsTemp.add(new Adj(4, 9));
       countryAdjsTemp.add(new Adj(4, 11));
       countryAdjsTemp.add(new Adj(4, 5));
       ASAdjs.add(3, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(4, 5));
       countryAdjsTemp.add(new Adj(4, 7));
       ASAdjs.add(4, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(0, 0));
       countryAdjsTemp.add(new Adj(4, 11));
       countryAdjsTemp.add(new Adj(4, 3));
       countryAdjsTemp.add(new Adj(4, 7));
       countryAdjsTemp.add(new Adj(4, 4));
       ASAdjs.add(5, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(4, 2));
       countryAdjsTemp.add(new Adj(4, 0));
       countryAdjsTemp.add(new Adj(3, 2));
       countryAdjsTemp.add(new Adj(3, 1));
       countryAdjsTemp.add(new Adj(2, 5));
       countryAdjsTemp.add(new Adj(2, 4));
       ASAdjs.add(6, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(4, 1));
       countryAdjsTemp.add(new Adj(4, 3));
       countryAdjsTemp.add(new Adj(4, 4));
       countryAdjsTemp.add(new Adj(4, 5));
       countryAdjsTemp.add(new Adj(4, 9));
       ASAdjs.add(7, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(4, 2));
       countryAdjsTemp.add(new Adj(4, 1));
       countryAdjsTemp.add(new Adj(5, 1));
       ASAdjs.add(8, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(4, 10));
       countryAdjsTemp.add(new Adj(4, 11));
       countryAdjsTemp.add(new Adj(4, 3));
       countryAdjsTemp.add(new Adj(4, 7));
       countryAdjsTemp.add(new Adj(4, 1));
       ASAdjs.add(9, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(4, 0));
       countryAdjsTemp.add(new Adj(4, 1));
       countryAdjsTemp.add(new Adj(4, 9));
       countryAdjsTemp.add(new Adj(2, 5));
       ASAdjs.add(10, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(4, 9));
       countryAdjsTemp.add(new Adj(4, 3));
       countryAdjsTemp.add(new Adj(4, 5));
       ASAdjs.add(11, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       adjTemp.add(ASAdjs);
       
       
       Vector<String> AUNames = new Vector<String>();
       Vector<IVector2> AUBoardCoords = new Vector<IVector2>();
       Vector<IVector2> AUSizes = new Vector<IVector2>();
       Vector<Vector<Adj>> AUAdjs = new Vector<Vector<Adj>>();
       
       countryAdjsTemp.add(new Adj(5, 2));
       countryAdjsTemp.add(new Adj(5, 3));
       AUAdjs.add(0, countryAdjsTemp);
       countryAdjsTemp = new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(4, 8));
       countryAdjsTemp.add(new Adj(5, 2));
       countryAdjsTemp.add(new Adj(5, 3));
       AUAdjs.add(1, countryAdjsTemp);
       countryAdjsTemp= new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(5, 0));
       countryAdjsTemp.add(new Adj(5, 1));
       countryAdjsTemp.add(new Adj(5, 3));
       AUAdjs.add(2, countryAdjsTemp);
       countryAdjsTemp= new Vector<Adj>();
       
       countryAdjsTemp.add(new Adj(5, 0));
       countryAdjsTemp.add(new Adj(5, 1));
       countryAdjsTemp.add(new Adj(5, 2));
       AUAdjs.add(3, countryAdjsTemp);
       
       countryAdjsTemp= new Vector<Adj>();
       
		

       adjTemp.add(AUAdjs);
       
    
       
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
       
       
       
       Vector<Vector<Territory>> tempTerrs = new Vector<Vector<Territory>>();
       
       
       Vector<Territory> auxTempinsertion = new Vector<Territory>();
       
       
       for(int i=0; i<nTerritories.get("NorthAmerica"); i++)
       {
    	   auxTempinsertion.add(new Territory(territoryIndex, NANames.get(i), "NorthAmerica", NASizes.get(i), NABoardCoords.get(i)));
    	   
    	   territoryIndex++;
    	   
    	   
       }
       tempTerrs.add(continentIndex, auxTempinsertion);
       territoryIndex=0;
       continentIndex++;
       auxTempinsertion = new Vector<Territory>();
       
       
       for(int i=0; i<nTerritories.get("SouthAmerica"); i++)
       {
       
    	   auxTempinsertion.add(new Territory(territoryIndex, SANames.get(i), "SouthAmerica", SASizes.get(i), SABoardCoords.get(i)));
    	   
    	   territoryIndex++;
       }
       tempTerrs.add(continentIndex, auxTempinsertion);
       territoryIndex=0;
       continentIndex++;
       auxTempinsertion = new Vector<Territory>();
       
       for(int i=0; i<nTerritories.get("Europe"); i++)
       {
    	   auxTempinsertion.add(new Territory(territoryIndex, EUNames.get(i), "Europe", EUSizes.get(i), EUBoardCoords.get(i)));
    	   
    	   territoryIndex++;
       }
       tempTerrs.add(continentIndex, auxTempinsertion);
       territoryIndex=0;
       continentIndex++;
       auxTempinsertion = new Vector<Territory>();

       
       for(int i=0; i<nTerritories.get("Africa"); i++)
       {
    	   auxTempinsertion.add(new Territory(territoryIndex, AFNames.get(i), "Africa", AFSizes.get(i), AFBoardCoords.get(i)));
    	   
    	   territoryIndex++;
       }
       tempTerrs.add(continentIndex, auxTempinsertion);
       territoryIndex=0;
       continentIndex++;
       auxTempinsertion = new Vector<Territory>();

       
       for(int i=0; i<nTerritories.get("Asia"); i++)
       {
    	   auxTempinsertion.add(new Territory(territoryIndex, ASNames.get(i), "Asia", ASSizes.get(i), ASBoardCoords.get(i)));
    	   
    	   territoryIndex++;
    	   
       }
       tempTerrs.add(continentIndex, auxTempinsertion);
       territoryIndex=0;
       continentIndex++;
       auxTempinsertion = new Vector<Territory>();

       
       for(int i=0; i<nTerritories.get("Australia"); i++)
       {
    	   auxTempinsertion.add(new Territory(territoryIndex, AUNames.get(i), "Australia", AUSizes.get(i), AUBoardCoords.get(i)));
    	   
    	   territoryIndex++;
       }
       tempTerrs.add(continentIndex, auxTempinsertion);
       territoryIndex=0;
       continentIndex++;
       auxTempinsertion = new Vector<Territory>();

       
       //enfiar as adjacencias nos territorios
       
       for(int i = 0; i< tempTerrs.size(); i++ )
       {
    	   
           
    	   for(int j =0; j< adjTemp.get(i).size(); j++)
    	   {
    		   
    		  
    		   Vector<Adj> adjindex = new Vector<Adj>(); 
    				 adjindex = adjTemp.get(i).get(j);
    		   
    				
    		   Vector<Territory> adjsToInsert = new Vector<Territory>();
    		  
    		   for(int k =0 ; k<adjindex.size(); k++)
    		   {
    			  
    			   adjsToInsert.add(tempTerrs.get(adjindex.get(k).getContIndex())
    					   					 .get(adjindex.get(k).getCountryIndex()));
    		   }
    		   
    		   Territory toAddAdjacent = new Territory();
    		   toAddAdjacent = tempTerrs.get(i).get(j);
    		   
    		   toAddAdjacent.setAdjacentTerr(adjsToInsert);
    		   //tempTerrs.get(i).get(j).
    		   
    		   
    		   gameBoard.add(toAddAdjacent);
    		  
    		   
    		   //gameBoard.add(tempTerrs.get(i).get(j).setAdjacentTerr(tempTerrs.get(adjTemp.get(i).get)));
    	   }
       }
       
       System.out.println("player0: "  + players.get(0).getAvailableSoldierNumber());
       System.out.println("player1: "  + players.get(1).getAvailableSoldierNumber());
       
       
       for(int i = 0; i<gameBoard.size(); i++)
       {
    	   
    	   
    	   Player dummy = players.get(r.nextInt(players.size()));
    	   
    	   Army dummyArmy= new Army(dummy, 1);
    	   
    	  
    	   
    	   Territory dummyTerr= gameBoard.get(i);
    	   dummyTerr.setArmy(dummyArmy);
    	   dummy.setAvailableSoldierNumber(dummy.getAvailableSoldierNumber()-1);
    	   dummyTerr.setGameBoardIndex(i);
    	   dummy.addTerritory(dummyTerr);
    	   
    	   
    	   
    	   
       Map properties = new HashMap();
       properties.put("position", gameBoard.get(i).getBoardCoord());
       properties.put("type", gameBoard.get(i).getShapeType());
       properties.put("size", gameBoard.get(i).getSize());
       properties.put("army", gameBoard.get(i).getArmy());
       properties.put("armySize", gameBoard.get(i).getArmy().getArmySize());
       properties.put("index", gameBoard.get(i).getIndex());
       properties.put("territoryname", gameBoard.get(i).getTerritoryName());
       properties.put("continentname", gameBoard.get(i).getContinentName());
       properties.put("textSize", gameBoard.get(i).getTextSize());
       properties.put("ownerColor", gameBoard.get(i).getOwnerColor());
       
       
       space.createSpaceObject("Territory", properties, null);
       
       }
       
       System.out.println("ndejogadores: "  + space.getProperties());
       System.out.println("ndejogadores: "  + playersNumber);
       
       System.out.println("player 0 remaining army: " + players.get(0).getAvailableSoldierNumber());
       System.out.println("player 1 remaining army: " + players.get(1).getAvailableSoldierNumber());
       
       
       
       int checkCounter=0;
       Boolean endAllocation= false;
       while(endAllocation==false)
       {
    	   
    	   for (int i= 0; i<players.size(); i++) 
    	   {
    		   Player tempPlayer = players.get(i);
    		   
    		   //System.out.println("player " + i+  ": "+ tempPlayer.getAvailableSoldierNumber());
				if(tempPlayer.getAvailableSoldierNumber()==0)
				{
					
					
					//tempPlayer.setAvailableSoldierNumber(0);
					checkCounter++;
				}
				else
				{
					Territory randomFromPlayer = players.get(i).getTerritory(r.nextInt(players.get(i).getTerritories().size()));
					
					
					Army newArmy = randomFromPlayer.getArmy();
					newArmy.addArmy(1);
					tempPlayer.setAvailableSoldierNumber(tempPlayer.getAvailableSoldierNumber()-1);
					
					gameBoard.set(randomFromPlayer.getGameBoardIndex(), randomFromPlayer);
					
				}
    		   
    	   }
    	   
    	   
    	   
    	 if( checkCounter==players.size())
    	 {
    		 endAllocation=true;
    	 }
    	 else
    	 {
    		 checkCounter=0;
    	 }
    	   
    	   
       }
       
       
       
       System.out.println("player 0 remaining army: " + players.get(0).getAvailableSoldierNumber());
       System.out.println("player 1 remaining army: " + players.get(1).getAvailableSoldierNumber());
      // System.out.println("spaceobjects: " + space.getSpaceObjectsByType("Territory"));
       
       
       updateGUI(gameBoard, space);


       for(int i=0; i<players.size(); i++)
       {
       Map player_M = new HashMap();
       player_M.put("name", players.get(i).getName());
       player_M.put("index", players.get(i).getIndex());
       player_M.put("color", players.get(i).getColor());
       space.createSpaceObject("Player", player_M, null);
       
       }
       
       
       System.out.println("numero de objectos player:  " + space.getSpaceObjectsByType("Player").length);
       ISpaceObject[] z = space.getSpaceObjectsByType("Player");
        System.out.println("nome  " + z[0].getProperty("name") );
        System.out.println("cor  " + z[1].getProperty("color") );
       IComponentDescription[] w =space.getComponents();

       System.out.println("numero de componentwes    " + w.length );
       
    }

   
	public void updateGUI(Vector<Territory> updatedBoard, Space2D oldSpace)
	{
		ISpaceObject[] terrs= oldSpace.getSpaceObjectsByType("Territory");
		
		if(terrs.length != updatedBoard.size())
		{
			System.out.println("tamanho das estuturas errado");
		}
		
		for(int i = 0 ; i< updatedBoard.size(); i++)
		{
				terrs[i].setProperty("position", updatedBoard.get(i).getBoardCoord());
		       terrs[i].setProperty("type", updatedBoard.get(i).getShapeType());
		       terrs[i].setProperty("size", updatedBoard.get(i).getSize());
		       terrs[i].setProperty("army", updatedBoard.get(i).getArmy());
		       terrs[i].setProperty("armySize", updatedBoard.get(i).getArmy().getArmySize());
		       terrs[i].setProperty("index", updatedBoard.get(i).getIndex());
		       terrs[i].setProperty("territoryname", updatedBoard.get(i).getTerritoryName());
		       terrs[i].setProperty("continentname", updatedBoard.get(i).getContinentName());
		       terrs[i].setProperty("textSize", updatedBoard.get(i).getTextSize());
		       terrs[i].setProperty("ownerColor", updatedBoard.get(i).getOwnerColor());
		}

    }



	@Override
    public void shutdown(IEnvironmentSpace iEnvironmentSpace) {

    }

	
	public IComponentIdentifier[] getAgentsByType(String type, Space2D spaceToSearch){
		
		//final IComponentIdentifier[] tempList= new IComponentIdentifier[]{};
		
		IFuture<IComponentIdentifier[]> fut = spaceToSearch.getExternalAccess().getChildren("Playerbdi");
		fut.addResultListener(new DefaultResultListener<IComponentIdentifier[]>() {
			public void resultAvailable(IComponentIdentifier[] cs) {
				listaret = cs;
				
			}
		});
		
		return listaret;
	}
	
	
    @Override
    public void execute(IClockService iClockService, IEnvironmentSpace iEnvironmentSpace) {

    	Space2D space = (Space2D) iEnvironmentSpace;
    	Vector<String> agentTypes= new Vector<String>();
    	
    	agentTypes.add("Playerbdi");
    	agentTypes.add("PlayerRea");
    	
    	
    	int numberOfAgents =0;
    	
    	
    	for (String agentype : agentTypes) {
    		
    		
    		numberOfAgents+= getAgentsByType(agentype, space).length;
			
		}
    	
    	int typeifagentssize =0;
    	
    	IComponentIdentifier[] tempList= new IComponentIdentifier[numberOfAgents];
    	for(int i=0; i<numberOfAgents; i++)
    	{
    		IComponentIdentifier[] round = new IComponentIdentifier[numberOfAgents];
    		
    		round =getAgentsByType(agentTypes.get(i), space);
    		for(int j=0; j< round.length; j++)
    		{
    			
    			tempList[typeifagentssize]= round[j];
    			System.out.println("component identifier: " + tempList[typeifagentssize]);
    			typeifagentssize++;
    		}
    	}
    	
    	
    	
    	
    	
    	
    	
    	for(int i=0; i<numberOfAgents; i++)
    	{
    	//System.out.println("meta "+ tempList.length);
    	//System.out.println("meta "+ tempList[0]);
    	//IComponentDescription asd;
    	System.out.println("componente description" + space.getAvatar(space.getComponents()[i]).getId());
    	
    	
    	ISpaceObject cenas= (ISpaceObject) space.getAvatar(space.getComponents()[i]).getId();
    	
    	
    	//System.out.println("componentes do space" + space.getAvatar(space.getComponents()[1]));
    	
    
    	
    	//ISpaceObject player = space.getSpaceObject(tempList[0]);
    	
    	//System.out.println("player ->  "+ player.getType());
    	}
    	
    	//IComponentDescription[] lista= new IComponentDescription[]{};
    	
    	//lista = space.getComponents();


    }


}
