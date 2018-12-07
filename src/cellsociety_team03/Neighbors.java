package cellsociety_team03;

import java.util.List;


public interface Neighbors {


	String DEFAULT="default";

	public void initialize(boolean countDiagonalNeighbor, boolean wrapToroidal);

	public List<Coordinate> getCellsgivenOrientation(int i, int j,int orientation);
	public void importCurrentGrid(Cell[][] currentGrid);


	public List<int[]> neighbors(int i, int j, boolean onlyIdentical, boolean getOtherType, String thatType );

	public void wrapToroidal();




}