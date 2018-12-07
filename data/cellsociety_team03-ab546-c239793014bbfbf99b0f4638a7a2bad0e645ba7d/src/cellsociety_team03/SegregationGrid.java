package cellsociety_team03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

class SegregationGrid extends Grid {

	public static final String IDENTICAL = "identical";
	public static final String TOTAL = "total";

	private int similar;
	
	private SegregationCell[][] nextgrid;

	private double[][] similarityGrid;
	private ArrayList<String> emptyCellIndexes;
	private int redpercent;
	private int bluepercent;
	
	public SegregationGrid(int inputsize, int percentRed, int percentBlue, int similaritythreshold){
		
		super(inputsize);
		replacegrid(new SegregationCell[getsize()][getsize()]);
		this.similar = similaritythreshold;
		redpercent=percentRed;
		bluepercent=percentBlue;
		
	}
	
	public void initialize() {

		
		Random r = new Random();
		for (int row = 0; row < getsize(); row++) {
			for (int col = 0; col < getsize(); col++) {
			
				int randomNum = r.nextInt((100 - 0) + 1) + 0; // random number between 0 and 100
				if (randomNum <= redpercent) {
					getgrid()[row][col]=new SegregationCell("red");
				} else if (randomNum <= redpercent + bluepercent) {
					getgrid()[row][col]= new SegregationCell("blue");
				} else {
					getgrid()[row][col]= new SegregationCell("empty");
				}
			}
		}

	}

	
	
	
	
	public void step() {
		updateCellSimilarity();
		moveCells();
	}

	public void updateCellSimilarity() {
		similarityGrid = new double[getsize()][getsize()];
		for (int row = 0; row < getsize(); row++) {
			for (int col = 0; col < getsize(); col++) {
				similarityGrid[row][col] = Double.POSITIVE_INFINITY;
				if (!getgrid()[row][col].getType().equals("empty")) {

					double neighbors = neighborsTotalOrIdentical(row,col,TOTAL);
					double identicalNeighbors = neighborsTotalOrIdentical(row,col,IDENTICAL);

					if (neighbors != 0) {
						similarityGrid[row][col] = (identicalNeighbors * 100) / neighbors;
					}
				}
			}
		}
	}


//	private double totalneighbors(int row,int col){
//		int rowLength=getsize();
//		int colLength=getsize();
//		double neighbors=0;
//		for (int x = Math.max(0, row - 1); x <= Math.min(row + 1, rowLength - 1); x++) {
//			for (int y = Math.max(0, col - 1); y <= Math.min(col + 1, colLength - 1); y++) {
//				if ((x != row || y != col) && !getgrid()[x][y].getTypecolor().equals("white")) {
//					neighbors += 1.0;
//				}
//			}
//		}
//		return neighbors;
//	}
	
//	private double identicalneighbors(int row, int col){
//		int identicalNeighbors=0;
//		int rowLength=getsize();
//		int colLength=getsize();
//		for (int x = Math.max(0, row - 1); x <= Math.min(row + 1, rowLength - 1); x++) {
//			for (int y = Math.max(0, col - 1); y <= Math.min(col + 1, colLength - 1); y++) {
//				if ((x != row || y != col) &&   (getgrid()[x][y].getTypecolor().equals(getgrid()[row][col].getTypecolor()))) {
//						identicalNeighbors += 1.0;
//					}
//				}
//			}
//		
//		return identicalNeighbors;
//		}
	private double neighborsTotalOrIdentical(int row, int col, String identicalOrTotal){
		int identicalNeighbors=0;

		int rowLength=getsize();
		int colLength=getsize();
		double neighbors=0;
		for (int x = Math.max(0, row - 1); x <= Math.min(row + 1, rowLength - 1); x++) {
			for (int y = Math.max(0, col - 1); y <= Math.min(col + 1, colLength - 1); y++) {

				if ((x != row || y != col) &&   (getgrid()[x][y].getTypecolor().equals(getgrid()[row][col].getTypecolor()))) {
					identicalNeighbors += 1.0;
				}
				if ((x != row || y != col) && !getgrid()[x][y].getTypecolor().equals("white")) {
					neighbors += 1.0;
				}
				}
			}
		if(identicalOrTotal.equals(IDENTICAL)){
			return identicalNeighbors;
		}
		else if(identicalOrTotal.equals(TOTAL)){
			return neighbors;
		}	
		return 0;
	}

	
	
	
	
	
	
	
	
	public void moveCells() {
		emptyCellIndexes = new ArrayList<String>();
		nextgrid = new SegregationCell[getsize()][getsize()];
		for (int row = 0; row < getsize(); row++) {
			for (int col = 0; col < getsize(); col++) {
				nextgrid[row][col] = new SegregationCell( getgrid()[row][col].getType());
				if (getgrid()[row][col].getType().equals("empty")) {
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
		if (colorToReplace.equals("blue")) {
			nextgrid[newRow][newCol]= new SegregationCell("blue");
		} else {
			nextgrid[newRow][newCol]= new SegregationCell("red");
		}
		emptyCellIndexes.remove(newCellPicker);
		nextgrid[deletedRow][deletedCol]= new SegregationCell("empty");

	}

	

}