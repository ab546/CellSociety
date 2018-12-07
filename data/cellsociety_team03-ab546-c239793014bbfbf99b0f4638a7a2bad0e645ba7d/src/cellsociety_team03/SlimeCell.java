package cellsociety_team03;

public class SlimeCell extends Cell{
	private boolean hasTurtle;
	private double slime;
	private Turtle turtle;
public SlimeCell(String state){
	super(state);
	gettypes().put("turtle", "green");
	gettypes().put("empty", "white");
	if(state.equals("turtle")){
		hasTurtle=true;
	}
	slime=0;
}
public boolean hasTurtle(){
	return hasTurtle;
}
public Turtle getTurtle(){
	return turtle;
}
}
