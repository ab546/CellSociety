package cellsociety_team03;
import java.util.*;
public class HexagonalNeighbors implements Neighbors{
	private boolean toroidal;
	private boolean countDiagonalNeighbor=true; //the concept of diagonal neighbors does not exist for hexagonal grids
	private Cell[][] currentGrid;
	private String WHITE="white";

	public void initialize(boolean diagonals, boolean wrapToroidal){
		toroidal=wrapToroidal;
	}

	public void importCurrentGrid(Cell[][] myGrid){
		currentGrid=myGrid;
	}

	public double neighbors(int i, int j, boolean onlyIdentical){
		int identicalNeighbors=0;
		int rowLength=currentGrid.length;
		int colLength=rowLength;
		double neighbors=0;

		List<int[]> cells=getCellsToCheck(i, j);
		for (int c=0; c<cells.size(); c++){
			int x=cells.get(c)[0];
			int y=cells.get(c)[1];
			if (currentGrid[x][y].getTypecolor().equals(currentGrid[i][j].getTypecolor())){
				identicalNeighbors+=1.0;
			}
			if (!currentGrid[x][y].getTypecolor().equals(WHITE)){
				neighbors+=1.0;
			}
		}
		if(onlyIdentical){
			return identicalNeighbors;
		}
		return neighbors;
	}


	public void wrapToroidal(){
		return;

	}

	public boolean isOnScreen(int x,int y){
		return (x>=0&&x<currentGrid.length&&y>=0&&y<currentGrid.length);
	}

	public List<int[]> getCellsToCheck(int i, int j){
		ArrayList cells=new ArrayList<int[]>();
		int[][] potentialCells={{i, j+1},{i+1,j},{i+1, j-1},{i, j-1},{i-1, j},{i-1, j+1}};
		for (int el=0; el<potentialCells.length; i++){
			if (isOnScreen(potentialCells[el][0],potentialCells[el][1])){
				cells.add(el);
			}
		}
		
		
		return cells;
	}


}