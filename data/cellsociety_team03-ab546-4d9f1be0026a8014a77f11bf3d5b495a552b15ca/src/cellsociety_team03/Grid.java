package cellsociety_team03;

import java.util.Random;

public abstract class Grid {
	private int size;
	private Cell[][] myGrid;
	
	public Grid(int mysize){
		size=mysize;
		myGrid= new Cell[size][size];
	}
	
	
	

	public int getsize(){
		return size;
	}
	public Cell[][] getgrid(){
		return myGrid;
	}
	
	public void replacegrid(Cell[][] newgrid){
		myGrid= newgrid;
	}
	public void initialize(Cell[][] cellarray){
		replacegrid(cellarray);
	}
	
	public abstract void step();
}
	


