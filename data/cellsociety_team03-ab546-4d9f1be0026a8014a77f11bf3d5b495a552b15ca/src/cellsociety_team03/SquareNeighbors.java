package cellsociety_team03;


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

	public double neighbors(int i, int j, boolean onlyIdentical){
		int identicalNeighbors=0;
		int rowLength=currentGrid.length;
		int colLength=rowLength;
		double neighbors=0;
		for (int x = Math.max(0, i - 1); x <= Math.min(i + 1, rowLength - 1); x++) {
			for (int y = Math.max(0, j - 1); y <= Math.min(j + 1, colLength - 1); y++) {
				if (!countDiagonalNeighbor&&!((x==i-1&&y==j-1)||(x==i-1&&y==j+1)||(x==i+1&&y==j-1)||(x==i+1&&y==j+1))){
										if ((x != i || y != j) &&   (currentGrid[x][y].getTypecolor().equals(currentGrid[i][j].getTypecolor()))) {
					identicalNeighbors += 1.0;
				}
				if ((x != i || y != j) && !currentGrid[x][y].getTypecolor().equals(WHITE)) {
					neighbors += 1.0;
				}
					}
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


}