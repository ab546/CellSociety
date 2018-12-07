package cellsociety_team03;

public class ConwayGame extends Game {


	ConwayGrid myGrid;
	
	public ConwayGame(int inputSize){
		super(inputSize);
		myGrid =new ConwayGrid(inputSize);
	}
	/*
	public void initializeGame(){
		myGrid.initialize();
	
	}*/
	
	public void step(){
		myGrid.step();
	}
	
	public ConwayGrid getGrid(){
		return myGrid;
	}
}
