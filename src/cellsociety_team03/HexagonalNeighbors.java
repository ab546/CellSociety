package cellsociety_team03;
import java.util.*;

//calculate neighbor locations for hexagonal cells

public class HexagonalNeighbors implements Neighbors{
	private boolean toroidal;
	private boolean countDiagonalNeighbor=true; //the concept of diagonal neighbors does not exist for hexagonal grids
	private Cell[][] currentGrid;
	private String WHITE="white";
	private List<int[]> cells;
	public void initialize(boolean diagonals, boolean wrapToroidal){
		toroidal=wrapToroidal;
	}
	public void importCurrentGrid(Cell[][] myGrid){
		currentGrid=myGrid;
	}
	
	
	public void wrapToroidal(){
		return;
	}
	
	
	/**
	i, j: coordinates of cell to check neighbors
	onlyIdentical: only look for identical neighbors
	otherwise, getOtherType: only look for neighbors of a certain type
	if getOtherType, need to specify which type we are looking for: thatType
	*/
	public List<int[]> neighbors(int i, int j, boolean onlyIdentical, boolean getOtherType, String thatType){
		ArrayList<int[]> identicalNeighbors=new ArrayList<int[]>();
		ArrayList<int[]> neighbors=new ArrayList<int[]>();
		ArrayList<int[]> neighborsTargetType=new ArrayList<int[]>();
		cells=getCellsToCheck(i, j);
		for (int c=0; c<cells.size(); c++){
			int x=cells.get(c)[0];
			int y=cells.get(c)[1];
			int[] coords={x, y};
			if (currentGrid[x][y].getTypecolor().equals(currentGrid[i][j].getTypecolor())){
				identicalNeighbors.add(coords);
			}
			if (currentGrid[x][y].getType().equals(currentGrid[i][j].getType().equals(thatType))){
				neighborsTargetType.add(coords);
			}
			if (!currentGrid[x][y].getTypecolor().equals(WHITE)){
				neighbors.add(coords);
			}
		}
		if (getOtherType){
			return neighborsTargetType;
		}
		if(onlyIdentical){
			return identicalNeighbors;
		}
		return neighbors;
	}
	public boolean isOnScreen(int x,int y){
		return (x>=0&&x<currentGrid.length&&y>=0&&y<currentGrid.length);
	}
	public List<int[]> getCellsToCheck(int i, int j){
		ArrayList<int[]> cells=new ArrayList<int[]>();
		int[][] potentialCells={{i, j+1},{i+1,j},{i+1, j-1},{i, j-1},{i-1, j},{i-1, j+1}};
		for (int el=0; el<potentialCells.length; el++){
			if (isOnScreen(potentialCells[el][0],potentialCells[el][1])){
				int[] newEl={potentialCells[el][0],potentialCells[el][1]};
				cells.add(newEl);
			}
		}
		
		
		return cells;
	}
	@Override
	public List<Coordinate> getCellsgivenOrientation(int i, int j, int orientation) {
		// TODO Auto-generated method stub
		return null;
	}
}