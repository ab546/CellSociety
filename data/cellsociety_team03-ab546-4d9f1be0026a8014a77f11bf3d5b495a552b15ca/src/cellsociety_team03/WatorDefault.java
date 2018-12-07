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
	public class WatorDefault extends Default{
	    // name in data file that will indicate it represents data for this type of object
	    public static final String DATA_TYPE = "Wator";
	    // field names expected to appear in data file holding values for this object
	    // simple way to create an immutable list
	    public static final List<String> DATA_FIELDS =  Arrays.asList(new String[] {
	        "fishTurns",
	        "sharkTurns",
	        "sharkstarves",
	        
	      "fishPercent",
	      "sharkPercent",
	      "size",
	      "speed"
	    });
	    public static final List<String> ALLOWED_STATES =  Arrays.asList(new String[] {
		        "shark",
		        "fish",
		        "empty"});
		  public List<String>getStates(){
			  return ALLOWED_STATES;
		  }
	    
	    public Cell[][] getGrid(int[]params){
	    	WatorCell[][] grid=new WatorCell[params[5]] [params[5]];
	    	if(!initialGrid){
	    	Random r = new Random();
			for (int i=0;i<params[5];i++){
				for (int j=0;j<params[5];j++){
					int randomNum = r.nextInt((100 - 0) + 1) + 0; // random number between 0 and 100
					if (randomNum<params[3]){
						//System.out.println(randomNum+"   "+fishpercent);
						grid[i][j] = new WatorCell("fish");
					}
					else if (randomNum<params[3]+params[4]){
						grid[i][j] = new WatorCell("shark");
					}
					else {
						grid[i][j] = new WatorCell("empty");
					}
					



				}
			}
	    	}
	    	else{
	    		String[]initialStates=states.split(" ");
		    	
	    		for(int row=0;row<params[0];row++){
		    		for (int col=0;col<params[0];col++){
		    		grid[row][col]=new WatorCell(initialStates[row*params[0]+col]);
		    		}
		    		}
	    	}
			return grid;
	    }
	    public Game getnewgame(int[] params,String shape){
	    	
	    	WatorGame newgame=new WatorGame(params[5],params[0],params[1],params[2],params[3],params[4],shape);
	    	return newgame;
	    }
	    
	    
	    public static final Map<String,Integer>MAXIMUMS=getMax();
	    static Map<String, Integer> getMax(){
	    	Map<String,Integer>map=new HashMap<String,Integer>();
	    	map.put("size",40);
	    	map.put("fishTurns",10);
	    	map.put("sharkTurns", 10);
	    	map.put("speed", 10);
	    	map.put("fishPercent", 100);
	    	map.put("sharkPercent", 100);
	    	map.put("sharkstarves", 10);
	    	return map;
	    }
	    public Map<String,Integer>getMaxes(){
	    	return MAXIMUMS;
	    }
	    // specific data values for this instance
	    private Map<String, String> myDataValues;
	    public WatorDefault (Map<String, String> dataValues) {
	        myDataValues = dataValues;
	    }
	    
	    public WatorDefault(){
	    	myDataValues=new HashMap<String,String>();
	    }
	    public void setDataValues(Map<String, String> dataValues){
	    	myDataValues=dataValues;
	    }
	    
	    // provide alternate ways to access data values if needed
	   /*
	    public int getFishTurns () {
	        return Integer.parseInt(myDataValues.get(DATA_FIELDS.get(0)));
	    }
	    public int getSharkTurns () {
	        return Integer.parseInt(myDataValues.get(DATA_FIELDS.get(1)));
	    }
	    public int getSize () {
	        return Integer.parseInt(myDataValues.get(DATA_FIELDS.get(3)));
	    }
	    public int getsharkStarves(){
	    	return Integer.parseInt(myDataValues.get(DATA_FIELDS.get(2)));
	    }*/
	    
	   
	    public String getType(){
	    	return DATA_TYPE;
	    }
	    public List<String>getFields(){
	    	return DATA_FIELDS;
	    }

	    public Map<String,String> getAttributes(){
			   return myDataValues;
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
