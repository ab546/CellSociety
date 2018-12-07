package cellsociety_team03;

public class SlimeGame extends Game{
public static final double DROP_RATE=2;
public static final double DIFFUSE_RATE=1;	
public static final double 	EVAP_RATE=.9;
private int sniffThreshold;
private int sniffAngle;
private int WiggleAngle;
private int WiggleBias;
private boolean toroidal;
private String cellShape;
private Neighbors n;
private SlimeCell[][]myGrid;
public SlimeGame(int inputSize,int sniffT,int sniffA,int wiggleA,int WiggleB,boolean toroidalOrNot,String shape){
	super(inputSize);
	replacegrid(new SlimeCell[getsize()][getsize()]);
	toroidal=toroidalOrNot;
	cellShape=shape;
	initialize();
}
private void initialize(){
	if (cellShape.equals(SQUARE)){
		n=new SquareNeighbors();
	}
	else if (cellShape.equals(HEXAGONAL)){
		n=new HexagonalNeighbors();
	}
	//else if(cellShape.equals(TRIANGULAR))){
		//n=new TriangularNeighbors();
	//}
}
public void step(){
	evaluateTurtles();
	evauluateSlime();
}
private void evaulateTurtles(){
	for(int i=0;i<getsize();i++){
		for(int j=0;j<getsize();j++){
			if (myGrid[i][j].hasTurtle()){
				myGrid[i][j].getTurtle().move(i,j);
			}
		}
	}
}

}
