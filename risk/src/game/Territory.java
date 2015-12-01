package game;

import java.util.Vector;

import jadex.extension.envsupport.math.IVector2;

public class Territory {

	private int index;
	
	int shapeType;
	
	public String territoryName, continentName;
	
	public int getShapeType() {
		return shapeType;
	}

	public void setShapeType(int shapeType) {
		this.shapeType = shapeType;
	}

	private Vector<Territory> adjacentTerr;
	
	public Vector<Territory> getAdjacentTerr() {
		return adjacentTerr;
	}

	public void setAdjacentTerr(Vector<Territory> adjacentTerr) {
		this.adjacentTerr = adjacentTerr;
	}

	private Army army;
	
	private IVector2 size;
	
	private IVector2 boardCoord;
	
	public Territory(int index, String territoryName, String continentName, IVector2 size, IVector2 boardCoord, Vector<Territory> adjacentTerritories) 
	{
		this.index = index;
		this.territoryName=territoryName;
		this.continentName=continentName;
		this.size= size;
		this.boardCoord= boardCoord;
		this.army= new Army(null, 0);
		this.adjacentTerr=adjacentTerritories;
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
		if(this.army != null && army.getPlayer() != this.army.getPlayer()) {
			this.army.getPlayer().removeTerritory(this);	
		}
		army.getPlayer().addTerritory(this);
		this.army = army;;
	}

	
	public Player getOwner() {
		if(army == null) {
			return null;
		}else {
			return army.getPlayer();
		}
	}
	
}
