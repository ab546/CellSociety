package cellsociety_team03;

import java.util.Random;

public class ConwayGame extends Game {

	private String ALIVE="alive";

	public static final boolean countDiagonalNeighbors=true;


	

	public static final String DEAD = "dead";


	
	public ConwayGame(ConwayCell[][] cellarray, int mysize, boolean istoroidal, String shape){
		super(mysize,istoroidal,shape);
		setgrid(cellarray);
		initialize(countDiagonalNeighbors);
	
	}


		
	
	
	
	
	public void step(){
		
		getneighbors().importCurrentGrid(getgrid());
		//<2 dies
		// 2 or 3 neighbor lives
		// >3 dies
		//=3 new live cell

		ConwayCell[][] newgrid = new ConwayCell[getsize()][getsize()];
		for (int i=0;i<getsize();i++){
			for (int j=0;j<getsize();j++){

				int numberlivecells= numbercellsliving(i, j);
				boolean cellstate= getgrid()[i][j].getType().equals("alive");
				String newstate = calculatestate(numberlivecells,cellstate);

				newgrid[i][j] = new ConwayCell(newstate);

			}
		}

		replacegrid(newgrid);
		
		
	}
	
	private String calculatestate(int numberlivecells, boolean currentlyalive){
		//System.out.println(numberlivecells);
		if(numberlivecells<2){
			return "dead";
		}
		if(numberlivecells==2 && !currentlyalive){
			return "dead";
		}
		if((numberlivecells==2 || numberlivecells==3) && currentlyalive){
			return "alive";
		}
		
		if((numberlivecells==3) && !currentlyalive){
			return "alive";
		}
		if(numberlivecells>=4){
			return "dead";
			
		}
		else{
			//throw exception here
			
			return null;
		}
		
	}
	
	private int numbercellsliving(int x, int y){
		int identicalNeighbors = getneighbors().neighbors(x,y,false, true, ALIVE).size();
		
		return identicalNeighbors;		
		
	}
	


}
