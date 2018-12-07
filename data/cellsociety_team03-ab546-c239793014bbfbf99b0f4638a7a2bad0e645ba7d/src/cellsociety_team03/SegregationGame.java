package cellsociety_team03;

import java.util.*;
class SegregationGame extends Game{
	private final String IDENTICAL = "identical";
	private final String TOTAL = "total";
	private final String BLUE = "blue";
	private final String RED = "red";
	private final String EMPTY = "empty";
	private int similar;
	
	private SegregationCell[][] nextgrid;

	private double[][] similarityGrid;
	private List<String> emptyCellIndexes;
	private int redpercent;
	private int bluepercent;
	private Neighbors n;
	private boolean toroidal;
	private String cellShape;
	String SQUARE="square";
	String TRIANGULAR="triangular";
	String HEXAGONAL="hexagonal";
	private boolean countDiagonalNeighbors=true;
	
	public SegregationGame(int inputSize, SegregationCell[][] cellarray,int similarThreshold, boolean toroidalOrNot, String shape){
		super(inputSize);
		replacegrid(new SegregationCell[getsize()][getsize()]);
		this.similar = similarThreshold;
	//	redpercent=redPercentage;
	//	bluepercent=bluePercentage;
		toroidal=toroidalOrNot;
		
		cellShape=shape;
		initialize();
	}


	public void initialize() {

		/*
		Random r = new Random();
		for (int row = 0; row < getsize(); row++) {
			for (int col = 0; col < getsize(); col++) {
			
				int randomNum = r.nextInt((100 - 0) + 1) + 0; // random number between 0 and 100
				if (randomNum <= redpercent) {
					getgrid()[row][col]=new SegregationCell(RED);  //	WILL NEED TO TRY TO GET RID OF THIS SOMEHOW
				} else if (randomNum <= redpercent + bluepercent) {
					getgrid()[row][col]= new SegregationCell(BLUE);
				} else {
					getgrid()[row][col]= new SegregationCell(EMPTY);
				}
			}
		}*/
		
		if (cellShape.equals(SQUARE)){
		n=new SquareNeighbors();
	}
	else if (cellShape.equals(HEXAGONAL)){
		n=new HexagonalNeighbors();
	}
//	else if (cellShape.equals(TRIANGULAR)){
//		n=new TriangularNeighbors();
//	}
		
	n.initialize(countDiagonalNeighbors, toroidal);

	}

	
	
	
	
	public void step() {
		updateCellSimilarity();
		moveCells();
	}

	public void updateCellSimilarity() {
		similarityGrid = new double[getsize()][getsize()];
		
		n.importCurrentGrid(getgrid());
		for (int row = 0; row < getsize(); row++) {
			for (int col = 0; col < getsize(); col++) {
				similarityGrid[row][col] = Double.POSITIVE_INFINITY;
				if (!getgrid()[row][col].getType().equals(EMPTY)) {
					double neighbors = n.neighbors(row,col,false);
					
					double identicalNeighbors = n.neighbors(row,col,true);
					
					if (neighbors != 0) {
						similarityGrid[row][col] = (identicalNeighbors * 100) / neighbors;
					}
				}
			}
		}
	}	
	
	public void moveCells() {
		
		emptyCellIndexes = new ArrayList<String>();
		nextgrid = new SegregationCell[getsize()][getsize()];
		for (int row = 0; row < getsize(); row++) {
			for (int col = 0; col < getsize(); col++) {
				nextgrid[row][col] = new SegregationCell( getgrid()[row][col].getType());
				if (getgrid()[row][col].getType().equals(EMPTY)) {
					emptyCellIndexes.add(row + "," + col);
				}
			}
		}
		for (int row = 0; row < getsize(); row++) {
			for (int col = 0; col < getsize(); col++) {
				
				if (similarityGrid[row][col] < similar) {
					
					moveElement(row, col);
				}
			}
		}

		for (int myrow = 0; myrow < getsize(); myrow++) {
			for (int mycol = 0; mycol < getsize(); mycol++) {
				
				getgrid()[myrow][mycol] = new SegregationCell(nextgrid[myrow][mycol].getType());
			}
		}

	}

	public void moveElement(int row, int col) {

		int deletedRow = row;
		int deletedCol = col;
		Random r = new Random();
		if (emptyCellIndexes.size() == 0) {
			return;
		}
		int newCellPicker = r.nextInt(((emptyCellIndexes.size() - 1) - 0) + 1) + 0;
		int newRow = Integer.parseInt(emptyCellIndexes.get(newCellPicker).split(",")[0]);
		int newCol = Integer.parseInt(emptyCellIndexes.get(newCellPicker).split(",")[1]);
		
		String colorToReplace = getgrid()[deletedRow][deletedCol].getTypecolor();
		if (colorToReplace.equals(BLUE)) {
			nextgrid[newRow][newCol]= new SegregationCell(BLUE);
		} else {
			nextgrid[newRow][newCol]= new SegregationCell(RED);
		}
		emptyCellIndexes.remove(newCellPicker);
		nextgrid[deletedRow][deletedCol]= new SegregationCell(EMPTY);

	}

	

}
	

