package cellsociety_team03;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.MouseEvent;

public class HexDisplayTester implements ScrollPaneMaker{
	private int rows;
	private int cols;
	private int side;
	private ScrollPane s1;
	public static final double radianssixty= Math.PI*60/180;
	Cell[][]myGrid;
<<<<<<< HEAD:src/cellsociety_team03/HexDisplayTest.java
	
public HexDisplayTest(int inputrows, int inputcols, int inputside,Cell[][] grid){
=======
	private Group root;
public HexDisplayTester(int inputrows, int inputcols, int inputside,Cell[][]grid){
>>>>>>> 8c08d12e322b2eb1460ee398f5fce789935be28e:data/cellsociety_team03-ab546-4d9f1be0026a8014a77f11bf3d5b495a552b15ca/src/cellsociety_team03/HexDisplayTester.java
	rows=inputrows;
	cols=inputcols;
	side=inputside;
	myGrid=grid;
	
	 

	initializescrollpane();

	

	
}


private  void initializescrollpane(){
	s1 = new ScrollPane();
	 s1.setPrefSize(sizex, sizey);
	 s1.setHbarPolicy(ScrollBarPolicy.ALWAYS);
	 s1.setVbarPolicy(ScrollBarPolicy.ALWAYS);
	 root=new Group();
	s1.setContent(root);
	populateshapes(root);
}


private void populateshapes(Group root){

	double currx=startx;
	double curry=starty;

	for(int i=0;i<rows;i++){
		
		currx= startx + 1.5*side * (i%2);
		curry = curry + Math.sin(radianssixty)*side;
		for (int j=0;j<cols;j++){
		Hexagon hex=new Hexagon(currx,curry,side);
		currx= currx + 3*side;
		Polygon p=hex.getShape();
		
		p.setFill(Color.valueOf(myGrid[i][j].getTypecolor()));
		
		int hextoUpdateX=i;
		int hextoUpdateY=j;
		p.setOnMouseClicked(new EventHandler<MouseEvent>()
        
		{
			public void handle(MouseEvent t) {
				myGrid[hextoUpdateX][hextoUpdateY].updateState();
				
				populateshapes(root);
			}
        });
		root.getChildren().add(p);
	
		


	}
	
	
}

}

public ScrollPane getScrollPane(){
	return s1;

}
public void setCellArray(Cell[][]cells){
	myGrid=cells;
	populateshapes(root);
}
}
