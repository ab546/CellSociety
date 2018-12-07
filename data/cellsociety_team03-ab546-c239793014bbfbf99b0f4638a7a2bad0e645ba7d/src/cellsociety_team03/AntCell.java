package cellsociety_team03;

public class AntCell extends Cell{
	
	
	private int homepheromones;
	private int foodpheromones;
	private int numberants;
	private boolean foodsource;
	private boolean ishome;
	
	public AntCell(String state){
		super(state);
		gettypes().put("ant", "black");
		gettypes().put("food", "yellow");
		gettypes().put("empty", "green");
		gettypes().put("home", "red");
		homepheromones=0;
		foodpheromones=0;
		
		
		if(state.equals("home")){
			ishome=true;
		}
		
		if(state.equals("food")){
			foodsource=true;
		}
		
	}
	
	
	public void incrementant(){
		numberants++;
	}
	public void decrementant(){
		numberants--;
	}
	
	
	public void incrementfoodpheromones(int x){
		foodpheromones+=x;
	}
	
	public void incrementhomepheromones(int x){
		homepheromones+=x;
	}
	
	public int getfoodpheromones(){
		return foodpheromones;
	}
	public int gethomepheromones(){
		return homepheromones;
	}
	public int getnumberants(){
		return numberants;
	}
	}
	
	
	

}
