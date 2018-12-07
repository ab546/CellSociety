package cellsociety_team03;

public interface Neighbors {


	public void initialize(boolean countDiagonalNeighbor, boolean wrapToroidal);


	public void importCurrentGrid(Cell[][] currentGrid);

	public double neighbors(int i, int j, boolean onlyIdentical);

	public void wrapToroidal();




}