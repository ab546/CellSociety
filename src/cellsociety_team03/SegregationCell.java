package cellsociety_team03;

public class SegregationCell extends Cell{

	String RED="red";
	String BLUE="blue";
	String EMPTY="empty";
	String WHITE="white";
	
	public SegregationCell(String state){
		super(state);
		gettypes().put(RED,RED);
		gettypes().put(BLUE,BLUE);
		gettypes().put(EMPTY, WHITE);

		
	}
	

}