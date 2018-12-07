package cellsociety_team03;
	import java.util.Arrays;
	import java.util.List;
	import java.util.Map;
import java.util.Random;

import com.sun.prism.paint.Color;
import java.util.HashMap;
	/**
	 * Simple immutable value object representing music data.
	 *
	 * @author Robert C. Duvall
	 */
	public class SegregationDefault extends Default{
	    // name in data file that will indicate it represents data for this type of object
		 public static final String DATA_TYPE = "Segregation";
		 
	    // field names expected to appear in data file holding values for this object
	    // simple way to create an immutable list
	    public static final List<String> DATA_FIELDS =  Arrays.asList(new String[] {
	        "percentSimilar",
	        "percentRed",
	        "percentBlue",
	        "size",
	        "speed"
	        
	        
	    });
	    public static final List<String> ALLOWED_STATES =  Arrays.asList(new String[] {
		        "red",
		        "blue",
		        "empty"
	    });
	    public List<String>getStates(){
	    	return ALLOWED_STATES;
	    }
	    public static final Map<String,Color>COLOR_CHOCIE=getColors();
	    static Map<String, Color> getColors(){
	    	Map<String,Color>map=new HashMap<String,Color>();
	    	map.put("blue",Color.BLUE);
	    	map.put("red",Color.RED);
	    	map.put("white", Color.WHITE);
	    	return map;
	    }
	    public static final Map<String,Integer>MAXIMUMS=getMax();
	    static Map<String, Integer> getMax(){
	    	Map<String,Integer>map=new HashMap<String,Integer>();
	    	map.put("size",40);
	    	map.put("percentBlue",100);
	    	map.put("percentRed", 100);
	    	map.put("percentSimilar", 100);
	    	map.put("speed", 10);
	    	return map;
	    }
	    // specific data values for this instance
	    private Map<String, String> myDataValues;
	    public SegregationDefault (Map<String, String> dataValues) {
	        myDataValues = dataValues;
	         
	    }
	    public Map<String,Integer> getMaxes(){
	    	return MAXIMUMS;
	    }
	    public SegregationDefault(){
	    	myDataValues=new HashMap<String,String>();
	 
	    }
	    public void setDataValues(Map<String, String> dataValues){
	    	myDataValues=dataValues;
	    }
	    public Map<String,String> getAttributes(){
			   return myDataValues;
		   }

	    public SegregationCell[][] getGrid(int[]parameters){

	    	//SegregationGrid grid=new SegregationGrid(parameters[3], parameters[1], parameters[2], parameters[0]);
	    	SegregationCell[][]grid=new SegregationCell[parameters[3]][parameters[3]];
	    	if(!initialGrid){
	    	Random r = new Random();
	    	for(int row=0;row<parameters[3];row++){
	    		for (int col=0;col<parameters[3];col++){
	    			int randomNum = r.nextInt((100 - 0) + 1) + 0; // random number between 0 and 100
					
	    			if (randomNum <= parameters[1]) {
						grid[row][col]=new SegregationCell("red");
					} else if (randomNum <= parameters[1]+ parameters[2]) {
						grid[row][col]= new SegregationCell("blue");
					} else {
						grid[row][col]= new SegregationCell("empty");
					}
	    		}
	    	}
	    	}
	    	else{
	    		String[]initialStates=states.split(" ");
	    		
	    		for(int row=0;row<parameters[3];row++){
		    		for (int col=0;col<parameters[3];col++){
		    		grid[row][col]=new SegregationCell(initialStates[row*col+col]);
		    		}
		    		}
	    	}
	    	return grid;
	    }
	    
	    public Game getnewgame(int[] parameters,String shape){
	    	SegregationGame newgame=new SegregationGame(parameters[3], getGrid(parameters), parameters[0],true,shape);
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