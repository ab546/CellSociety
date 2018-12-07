package cellsociety_team03;
import java.util.*;
import java.util.List;
import java.math.*;
public class AntsGame extends Game{
	

	
	private static final boolean countDiagonalNeighbors=true;
	
	private static final int foodplimit= 1000;
	private static final int homeplimit=1000;
	private static final double K= .001;
	private static final double N=10;
	private static final int maxantspercell=10;
	private String HOME="home";
	private String FOOD="food";
	private String DEFAULT="default";
	private String OBSTACLE="obstacle";
	public AntsGame(Cell[][] cellarray,int inputSize, boolean toroidalOrNot,String shape ){
		super(inputSize,toroidalOrNot,shape);
		replacegrid(cellarray);
		initialize(countDiagonalNeighbors);

		
	}
	
	
	
	
	
	
	private void antbehavior(AntAnimal ant,AntCell currlocation,Coordinate currcoord){
	if(ant.hasfooditem()){
		antreturntonest(ant, currlocation,currcoord);
	}
	
	
	else{
		
		antfindfoodsource(ant,currlocation,currcoord);
	}
	
	}
	
	
	
	
	
	private void antreturntonest(AntAnimal ant, AntCell currlocation,Coordinate currcoord){
		
		Coordinate x = findforwardhome(ant,currcoord);
		
		if (x!=null){
			
			currlocation.decrementant(ant);
			AntCell nextlocation =(AntCell) getgrid()[x.getx()][x.gety()];
			nextlocation.incrementant(ant);
			dropfoodpheromone(nextlocation, x);
		}
		
		
		
		if (x==null){
			x=selectlocationall(currcoord,HOME);
			
			//change orientation here
			
		}
		
		
		if (currlocation.getishome()){
		 	ant.setfooditem(false);
		}
	
		
	}
	
	private Coordinate findforwardhome(AntAnimal ant,Coordinate currcoord){
		List<Coordinate> lc =getneighbors().getCellsgivenOrientation(currcoord.getx(), currcoord.gety(), ant.getorientation());
		List<int[]> intlist = coordtointarray(lc);
		
		return randomweighter(intlist,HOME);
		
		
		
	}
	private Coordinate findforwardfood(AntAnimal ant, Coordinate currcoord){
		List<Coordinate> lc =getneighbors().getCellsgivenOrientation(currcoord.getx(), currcoord.gety(), ant.getorientation());
		List<int[]> intlist = coordtointarray(lc);
		
		return randomweighter(intlist,FOOD);
		
	}
	
	private void antfindfoodsource(AntAnimal ant, AntCell currlocation,Coordinate currcoord){
		
		
		
		
		Coordinate x=findforwardfood(ant, currcoord);
				
				
		if (x==null){
			Coordinate coord = selectlocationall(currcoord,FOOD);
			ant.fliporientation();
			AntCell ac = (AntCell) getgrid()[coord.getx()][coord.gety()];
			ac.incrementant(ant);
			currlocation.decrementant(ant);
			//change orientation here

		}
		
		if (x!=null){
			
			drophomepheromone(currlocation,currcoord);
			
			
		}
		
		if(currlocation.getisfoodsource()){
			//pickupfooditem
			ant.setfooditem(true);
		}
	}
		
		
	public Coordinate selectlocationall(Coordinate currant, String foodorhome){
		
		
		List<int[]> locset =getneighbors().neighbors(currant.getx(), currant.gety(), false,false,"default");
		
		
		for (int i =0;i<locset.size();i++){
			Coordinate currcoord= new Coordinate(locset.get(i)[0],locset.get(i)[1]);
			AntCell ac=	(AntCell) getgrid()[currcoord.getx()][currcoord.gety()];
			if(ac.getnumberants() > maxantspercell){
				locset.remove(i);
				i--;
			}
			
			if (ac.getType().equals(OBSTACLE)){
				locset.remove(i);
				i--;
			}
			}
	
		if(locset.size()==0){
			return null;
		}
		

		
		return randomweighter(locset,foodorhome);
		//select location with each location probability (K+food)^N
				
				
		
	}
	

	
	public Coordinate randomweighter(List<int[]> locset,String foodorhome) {
        int totalweight=0;
        for (int i=0;i<locset.size();i++){
        	Coordinate currcoord= new Coordinate(locset.get(i)[0],locset.get(i)[1]);
			AntCell ac=	(AntCell) getgrid()[currcoord.getx()][currcoord.gety()];
			if(foodorhome.equals(FOOD)){
			totalweight+= ac.getfoodpheromones();
			}
			else{
				totalweight+=ac.gethomepheromones();
			}
        }
        Random r = new Random();
        double newchance = totalweight * r.nextDouble();
        double weight= 0.0;
        for (int i=0;i<locset.size();i++){
        	Coordinate currcoord= new Coordinate(locset.get(i)[0],locset.get(i)[1]);
			AntCell ac=	(AntCell) getgrid()[currcoord.getx()][currcoord.gety()];
			if(foodorhome.equals(FOOD)){
			weight+= ac.getfoodpheromones();
			}
			else{
				weight+=ac.gethomepheromones();
			}
            if (weight >= newchance)
                return new Coordinate(locset.get(i)[0],locset.get(i)[1]);
        }
        return null;
    }
	
	public List<int[]> coordtointarray(List<Coordinate> locset) {
		List<int[]> intarraylist = new ArrayList<int[]>();
		for (int i=0;i<locset.size();i++){
			Coordinate s = locset.get(i);
			int[] newcoord= new int[2];
			newcoord[0]=s.getx();
			newcoord[1]=s.gety();
			intarraylist.add(newcoord);
		}
		return intarraylist;
	}
		
		
	
	
	
	private void drophomepheromone(AntCell currlocation,Coordinate locationcoordinate){
		
		
		if (currlocation.getishome()){
			
			currlocation.incrementhomepheromones(homeplimit);
		}
		else{
			
			
			List<int[]> coordlist=getneighbors().neighbors(locationcoordinate.getx(), locationcoordinate.gety(), false, false, DEFAULT);
			int max=0;
			for (int i=0;i<coordlist.size();i++){
				int[] coordinate=coordlist.get(i);
				AntCell ac = (AntCell) getgrid()[coordinate[0]][coordinate[1]];
				if (max<ac.gethomepheromones()){
					max=ac.gethomepheromones();
				}
			}
			

			int des=max-2;
			int d=des-currlocation.gethomepheromones();
			
			if(d>0){
				currlocation.incrementhomepheromones(d);
			}
		
		}
		
		
	}
	
	
	
	private void dropfoodpheromone(AntCell currlocation, Coordinate locationcoordinate){
		if (currlocation.getisfoodsource()){
			currlocation.incrementfoodpheromones(foodplimit);
		}
		
		
		else{
			
			
			List<int[]> coordlist=getneighbors().neighbors(locationcoordinate.getx(), locationcoordinate.gety(), false, false, "default");
			int max=0;
			for (int i=0;i<coordlist.size();i++){
				int[] coordinate=coordlist.get(i);
				AntCell ac = (AntCell) getgrid()[coordinate[0]][coordinate[1]];
				if (max<ac.getfoodpheromones()){
					max=ac.getfoodpheromones();
				}
			}
			

			int des=max-2;
			int d=des-currlocation.getfoodpheromones();
			
			if(d>0){
				currlocation.incrementfoodpheromones(d);
			}
		
		}
	}
	
	
	public void step(){
		
		
	}
	

}
