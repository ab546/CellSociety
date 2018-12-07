package cellsociety_team03;
import java.util.HashMap;
import java.util.Map;
public class WatorCellBackup extends Cell{
	private int moveamount;
	private boolean isanimal;
	private boolean hasmoved;
	
	public WatorCellBackup(String state){
		super(state);
		initializecell(state);
		
	}
	
	
	public WatorCellBackup(String state, boolean moved){
		super(state);
		initializecell(state);
		hasmoved=moved;
		
	}
	
	
	
	
	public void initializecell(String state){
		
		gettypes().put("fish","green");
		gettypes().put("shark", "red");
		gettypes().put("empty", "blue");
	
		
		isanimal = (state.equals("shark") || state.equals("fish"));
		
		
		
		
	}
	
	
	public void increasemove(){
		
		moveamount=moveamount+1;
		
	}
	
	public void resetmove(){
		moveamount=0;
	}
	
	
	public int getmove(){
		return moveamount;
	}
	
	
	
	public boolean hasmoved(){
		
		return hasmoved;
	}
	public void setmove(boolean b){
		hasmoved=b;
	}
	
	public boolean isanimal(){
		return isanimal;
	}
	
}
