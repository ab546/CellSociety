package cellsociety_team03;

import java.util.ArrayList;
import java.util.List;

//get neighbors for a square cell
public class SquareNeighbors implements Neighbors{
	private boolean toroidal;
	private boolean countDiagonalNeighbor;
	private Cell[][] currentGrid;
	private String WHITE="white";

	public void initialize(boolean diagonals, boolean wrapToroidal){
		countDiagonalNeighbor=diagonals;
		toroidal=wrapToroidal;
	}

	public void importCurrentGrid(Cell[][] myGrid){
		currentGrid=myGrid;
	}

	
	
	/**
	i, j: coordinates of cell to check neighbors
	onlyIdentical: only look for identical neighbors
	otherwise, getOtherType: only look for neighbors of a certain type
	if getOtherType, need to specify which type we are looking for: thatType
	*/
	public List<int[]> neighbors(int i, int j, boolean onlyIdentical, boolean getOtherType, String thatType ){
		int rowLength=currentGrid.length;
		int colLength=rowLength;
		ArrayList<int[]> identicalNeighbors=new ArrayList<int[]>();
		ArrayList<int[]> neighbors=new ArrayList<int[]>();
		ArrayList<int[]> neighborsTargetType=new ArrayList<int[]>();
		for (int x = Math.max(0, i - 1); x <= Math.min(i + 1, rowLength - 1); x++) {
			for (int y = Math.max(0, j - 1); y <= Math.min(j + 1, colLength - 1); y++) {
				int[] coords={x, y};
				if (!((!countDiagonalNeighbor&&((x==i-1&&y==j-1)||(x==i-1&&y==j+1)||(x==i+1&&y==j-1)||(x==i+1&&y==j+1))))){
					//System.out.println("we made it line 32");
					if ((x != i || y != j) &&   (currentGrid[x][y].getTypecolor().equals(currentGrid[i][j].getTypecolor()))) {
						
						identicalNeighbors.add(coords);
						//System.out.println(identicalNeighbors.size());

					}

					if ((x != i || y != j) &&   (currentGrid[x][y].getType().equals(thatType))) {
						neighborsTargetType.add(coords);
					}


					if ((x != i || y != j) && !currentGrid[x][y].getTypecolor().equals(WHITE)) {
						neighbors.add(coords);
						
				    	//System.out.println(coords[0]+" "+coords[1]+ "we made it line 47");

					}
				}
			}
		}

		if (getOtherType){
			return neighborsTargetType;
		}

		if (onlyIdentical){
			return identicalNeighbors;
		}
		return neighbors;
	}


	public void wrapToroidal(){
		return;

	}

	@Override
	public List<Coordinate> getCellsgivenOrientation(int i, int j, int orientation) {
		// TODO Auto-generated method stub
		return null;
	}


}