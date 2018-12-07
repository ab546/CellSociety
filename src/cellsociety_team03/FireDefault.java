package cellsociety_team03;
	import java.util.Arrays;
	import java.util.List;
	import java.util.Map;
import java.util.HashMap;
	/**
	 * Simple immutable value object representing music data.
	 *
	 * @author Robert C. Duvall
	 */
	public class FireDefault extends Default{
		
		
		public static final int maxprobcatch=100;
		
		
	    // name in data file that will indicate it represents data for this type of object
	    public static final String DATA_TYPE = "Fire";
	    // field names expected to appear in data file holding values for this object
	    // simple way to create an immutable list
	    
	    
	    public static final List<String> DATA_FIELDS =  Arrays.asList(new String[] {
	        "probCatch",
	        "size",
	        "speed"
	       
	    });
	    public static final List<String> ALLOWED_STATES =  Arrays.asList(new String[] {
		        "burning",
		        "tree",
		        "empty"
		       
		    });
	    public static final Map<String,Integer> MAXIMUMS=getMax();
		private static Map<String, Integer> getMax()
		{
			 Map<String,Integer> myMap = new HashMap<String,Integer>();
			myMap.put("probCatch", maxprobcatch);
			myMap.put("size", maxsize);
			myMap.put("speed", maxspeed);
			return myMap;
		};
		public Map<String,Integer>getMaxes(){
			return MAXIMUMS;
		}
		public List<String>getStates(){
			return ALLOWED_STATES;
		}
	    // specific data values for this instance
	    private Map<String, String> myDataValues;
	    public FireDefault (Map<String, String> dataValues) {
	        myDataValues = dataValues;
	    }
	    
	    public FireDefault(){
	    	myDataValues=new HashMap<String,String>();
	    }
	    public void setDataValues(Map<String, String> dataValues){
	    	myDataValues=dataValues;
	    }
	   public Map<String,String> getAttributes(){
		   return myDataValues;
	   }
	    // provide alternate ways to access data values if needed
	    
	    public int getprobCatch () {
	        return Integer.parseInt(myDataValues.get(DATA_FIELDS.get(0)));
	    }
	  
	  
	    public String getType(){
	    	return DATA_TYPE;
	    }
	    public List<String>getFields(){
	    	return DATA_FIELDS;
	    }
	    
	    
	    public Cell[][] getGrid(int []params){
	    	FireCell [][]grid=new FireCell[params[1]][params[1]];
	    	if(!initialGrid){
	    	
	    	for (int i=0;i<params[1];i++){
				for(int j=0;j<params[1];j++){
					
					FireCell gridcell= new FireCell("tree");
					grid[i][j]=gridcell;
				}
				
	    	}
	    	}
	    	else{
	    		String[]initialStates=states.split(" ");
		    	
	    		for(int row=0;row<params[0];row++){
		    		for (int col=0;col<params[0];col++){
		    		grid[row][col]=new FireCell(initialStates[row*params[0]+col]);
		    		}
		    		}
	    	}
	    	
	    	return grid;
	    }
	    
	    
	    public Game getnewgame(int[] params,String shape){
	    	FireGame newgame = new FireGame(getGrid(params), params[1], true, shape, params[0]);
	    	return newgame;
	    }
	    
	    public int getsize(){
	    	 return Integer.parseInt(myDataValues.get(DATA_FIELDS.get(1)));
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