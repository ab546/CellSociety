package cellsociety_team03;

public abstract class Game {
	
	//abstract class which all games use
	
	
	public static final String SQUARE="square";
	public static final String TRIANGULAR="triangular";
	public static final String HEXAGONAL="hexagonal";
	private String mycellShape;
	private boolean toroidal;
	private Neighbors n;
	
	private int mysize;

	private Cell[][] myGrid;
	
	
	
public void initialize(boolean countDiagonalNeighbors){
		
		if (getcellshape().equals(SQUARE)){
			n=new SquareNeighbors();
		}
		else if (getcellshape().equals(HEXAGONAL)){
			n=new HexagonalNeighbors();
		}
//		else if (cellShape.equals(TRIANGULAR)){
//			n=new TriangularNeighbors();
//		}
		n.initialize(countDiagonalNeighbors, gettoroidal());
		
		
	}
	public Neighbors getneighbors(){
		return n;
	}

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
	
	public Game(int size,boolean istoroidal, String cellshape){
		mysize=size;
		toroidal=istoroidal;
		mycellShape=cellshape;
	}
	
	
	public boolean gettoroidal(){
		return toroidal;
	}
	public String getcellshape(){
		return mycellShape;
	}
	public void setgrid(Cell[][] inputgrid){ //changed from Grid inputgrid to Cell[][] inputgrid

		myGrid= inputgrid;
	}
	
	
	
	
	
	
}

