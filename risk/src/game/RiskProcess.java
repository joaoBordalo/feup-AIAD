package game;
import jadex.bridge.service.types.clock.IClockService;
import jadex.commons.SimplePropertyObject;
import jadex.extension.envsupport.environment.IEnvironmentSpace;
import jadex.extension.envsupport.environment.ISpaceProcess;
import jadex.extension.envsupport.environment.space2d.Space2D;
import jadex.extension.envsupport.math.IVector2;
import jadex.extension.envsupport.math.Vector2Double;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Vector;


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

	@Override
    public void start(IClockService arg0, IEnvironmentSpace arg1) {

        Space2D space = (Space2D)arg1;

        this.players= new Vector<Player>();
        
        players.addElement(new Player(0, "zero", Color.cyan));
        players.addElement(new Player(1, "um", Color.gray));
        
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
       //System.out.println("size: " + adjTemp.size());
       //System.out.println("size at i: " + adjTemp.get(0).size());
       
      
       
       
       
       
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
       
       //System.out.println("size: " + adjTemp.size());
       //System.out.println("size at i: " + adjTemp.get(1).size());
       
       
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
       
     /* for(int i =0; i< AUAdjs.size(); i++)
      {
    	  System.out.println("size auadjs" + AUAdjs.size() + " " + AUAdjs.get(i));
      }
       System.out.println("auadjscenas+foiahsgf+ao" + AUAdjs.size() + " " + AUAdjs.get(0));
       System.out.println("adjtemp no fim: " + adjTemp.size());*/
       
       
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
       Vector<Territory> gameBoard= new Vector<Territory>();
       
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
    		   
    		   //System.out.println("j->:" + j);
    		   Vector<Adj> adjindex = new Vector<Adj>(); 
    				 adjindex = adjTemp.get(i).get(j);
    		   
    				
    		   Vector<Territory> adjsToInsert = new Vector<Territory>();
    		   //System.out.println("size adjindex "+adjindex.size());
    		   for(int k =0 ; k<adjindex.size(); k++)
    		   {
    			   //System.out.println("tempterr: " +tempTerrs.get(k));
    			   //System.out.println("k: " +k);
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
       
       
       
       
       for(int i = 0; i<gameBoard.size(); i++)
       {
    	   
    	   
    	   Player dummy = players.get(r.nextInt(players.size()));
    	   Army dummyArmy= new Army(dummy, 1);
    	   
    	   gameBoard.get(i).setArmy(dummyArmy);
    	   
    	   
    	   
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
       


    }

   

	@Override
    public void shutdown(IEnvironmentSpace iEnvironmentSpace) {

    }

    @Override
    public void execute(IClockService iClockService, IEnvironmentSpace iEnvironmentSpace) {
    	//System.out.println("tou a dar turno");
    }


}
