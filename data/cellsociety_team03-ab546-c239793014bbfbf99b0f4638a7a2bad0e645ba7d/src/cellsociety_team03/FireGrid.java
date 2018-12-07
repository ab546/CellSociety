package cellsociety_team03;

import java.util.Random;

public class FireGrid extends Grid{

	public static final String TREE = "tree";
	public static final String BURNING = "burning";

	int probCatch;

	
	
	public FireGrid(int mysize,int inputprobcatch){
		super(mysize);
		
		probCatch= inputprobcatch;
	
	}
	public void initialize(){
		for (int i=0;i<getsize();i++){
			for(int j=0;j<getsize();j++){
				
				Cell gridcell= new FireCell("tree");
				getgrid()[i][j]=gridcell;
			}
			
			
		}
		
		
		
		//getgrid()[3][3]=new FireCell("burning");
		
		
	}
	public void initialize(int rs){
		
		for (int i=0;i<getsize();i++){
			for(int j=0;j<getsize();j++){
				

				Cell gridcell= new FireCell(TREE);

				getgrid()[i][j]=gridcell;
			}
			
			
		}
		
		
		

		getgrid()[3][3]=new FireCell(BURNING);

		/*
		Cell mycell = new FireCell("alive");
		getgrid()[1][2] = mycell;
		//System.out.println(getgrid()[0][1].getTypecolor());
		Cell mycelltwo= new FireCell("alive");
		getgrid()[2][2]=mycelltwo;
		Cell mycellthree=new FireCell("alive");
		getgrid()[3][2] = mycellthree;
		mycell= new FireCell("alive");
		getgrid()[2][3] = mycell;
		mycell= new FireCell("alive");
		getgrid()[3][3] = mycell;
		mycell= new FireCell("alive");
		getgrid()[4][3] = mycell;
		
		/*Cell mycellfour = new FireCell("alive");
		getgrid()[2][2] = mycellfour;
		Cell mycellfive = new FireCell("alive");
		getgrid()[3][3] = mycellfour;
		Cell mycellsix= new FireCell("alive");
		getgrid()[3][4] = mycellsix;
		//Cell mycellseven = new FireCell("alive");
		//getgrid()[4][4] = mycellseven;
		//Cell mycelleight = new FireCell("alive");
		//getgrid()[4][3] = mycelleight;*/
		
		
		
	}
	
	
	public void step(){
		
		
		FireCell[][] newgrid = new FireCell[getsize()][getsize()];
		for (int i=0;i<getsize();i++){
		for (int j=0;j<getsize();j++){
		

		String cellstate= getgrid()[i][j].getType();
		//System.out.println(cellstate);
		boolean surroundingfirepresent = findfire(i,j);
		String newstate = calculatestate(cellstate,surroundingfirepresent);
		
		newgrid[i][j] = new FireCell(newstate);
		
		}
		}
		
		replacegrid(newgrid);
		
		
	}
	
	private boolean findfire(int i, int j){
		return((i>0 && getgrid()[i-1][j].getType().equals("burning")) ||
				(j>0 && getgrid()[i][j-1].getType().equals("burning")) ||
				(i<getsize()-1 && getgrid()[i+1][j].getType().equals("burning"))||
				(j<getsize()-1 && getgrid()[i][j+1].getType().equals("burning")));
		
		
		
	}
	
	private String calculatestate(String cellstate, boolean surroundingfirepresent){
		//System.out.println(numberlivecells);
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
	
	
	public Cell[][] getGrid(){
		
		return getgrid();
	}
	
	
	
	
	
	
}
