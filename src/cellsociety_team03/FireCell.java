package cellsociety_team03;

//create connection between state in fire and color to be displayed by interface


import java.util.HashMap;
import java.util.Map;

public class FireCell extends Cell {
	private String EMPTY="empty";
	private String YELLOW="yellow";
	private String TREE="tree";
	private String GREEN="green";
	private String BURNING="burning";
	private String RED="red";

	
	public FireCell(String state){
		super(state);
		gettypes().put(EMPTY, YELLOW);
		gettypes().put(TREE, GREEN);
		gettypes().put(BURNING, RED);
	
		
	}
	
	
	
	
	

		
	
}
