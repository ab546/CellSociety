package cellsociety_team03;

import java.util.HashMap;
import java.util.Map;

public class FireCell extends Cell {
	

	
	public FireCell(String state){
		super(state);
		gettypes().put("empty","yellow");
		gettypes().put("tree", "green");
		gettypes().put("burning", "red");
	
		
	}
	
	
	
	
	

		
	
}
