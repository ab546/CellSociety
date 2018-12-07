package cellsociety_team03;

public class AntAnimal {

	private boolean hasfooditem;
	private int orientation;
	
	
	
	
	public void setfooditem(boolean tf){
		hasfooditem=tf;
	}
	
	public void fliporientation(){
		
		
		orientation = (orientation +2 )%4;
	}
	
	
	public int getorientation(){
		return orientation;
	}
	
	
	public boolean hasfooditem(){
		return hasfooditem;
	}
	
	
	
	
}
