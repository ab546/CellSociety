package cellsociety_team03;

//segregation game simulation
import java.util.ArrayList;
import java.util.*;
class SegregationGame extends Game{
	private final String IDENTICAL = "identical";
	private final String TOTAL = "total";
	private final String BLUE = "blue";
	private final String RED = "red";
	private final String EMPTY = "empty";
	private final String DEFAULT="default";
	private int similar;
	public static final boolean countDiagonals= true;
	private SegregationCell[][] nextgrid;

	private double[][] similarityGrid;
	private List<String> emptyCellIndexes;




	private boolean countDiagonalNeighbors=true;
	
	public SegregationGame(int inputSize, SegregationCell[][] cellarray,int similarThreshold, boolean toroidalOrNot, String shape){
		super(inputSize,toroidalOrNot,shape);
		replacegrid(cellarray);
		this.similar = similarThreshold;

		initialize(countDiagonals);
	}


	
	
	
	
	public void step() {
		updateCellSimilarity();
		moveCells();
	}

	public void updateCellSimilarity() {
		similarityGrid = new double[getsize()][getsize()];
		
		getneighbors().importCurrentGrid(getgrid());
		for (int row = 0; row < getsize(); row++) {
			for (int col = 0; col < getsize(); col++) {
				similarityGrid[row][col] = 999;
				if (!getgrid()[row][col].getType().equals(EMPTY)) {
					int neighbors = getneighbors().neighbors(row,col,false, false, DEFAULT).size();
					int identicalNeighbors = getneighbors().neighbors(row,col,true, false, DEFAULT).size();
					//System.out.println("similar is "+identicalNeighbors);

					if (neighbors != 0) {
						similarityGrid[row][col] = (identicalNeighbors * 100.0) / (neighbors*1.0);
						//System.out.println(similarityGrid[row][col] + " similarity"+ " row: "+row+" col:"+col);


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
		Cell[][] grid=getgrid().clone();

		for (int myrow = 0; myrow < getsize(); myrow++) {
			for (int mycol = 0; mycol < getsize(); mycol++) {
				
				
				//System.out.println(getgrid()[myrow][mycol].getType().equals(nextgrid[myrow][mycol].getType()));
				//getgrid()[myrow][mycol] = new SegregationCell("blue");
				getgrid()[myrow][mycol] = new SegregationCell(nextgrid[myrow][mycol].getType());
				//getgrid()[myrow][mycol]=null;
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
	
