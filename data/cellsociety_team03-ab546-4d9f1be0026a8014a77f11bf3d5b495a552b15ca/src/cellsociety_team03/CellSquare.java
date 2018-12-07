package cellsociety_team03;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import java.lang.Math;

public class CellSquare implements CellShape{
	private double centerX;
	private double centerY;
	private double mySide;
	private double[]points=new double[8];
	double radianssixty= Math.PI*45/180;
	public CellSquare(double X,double Y,double side){
		centerX=X;
	      centerY=Y;
	      mySide=side;
		 createShape(X,Y,side);
	}
		
		public void createShape(double X,double Y,double side){
			
			points[0]= X-.5*side;
			points[1]= Y-.5*side;
			points[2]=X+.5*side;
			points[3]=Y-.5*side;
			points[4]=X+.5*side;
			points[5]=Y+.5*side;
			points[6]=X-.5*side;
			points[7]=Y+.5*side;
			
		      
			
		}
		
		
		
		
	public Polygon getShape(){
		Polygon P= new Polygon(points);
		P.setStroke(Color.BLACK);
		P.setFill(Color.BLUE);
		return P;
	}
	
	
	
	
}
