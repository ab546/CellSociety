package cellsociety_team03;

import java.util.Random;

//fire game simulation

public class FireGame extends Game {
	private static final String TREE = "tree";
	private static final String BURNING = "burning";
	private int probCatch;
	private boolean countDiagonalNeighbors=false;




	public FireGame(Cell[][] inputGrid, int inputSize, boolean toroidalOrNot, String shape, int inputprobCatch){
		super(inputSize,toroidalOrNot,shape);
		probCatch= inputprobCatch;

		initialize(countDiagonalNeighbors);

	}
	
	
	
	private String calculatestate(String cellstate, boolean surroundingfirepresent){
		if(cellstate.equals("burning")){
			return "empty";
		}
		if(cellstate.equals("tree") && surroundingfirepresent){
			Random rand = new Random();
			double chancecalc= 100* rand.nextDouble();
			if(chancecalc<probCatch ){
			return "burning";
			}
			else{
				return "tree";
			}
		}
		if(cellstate.equals("tree") && !surroundingfirepresent){
			return "tree";
		}
		if(cellstate.equals("empty")){
			return "empty";
		}	
		return null;
	}

	public void step(){
		getneighbors().importCurrentGrid(getgrid());
		FireCell[][] newgrid = new FireCell[getsize()][getsize()];
		for (int i=0;i<getsize();i++){
			for (int j=0;j<getsize();j++){
				String cellstate= getgrid()[i][j].getType();
				boolean surroundingfirepresent = findfire(i,j);
				String newstate = calculatestate(cellstate,surroundingfirepresent);
				newgrid[i][j] = new FireCell(newstate);

			}
		}
		
		replacegrid(newgrid);
		
	}

	private boolean findfire(int i, int j){
		return(((i>0||i<getsize()-1) && (j>0||j<getsize()-1) && getneighbors().neighbors(i,j,false, true, BURNING).size()>0));
	}

	
}