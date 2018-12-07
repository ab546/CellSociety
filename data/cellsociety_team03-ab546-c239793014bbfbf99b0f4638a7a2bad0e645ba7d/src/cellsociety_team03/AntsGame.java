package cellsociety_team03;

public class AntsGame extends Game {
	
	
	
	public AntsGame(){
		
	}
	
	
	antbehavior(){
	if(hasfooditem){
		antreturntonest;
	}
	
	
	else{
		
		antfindfoodsource
	}
	
	}
	
	
	
	
	
	antreturntonest(){
		
		movetoforwardlocationwithmaxhomepheremone()
		if movedforward, dropfoodpheromone
		
		
		
		
		if forwardlocationsnull
			movetoneighborlocation
			else
				move to random neighborlocation
				
				
		if atnest
		 	dropfood
				
	
		
	}
	
	
	
	antfindfoodsource(){
		
		x=forwardlocationwithmostfoodphereomone
		if (x==null){
			movetoneighborlocationwithmostfoodphereomone
		}
		
		if (x!=null){
			drophomephereomone
			
			
		}
		
		if(atfoodsource){
			pickupfooditem
			hasfooditem=true;
		}
		
		
	selectlocation(locset){
		
		
		
		locset-obstacles-ants
		if locset=null,return null
				
				
				
		
	}
	}
	
	
	
	public void step(){
		
		
	}
	

}
