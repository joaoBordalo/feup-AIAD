package game;

public class Territory {

	private int index;
	
	private Army army;
	
	private final int row,column;
	
	public Territory(int index, int row, int column) 
	{
		this.index = index;
		this.row = row;
		this.column = column;
	}
	
	public boolean isAdjacent(Territory territory) {
		int rowDiff = Math.abs( getRow() - territory.getRow() );
		int colDiff = Math.abs( getColumn() - territory.getColumn() );
	
		// se o territorio for o mesmo ou mais longe do que 1 quadrado, é falso
		return (rowDiff + colDiff == 1);
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

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
	
	public Player getOwner() {
		if(army == null) {
			return null;
		}else {
			return army.getPlayer();
		}
	}
	
}
