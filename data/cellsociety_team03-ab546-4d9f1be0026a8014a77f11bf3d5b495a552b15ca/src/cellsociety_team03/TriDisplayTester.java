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

public class TriDisplayTester implements ScrollPaneMaker{
	private int side=30;
	private int rows=10;
	private int cols=10;
	private Group root;
	private ScrollPane s1;
	Cell [][]myGrid;
	private static final double radianssixty= Math.PI*60/180;
	
	public TriDisplayTester(int inputrows, int inputcols, int inputside,Cell[][]grid){
		rows=inputrows;
		cols=inputcols;
		side=inputside;
		myGrid=grid;
		initalizescrollpane();
		
	}
	
	private void initalizescrollpane(){
		s1 = new ScrollPane();
		 s1.setPrefSize(sizex, sizey);
		 s1.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		 s1.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
		
		Group root=new Group();		
		populateshapes(root);
		s1.setContent(root);

	}
	
	
	
	
	private void populateshapes(Group root){
		boolean currpointingup=false;
		int flipconstant=1;
		double currx=startx;
		double curry=starty;
		for (int i=0;i<rows;i++){
			currx=startx;
			curry = curry + checkadder(i);
			if(cols % 2==0){
			currpointingup=!currpointingup;
			flipconstant=-flipconstant;
			}
		for (int j=0;j<cols;j++){
			CellTriangle celltri = new CellTriangle(currx,curry,side,currpointingup);
			//Text t1 = new Text(currx,curry , i + "," + j);	
			currx= currx + .5*side;
			curry=	flipconstant*(side/(2*Math.sin(radianssixty))) + curry -flipconstant*(Math.cos(radianssixty)*side/(2));
			flipconstant=-flipconstant;
			currpointingup=!currpointingup;
			Polygon p=celltri.getShape();
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
			//root.getChildren().add(t1);
			}
		}
	}
		
		private double checkadder(int i){
			if (cols %2 == 1 &&i % 2 ==0){
				return (side/(Math.sin(radianssixty))  +(Math.cos(radianssixty)*side/(2))) -(side/(2*Math.sin(radianssixty)));
			}
			if (cols %2 == 1 &&i % 2 ==1){
				return	((side*Math.cos(radianssixty))  -(Math.cos(radianssixty)*side/(2)))+(side/(2*Math.sin(radianssixty)));
			}
			if (cols %2 == 0 &&i % 2 ==1){
			return	((side*Math.cos(radianssixty)));
			}
			if (cols %2 == 0 &&i % 2 ==0){
				return (side/(Math.sin(radianssixty)));
			}
			return 1;
			}
		

	

	public ScrollPane getScrollPane(){
		return s1;
	}
	public void setCellArray(Cell[][]cells){
		myGrid=cells;
		populateshapes(root);
	}
}
