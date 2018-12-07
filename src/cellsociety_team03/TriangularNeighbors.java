package cellsociety_team03;
import java.util.*;

//get neighbors for a triangular cell
public class TriangularNeighbors implements Neighbors{
	private boolean toroidal;
	private boolean countDiagonalNeighbor=true; //the concept of diagonal neighbors does not exist for hexagonal grids
	private Cell[][] currentGrid;
	private String WHITE="white";	
	private boolean countDiagonalNeighbor;
	public void initialize(boolean diagonals, boolean wrapToroidal){
		toroidal=wrapToroidal;
		countDiagonalNeighbor=diagonals;
	}
	public void importCurrentGrid(Cell[][] myGrid){
		currentGrid=myGrid;
	}
	public List<int[][]> neighbors(int i, int j, boolean onlyIdentical, boolean getOtherType, String thatType){
		int identicalNeighbors=0;
		int rowLength=currentGrid.length;
		int colLength=rowLength;
		ArrayList<int[][]> identicalNeighbors=new ArrayList<int[][]>();
		ArrayList<int[][]> neighbors=new ArrayList<int[][]>();
		ArrayList<int[][]> neighborsTargetType=new ArrayList<int[][]>;
		List<int[]> cells=getCellsToCheck(i, j);
		for (int c=0; c<cells.size(); c++){
			int x=cells.get(c)[0];
			int y=cells.get(c)[1];
			if (currentGrid[x][y].getTypecolor().equals(currentGrid[i][j].getTypecolor())){
				identicalNeighbors.add({x, y});
			}
			if (currentGrid[x][y].getType().equals(thatType)){
				neighborsTargetType.add({x, y});
			}
			if (!currentGrid[x][y].getTypecolor().equals(WHITE)){
				neighbors.add({x, y});
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
	public void wrapToroidal(){
		return;
	}
	public boolean isOnScreen(int x,int y){
		return (x>=0&&x<currentGrid.length&&y>=0&&y<currentGrid.length);
	}
	public List<int[]> getCellsToCheck(int i, int j){
		ArrayList cells=new ArrayList<int[]>();
		if (!countDiagonalNeighbor){
			int[][] potentialCells={{i, j+1},{i,j-1},{i+1, j},{i-1, j},{i+2, j},{i-2, j}};
		}
		else(
			if (currentGrid[i][j].getShape.getispointingUp()){
			int[][] potentialCells={{i, j+1},{i,j-1},{i+1, j},{i-1, j},{i+2, j},{i-2, j}, {u+1, v-1}, {u+1, v+1}, {u-1, v-1}, {u-1, v+1}, { u+1, v-2}, { u+1, v+2}};
			}
			else{
			int[][] potentialCells={{i, j+1},{i,j-1},{i+1, j},{i-1, j},{i+2, j},{i-2, j}, {u+1, v-1}, {u+1, v+1}, {u-1, v-1}, {u-1, v+1}, { u-1, v-2}, { u+1, v+2}};
			}
			)
		for (int el=0; el<potentialCells.length; i++){
			if (isOnScreen(potentialCells[el][0],potentialCells[el][1])){
				cells.add(el);
			}
		}
		
		
		return cells;
	}
}