package cellsociety_team03;

import java.util.HashMap;
import java.util.Map;
import java.util.*;

import javafx.scene.shape.*;


public abstract class Cell {
	private String currentType;
	private Map<String,String> types = new TreeMap<String,String>();
	
	
	

	private CellShape currshape;

	
	
	public void updateState(){
		ArrayList<String> currtypes =new ArrayList<String>(types.keySet());
		int currindex=currtypes.indexOf(currentType);
		int updateindex=(currindex+1)%currtypes.size();
		currentType=currtypes.get(updateindex);
	
	}
	
	
	
	
	public Cell(String state){
		
		currentType = state;
	}
public String getType(){
	
	return currentType;
}
	
public String getTypecolor(){
	
	return types.get(currentType);
}


public Map<String,String> gettypes(){
	
	
	return types;
}

public void setShape(CellShape cs){
	currshape=cs;
}
}

	

	







