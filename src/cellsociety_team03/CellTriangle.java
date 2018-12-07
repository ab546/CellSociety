package cellsociety_team03;

//generate triangular cell

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import java.lang.Math;
public class CellTriangle implements CellShape{
	private double centerX;
	private double centerY;
	private double mySide;
	private double[]points=new double[6];
	
	private boolean ispointingup;
	double radianssixty= Math.PI*60/180;
	
	
	public CellTriangle(double X,double Y,double side,boolean pointingup){
		 ispointingup = pointingup; 
		createShape(X,Y,side);
		
		
		 centerX=X;
			centerY=Y;
			mySide=side;
	}
	
	public void createShape(double X,double Y,double side){
		int flipconstant=1;
		if(!ispointingup){
			flipconstant=-1;
		}
			
	
		points[0]=X;
		points[1]=Y- (.5*side)/Math.sin(radianssixty)*flipconstant;
		points[2]=X+.5*side*flipconstant;
		points[3]=Y+.5*side*Math.cos(radianssixty)*flipconstant;
		points[4]=X-.5*side*flipconstant;
		points[5]=Y+.5*side*Math.cos(radianssixty)*flipconstant;
		/*
		}else{
			
			points[0]=X;
			points[1]=Y+ (.5*side)/Math.sin(radianssixty);
			points[2]=X-.5*side;
			points[3]=Y-.5*side*Math.cos(radianssixty);
			points[4]=X+.5*side;
			points[5]=Y-.5*side*Math.cos(radianssixty);
		}*/
	
	}
	
	public Polygon getShape(){
		Polygon P= new Polygon(points);
		P.setStroke(Color.BLACK);
//		P.setFill(Color.BLUE);
		return P;
	}
	
	public boolean getispointingup(){
		return ispointingup;
	}
	
}
