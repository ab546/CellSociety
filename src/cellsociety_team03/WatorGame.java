package cellsociety_team03;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
public class WatorGame extends Game{
	Neighbors n;
	private String SQUARE="square";
	private String HEXAGONAL="hexagonal";
	private String TRIANGULAR="triangular";
	private String DEFAULT="default";
	private int sharkmove;
	private int fishmove;
	private int fishpercent;
	private int sharkpercent;
	private int sharkstarves;
	private HashMap<String, Integer> timeAliveCount=new HashMap<String, Integer>();
	private HashMap<String, Integer> timeSinceLastAte=new HashMap<String, Integer>();
	private WatorCell[][] nextgrid;
	private String shape;
	private String SHARK="shark";
	private String FISH="fish";
	private String EMPTY="empty";
	private static final boolean countDiagonalNeighbors=true;
	private boolean toroidal;
	
	public WatorGame(WatorCell[][] cellarray, int inputSize, boolean istoroidal, String myShape, int inputfishmove, int inputsharkmove, int sharkstarvationturns){
		super(inputSize,istoroidal,myShape);
		
		sharkmove=inputsharkmove;
		fishmove=inputfishmove;
		sharkstarves=sharkstarvationturns;
		toroidal=istoroidal;
		shape=myShape;
		initialize();
	}
	
	
	public void initialize() {
		
		if (shape.equals(SQUARE)){
		n=new SquareNeighbors();
	}
	else if (shape.equals(HEXAGONAL)){
		n=new HexagonalNeighbors();
	}
//	else if (shape.equals(TRIANGULAR)){
//		n=new TriangularNeighbors();
//	}
	n.initialize(countDiagonalNeighbors, toroidal);
	
	for (int i=0;i<getsize();i++){
		for (int j=0;j<getsize();j++){
			timeAliveCount.put(i+","+j,0);
			timeSinceLastAte.put(i+","+j, 0); //only needed for sharks, but no harm in already initializing it for future movement of sharks
		}
	}
	}
	
	
	public void resetmoving(){
		for (int i=0;i<getsize();i++){
			for (int j=0;j<getsize();j++){
				WatorCell currcell = (WatorCell) getgrid()[i][j];
				if(currcell.isanimal()){
					currcell.setmove(false);
				}
			}
		}
	}
	
	private WatorCell[][] gridcopy(){
		WatorCell[][] newgrid= new WatorCell[getsize()][getsize()];
		for (int i=0;i<getsize();i++){
			for(int j=0;j<getsize();j++){
				WatorCell currcell =(WatorCell) getgrid()[i][j];
				String celltype = currcell.getType();
				WatorCell cellcopy;
				if(currcell.isanimal()){
					cellcopy = new WatorCell(celltype, currcell.hasmoved());
				}else{
					cellcopy = new WatorCell(celltype);
				}
				newgrid[i][j]=cellcopy;
			}
		}
		return newgrid;
	}
	
	
	public void migrate(int i,int j,WatorCell animal){
		if(animal.hasmoved()){
			return;
		}
		timeAliveCount.put(i+","+j, timeAliveCount.get(i+","+j)+1);
		if (animal.getType().equals(SHARK)){
			timeSinceLastAte.put(i+","+j,timeSinceLastAte.get(i+","+j)+1);
		}
		if (sharkIsDead(i, j)){
			return;
		}
		if (sharkEatsFish(i, j, animal)){
			moveAnimal(i, j, animal);
			return;
		}
		int[] newCordinatesAfterMoving=moveAnimal(i, j, animal);
		int newI=newCordinatesAfterMoving[0];
		int newJ=newCordinatesAfterMoving[1];
		if (reproduce(newI, newJ, animal)){
			return;
		}
		animal.setmove(true);
	}
	public int[] moveAnimal(int i, int j, WatorCell animal){
		int timeCellAlive=timeAliveCount.get(i+","+j);
		List<int[]> coordinates = getavailablecells(i,j, EMPTY);
		int[] chosencoordinate=new int[2];
		if(coordinates.size()>0){
			Random rand = new Random();
			int chancecalc = (int) Math.floor(coordinates.size() *rand.nextDouble());
			chosencoordinate = coordinates.get(chancecalc);
			nextgrid[chosencoordinate[0]][chosencoordinate[1]] = animal;
			timeAliveCount.put(chosencoordinate[0]+","+chosencoordinate[1], timeCellAlive);
			nextgrid[i][j]=new WatorCell(EMPTY);
			timeAliveCount.put(i+","+j, 0);
		}
		if (animal.getType().equals(SHARK)){
			timeSinceLastAte.put(chosencoordinate[0]+","+chosencoordinate[1],timeSinceLastAte.get(i+","+j));
		}
		return chosencoordinate;
	}
	public List<int[]> getavailablecells(int i, int j, String typeOfCellsNeeded){
		List<int[]> coordinates = new ArrayList<int[]>();
		if(i>0 && nextgrid[i-1][j].getType().equals(typeOfCellsNeeded)){
			coordinates.add(coordinateformat(i-1,j));
		}
		if(j>0 && nextgrid[i][j-1].getType().equals(typeOfCellsNeeded)){
			coordinates.add(coordinateformat(i,j-1));
		}
		if(i<getsize()-1 && nextgrid[i+1][j].getType().equals(typeOfCellsNeeded)){
			coordinates.add(coordinateformat(i+1,j));
		}
		if(j<getsize()-1 && nextgrid[i][j+1].getType().equals(typeOfCellsNeeded)){
			coordinates.add(coordinateformat(i,j+1));
		}
		return coordinates;
	}
	public int[] coordinateformat(int i,int j){
		int[] coordinates = new int[2];
		coordinates[0]=i;
		coordinates[1]=j;
		return coordinates;
	}
	public boolean sharkEatsFish(int i, int j, WatorCell sharkcell){
		if (!sharkcell.getType().equals(SHARK)){
			return false;
		}
		List<int[]> coordinates = getavailablecells(i,j, FISH);
		if(coordinates.size()>0){
			Random rand = new Random();
			int chancecalc = (int) Math.floor(coordinates.size() *rand.nextDouble());
			int[] chosencoordinate = coordinates.get(chancecalc);
			nextgrid[chosencoordinate[0]][chosencoordinate[1]] = new WatorCell(EMPTY);
			timeSinceLastAte.put(i+","+j, 0);
			timeAliveCount.put(i+","+j, 0);
			timeAliveCount.put(chosencoordinate[0]+","+chosencoordinate[1],0);
			return true;
		}
		return false;
	}
	public boolean reproduce(int i, int j,WatorCell animal){
		int timeAnimalAlive=timeAliveCount.get(i+","+j);
		boolean animalReproduced=(getgrid()[i][j].getType().equals(SHARK)&&timeAnimalAlive>=sharkmove)||(getgrid()[i][j].getType().equals(FISH)&&timeAnimalAlive>=fishmove);
		if (animalReproduced){
			List<int[]> coordinates = getavailablecells(i,j, EMPTY);
			if(coordinates.size()>0){
				Random rand = new Random();
				int chancecalc = (int) Math.floor(coordinates.size() *rand.nextDouble());
				int[] chosencoordinate = coordinates.get(chancecalc);
				nextgrid[chosencoordinate[0]][chosencoordinate[1]] = new WatorCell(animal.getType());
				timeAliveCount.put(chosencoordinate[0]+","+chosencoordinate[1], 0);
				timeAliveCount.put(i+","+j, 0);
			}
		}
		animal.setmove(true);
		return animalReproduced;
	}
	public boolean sharkIsDead(int i, int j){
		boolean sharkDies=false;
		if (getgrid()[i][j].getType().equals(SHARK)){
			sharkDies=timeSinceLastAte.get(i+","+j)>=sharkstarves;
			if (sharkDies){
				nextgrid[i][j]=new WatorCell(EMPTY);
				timeAliveCount.put(i+","+j,0);
				timeSinceLastAte.put(i+","+j,0);
				sharkDies=true;
			}
		}
		return sharkDies;
	}
	
	public void step(){
		
		nextgrid = (WatorCell[][]) getgrid();
		for (int i=0;i<getsize();i++){
			for(int j=0;j<getsize();j++){
				WatorCell currcell= nextgrid[i][j];
				if(currcell.isanimal() ){
					migrate(i,j,currcell);
				}
			}
		}
		replacegrid(nextgrid);
		resetmoving();	
		
		
	}
}