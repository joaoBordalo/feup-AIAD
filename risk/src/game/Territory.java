package game;

import java.awt.Color;
import java.util.Vector;


import jadex.extension.envsupport.environment.ISpaceObject;
import jadex.extension.envsupport.math.IVector2;

public class Territory{

	private int index;
	
	int shapeType;
	
	public String territoryName, continentName;
	
	private Vector<Territory> adjacentTerr;
	
	private Army army;
	
	private IVector2 size;
	
	private IVector2 boardCoord;
	
	private Integer textSize;
	
	private Color ownerColor;
	
	private int gameBoardIndex;
	
	public int getGameBoardIndex() {
		return gameBoardIndex;
	}



	public void setGameBoardIndex(int gameBoardIndex) {
		this.gameBoardIndex = gameBoardIndex;
	}



	public Territory(int index, String territoryName, String continentName, IVector2 size, IVector2 boardCoord) 
	{
		this.index = index;
		this.territoryName=territoryName;
		this.continentName=continentName;
		this.size= size;
		this.boardCoord= boardCoord;
		this.army= new Army(null, 0);
		this.adjacentTerr=null;
		this.textSize=2;
		//this.ownerColor= army.getPlayer().getColor();
	}
	
 //// convert an ISpaceObject of type Territory into an actual Territory
	public Territory(ISpaceObject s){
		int index = (int) s.getProperty("index");
		String territoryName = (String) s.getProperty("territoryname");
		String continentName = (String) s.getProperty("continentname");
		IVector2 size = (IVector2) s.getProperty("size");
		IVector2 boardCoord = (IVector2) s.getProperty("position");
		int shape = (int) s.getProperty("type");
		int testsize = (int) s.getProperty("textSize");
		Color owner = (Color) s.getProperty("ownerColor");

		setOwnerColor(owner);
		setIndex(index);
		setTextSize(testsize);
		setShapeType(shape);
		setTerritoryName(territoryName);
		setContinentName(continentName);
		setSize(size);
		setBoardCoord(boardCoord);
		
		System.out.println("construtor terr " + this.getIndex());

	}


	public Color getOwnerColor() {
		return ownerColor;
	}



	public void setOwnerColor(Color ownerColor) {
		this.ownerColor = ownerColor;
	}



	public Territory(){}
	
	
	public Integer getTextSize() {
		return textSize;
	}

	public void setTextSize(Integer textSize) {
		this.textSize = textSize;
	}

	public int getShapeType() {
		return shapeType;
	}

	public void setShapeType(int shapeType) {
		this.shapeType = shapeType;
	}
	
	
	public Vector<Territory> getAdjacentTerr() {
		return adjacentTerr;
	}

	public void setAdjacentTerr(Vector<Territory> adjacentTerr) {
		this.adjacentTerr = adjacentTerr;
	}
	
	public String getTerritoryName() {
		return territoryName;
	}

	public void setTerritoryName(String territoryName) {
		this.territoryName = territoryName;
	}

	public String getContinentName() {
		return continentName;
	}

	public void setContinentName(String continentName) {
		this.continentName = continentName;
	}

	public IVector2 getBoardCoord() {
		return boardCoord;
	}

	public void setBoardCoord(IVector2 boardCoord) {
		this.boardCoord = boardCoord;
	}

	public IVector2 getSize() {
		return size;
	}

	public void setSize(IVector2 size) {
		this.size = size;
	}

	

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Army getArmy() {
		return army;
	}

	public void setArmy(Army army) {
		/*if(this.army != null && army.getPlayer() != this.army.getPlayer()) {
			this.army.getPlayer().removeTerritory(this);	
		}
		army.getPlayer().addTerritory(this);*/
		this.army = army;
		this.ownerColor= army.getPlayer().getColor();
	}

	
	public Player getOwner() {
		if(army == null) {
			return null;
		}else {
			return army.getPlayer();
		}
	}
	
	
	
	
}
