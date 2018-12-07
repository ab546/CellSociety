package cellsociety_team03;

import java.util.Random;

public class ConwayGrid extends Grid {

	public static final String DEAD = "dead";

	/*
	int getsize();
	private Cell[][] getgrid();
	*/
	
	public ConwayGrid(int mysize){
		super(mysize);
		
	
	}
	
	public void initialize(){
		Random r= new Random();
		
		for (int i=0;i<getsize();i++){
			for(int j=0;j<getsize();j++){
			float s = 2*r.nextFloat();
			ConwayCell mycell;
			if (s>=0 &&s<1){
			mycell= new ConwayCell("alive");
			getgrid()[i][j]=mycell;
			
		}
		if(s>=1 && s<2){
			mycell=new ConwayCell("dead");
			getgrid()[i][j]=mycell;
		}
		}
			
		}
		
		
	}
	
	public void initialize(int rs){
		
		for (int i=0;i<getsize();i++){
			for(int j=0;j<getsize();j++){
				

				Cell gridcell= new ConwayCell(DEAD);

				getgrid()[i][j]=gridcell;
			}
			
			
		}
		Cell mycell = new ConwayCell("alive");
		getgrid()[1][2] = mycell;
		//System.out.println(getgrid()[0][1].getTypecolor());
		Cell mycelltwo= new ConwayCell("alive");
		getgrid()[2][2]=mycelltwo;
		Cell mycellthree=new ConwayCell("alive");
		getgrid()[3][2] = mycellthree;
		mycell= new ConwayCell("alive");
		getgrid()[2][3] = mycell;
		mycell= new ConwayCell("alive");
		getgrid()[3][3] = mycell;
		mycell= new ConwayCell("alive");
		getgrid()[4][3] = mycell;
		
		/*Cell mycellfour = new ConwayCell("alive");
		getgrid()[2][2] = mycellfour;
		Cell mycellfive = new ConwayCell("alive");
		getgrid()[3][3] = mycellfour;
		Cell mycellsix= new ConwayCell("alive");
		getgrid()[3][4] = mycellsix;
		//Cell mycellseven = new ConwayCell("alive");
		//getgrid()[4][4] = mycellseven;
		//Cell mycelleight = new ConwayCell("alive");
		//getgrid()[4][3] = mycelleight;*/
		
		
		
	}
	
	
	public void step(){
		
		
		//<2 dies
		// 2 or 3 neighbor lives
		// >3 dies
		//=3 new live cell
		//System.out.println("heap");
		ConwayCell[][] newgrid = new ConwayCell[getsize()][getsize()];
		for (int i=0;i<getsize();i++){
		for (int j=0;j<getsize();j++){
		
		int numberlivecells= numbercellsliving(i, j);
		//System.out.println(i + "," + j);
		boolean cellstate= getgrid()[i][j].getType().equals("alive");
		//System.out.println(cellstate);
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
			System.out.println("null");
			return null;
		}
		
	}
	private int numbercellsliving(int x, int y){
		int total=0;
	
		if( y<getsize()-1 &&getgrid()[x][y+1].getType().equals("alive")){
			total+=1;
			
		}
		
		if(y >0 &&getgrid()[x][y-1].getType().equals("alive")){
			total+=1;
		}
		
		
		if( x<getsize()-1 && getgrid()[x+1][y].getType().equals("alive")){
			total+=1;
		}
		
		if( x>0 &&getgrid()[x-1][y].getType().equals("alive")){
			total+=1;
		}
		
		if( x>0 && y>0 &&getgrid()[x-1][y-1].getType().equals("alive")){
			total+=1;
		}
		if( x>0 && y<getsize()-1 &&getgrid()[x-1][y+1].getType().equals("alive")){
			total+=1;
		}
		
		if( x<getsize()-1 && y<getsize()-1 &&getgrid()[x+1][y+1].getType().equals("alive")){
			total+=1;
		}
		if( x<getsize()-1 && y>0 &&getgrid()[x+1][y-1].getType().equals("alive")){
			total+=1;
		}		
		
		
		//System.out.println(total);
		return total;
		
		
		
		
		
		
	}
	

	
	
}
