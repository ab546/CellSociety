package cellsociety_team03;

public abstract class Game {
	
	private int mysize;
	String SQUARE="square";
	String TRIANGULAR="triangular";
	String HEXAGONAL="hexagonal";
	private Cell[][] myGrid;


	public void initializeGame(Cell[][] cellarray){
		replacegrid(cellarray);
	}
	
	public void replacegrid(Cell[][] newgrid){
		myGrid= newgrid;
	}
	
	public abstract void step();

	public int getsize(){
		return mysize;
	}
	public Cell[][] getgrid(){
		return myGrid;
	}
	
	public Game(int size){
		mysize=size;
	}
	
	
	public void setgrid(Cell[][] inputgrid){ //changed from Grid inputgrid to Cell[][] inputgrid

		myGrid= inputgrid;
	}
	
	
	
	
}

