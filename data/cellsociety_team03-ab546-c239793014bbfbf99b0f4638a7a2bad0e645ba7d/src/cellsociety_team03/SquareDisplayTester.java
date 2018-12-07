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
import cellsociety_team03.CellSquare;

public class SquareDisplayTester implements ScrollPaneMaker{
	private int side;
	private int rows;
	private int cols;
	private CellShape[][] shapearray;
	private Cell[][]myGrid;
	private ScrollPane s1;
	private Group root;
	public SquareDisplayTester(int inputrows,int inputcols, int inputside,Cell[][]grid){
		rows=inputrows;
		cols=inputcols;
		side=inputside;
		myGrid=grid;
		initializescrollpane();
		shapearray= new CellShape[inputrows][inputcols];

	}
	private  void initializescrollpane(){
		 s1 = new ScrollPane();
		 s1.setPrefSize(sizex,sizey);
		 s1.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		 s1.setVbarPolicy(ScrollBarPolicy.ALWAYS);

		root=new Group();
		populateshapes(root);
		s1.setContent(root);
		
	}
	
	private void populateshapes(Group root){
		int currx=startx;
		int curry=starty;
		
		for (int i=0;i<rows;i++){
			currx=startx;
			curry=curry+side;
		for (int j=0;j<cols;j++){
		
		currx = currx + side;
		CellSquare cellquad = new CellSquare(currx,curry,side);
		
		//shapearray[i][j]=cellquad;
		Polygon p=cellquad.getShape();
		p.setFill(Color.valueOf(myGrid[i][j].getTypecolor()));
		int loctoUpdateX=i;
		int loctoUpdateY=j;
		p.setOnMouseClicked(new EventHandler<MouseEvent>()
        
		{
			public void handle(MouseEvent t) {
				myGrid[loctoUpdateX][loctoUpdateY].updateState();
				
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
	public CellShape[][] getshapearray(){
		return shapearray;
	}
	public void setCellArray(Cell[][]cells){
		myGrid=cells;
		populateshapes(root);
	}
	
	
	
	
	
	
	
	

		
}
