package cellsociety_team03;
	import java.util.Arrays;
	import java.util.List;
	import java.util.Map;
import java.util.Random;
import java.util.HashMap;
	/**
	 * Simple immutable value object representing music data.
	 *
	 * @author Robert C. Duvall
	 */
	public class ConwayDefault extends Default{
	    // name in data file that will indicate it represents data for this type of object
	    public static final String DATA_TYPE = "Conway";
	    // field names expected to appear in data file holding values for this object
	    // simple way to create an immutable list
	    public static final List<String> DATA_FIELDS =  Arrays.asList(new String[] {
	    		"size",
	        "speed"
	        
	    });
	    public static final List<String> ALLOWED_STATES =  Arrays.asList(new String[] {
		        "alive",
		        "dead"});
		       
	    public static final Map<String,Integer> MAXIMUMS=getMax();
		private static Map<String, Integer> getMax()
		{
			 Map<String,Integer> myMap = new HashMap<String,Integer>();
			myMap.put("size", 40);
			myMap.put("speed", 10);
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
	    public ConwayDefault (Map<String, String> dataValues) {
	        myDataValues = dataValues;
	    }
	    
	    public ConwayDefault(){
	    	myDataValues=new HashMap<String,String>();
	    }
	    public void setDataValues(Map<String, String> dataValues){
	    	myDataValues=dataValues;
	    }
	    public Map<String,String> getAttributes(){
			   return myDataValues;
		   }
	    // provide alternate ways to access data values if needed
	    
	    
	    public int getSize () {
	        return Integer.parseInt(myDataValues.get(DATA_FIELDS.get(3)));
	    }
	    public String getType(){
	    	return DATA_TYPE;
	    }
	    public List<String>getFields(){
	    	return DATA_FIELDS;
	    }
	    public ConwayCell[][] getGrid(int[]params){
	    	ConwayCell[][]grid=new ConwayCell[params[0]][params[0]];
	    	if(!initialGrid){
	    	Random r = new Random();
	    	for (int i=0;i<params[0];i++){
				for(int j=0;j<params[0];j++){
				float s = 2*r.nextFloat();
				ConwayCell mycell;
				if (s>=0 &&s<1){
				mycell= new ConwayCell("alive");
				grid[i][j]=mycell;
				
			}
			if(s>=1 && s<2){
				mycell=new ConwayCell("dead");
				grid[i][j]=mycell;
			}
			}
				
			}
	    	}
	    	else{
String[]initialStates=states.split(" ");
	    	
	    		for(int row=0;row<params[0];row++){
		    		for (int col=0;col<params[0];col++){
		    		grid[row][col]=new ConwayCell(initialStates[row*params[0]+col]);
		    		}
		    		}
	    	}
	    	return grid;
	    }
	    
	    public Game getnewgame(int[]params,String shape){
	    	ConwayGame startgame= new ConwayGame(getGrid(params),params[0],false,shape);
	    	
	    	return startgame;
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