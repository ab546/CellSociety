package cellsociety_team03;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.sun.prism.paint.Color;

public class AntDefault extends Default{
	 public static final String DATA_TYPE = "Ants";
	 
	    // field names expected to appear in data file holding values for this object
	    // simple way to create an immutable list
	    public static final List<String> DATA_FIELDS =  Arrays.asList(new String[] {
	       "percentAnts",
	    	"percentObstacles",
	    	"percentFood",
	        "size",
	        "speed"
	        
	        
	    });
	    public static final List<String> ALLOWED_STATES =  Arrays.asList(new String[] {
		        "ant",
		        "food",
		        "empty",
		        "home",
		        "obstacle"
	    });
	    public List<String>getStates(){
	    	return ALLOWED_STATES;
	    }
	   
	    public static final Map<String,Integer>MAXIMUMS=getMax();
	    static Map<String, Integer> getMax(){
	    	Map<String,Integer>map=new HashMap<String,Integer>();
	    	map.put("size",40);
	    	
	    	map.put("speed", 10);
	    	return map;
	    }
	    // specific data values for this instance
	    private Map<String, String> myDataValues;
	    public AntDefault (Map<String, String> dataValues) {
	        myDataValues = dataValues;
	         
	    }
	    public Map<String,Integer> getMaxes(){
	    	return MAXIMUMS;
	    }
	    public AntDefault(){
	    	myDataValues=new HashMap<String,String>();
	 
	    }
	    public void setDataValues(Map<String, String> dataValues){
	    	myDataValues=dataValues;
	    }
	    public Map<String,String> getAttributes(){
			   return myDataValues;
		   }

	    public AntCell[][] getGrid(int[]parameters){

	    	//SegregationGrid grid=new SegregationGrid(parameters[3], parameters[1], parameters[2], parameters[0]);
	    	AntCell[][]grid=new AntCell[parameters[0]][parameters[0]];
	    	if(!initialGrid){
	    	Random r = new Random();
	    	for(int row=0;row<parameters[3];row++){
	    		for (int col=0;col<parameters[3];col++){
	    			int randomNum = r.nextInt((100 - 0) + 1) + 0; // random number between 0 and 100
					
	    			if (randomNum <= parameters[0]) {
						grid[row][col]=new AntCell("ant");
					} else if (randomNum <= parameters[0]+ parameters[1]) {
						grid[row][col]= new AntCell("obstacle");
					} else if (randomNum <= parameters[0]+ parameters[1]+parameters[2]){
						grid[row][col]= new AntCell("food");
					}
					else{
						grid[row][col]=new AntCell("empty");
					}
	    		}
	    	}
	    	}
	    	else{
	    		String[]initialStates=states.split(" ");
	    		
	    		for(int row=0;row<parameters[0];row++){
		    		for (int col=0;col<parameters[0];col++){
		    		grid[row][col]=new AntCell(initialStates[row*col+col]);
		    		}
		    		}
	    	}
	    	return grid;
	    }
	    
	    public Game getnewgame(int[] parameters,String shape){
	    	SegregationGame newgame=new AntsGame(getGrid(parameters), parameters[3],true,shape);
	    	return newgame;
	    }
	    // provide alternate ways to access data values if needed
	  
	    public int getSimilar () {
	        return Integer.parseInt(myDataValues.get(DATA_FIELDS.get(0)));
	    }
	    public int getRed () {
	        return Integer.parseInt(myDataValues.get(DATA_FIELDS.get(1)));
	    }
	    public int getBlue () {
	        return Integer.parseInt(myDataValues.get(DATA_FIELDS.get(2)));
	    }
	    public int getSize () {
	        return Integer.parseInt(myDataValues.get(DATA_FIELDS.get(3)));
	    }
	    public String getType(){
	    	return DATA_TYPE;
	    }
	    public List<String>getFields(){
	    	return DATA_FIELDS;
	    }
	    @Override
	    public String toString () {
	        StringBuilder result = new StringBuilder();
	        result.append("Default {\n");
	        for (Map.Entry<String, String> e : myDataValues.entrySet()) {
	            result.append("  "+e.getKey()+"='"+e.getValue()+"',\n");
	        }
	        result.append("}\n");
	        return result.toString();
	    }
	}

