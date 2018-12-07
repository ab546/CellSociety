package cellsociety_team03;
import java.util.*;
public class AntCell extends Cell{
	
	
	private int homepheromones=100;
	private int foodpheromones=100;
	private List<AntAnimal> ants;
	private boolean foodsource;
	private boolean ishome;
	private int myfoodpmax;
	private int myhomepmax;
	private String ANT="ant";
	private String BLACK="black";
	private String EMPTY="empty";
	private String GREEN="green";
	private String HOME="home";
	private String PURPLE="purple";
	private String OBSTACLE="obstacle";
	private String FOOD="food";
	private String YELLOW="yellow";
	private String RED="red";
	
	
	public AntCell(String state){
		super(state);
		gettypes().put(ANT, BLACK);
		gettypes().put(FOOD, YELLOW);
		gettypes().put(EMPTY, GREEN);
		gettypes().put(HOME, RED);
		gettypes().put(OBSTACLE, PURPLE);
		homepheromones=0;
		foodpheromones=0;
	
		
		
		if(state.equals(HOME)){
			ishome=true;
		}
		
		if(state.equals(FOOD)){
			foodsource=true;
		}
		
	}
	
	
	public void incrementant(AntAnimal ant){
		ants.add(ant);
	}
	public void decrementant(AntAnimal ant){
		ants.remove(ant);
	}
	public boolean getisfoodsource(){
		return foodsource;
	}
	
	public void incrementfoodpheromones(int x){
		foodpheromones+=x;
		
		if (foodpheromones> myfoodpmax){
			foodpheromones=myfoodpmax;
		}
		
	}
	
	public void incrementhomepheromones(int x){
		homepheromones+=x;
		if(homepheromones>myhomepmax){
			homepheromones=myhomepmax;
		}
	}
	
	public int getfoodpheromones(){
		return foodpheromones;
	}
	public int gethomepheromones(){
		return homepheromones;
	}
	public int getnumberants(){
		return ants.size();
	}
	
	
	public boolean getishome(){
		return ishome;
	}
	
	
	}
	
	
	


