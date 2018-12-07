package cellsociety_team03;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import java.lang.Math;
/**
*A class which takes an X and Y coordinate for the center of a hexagon,
*and a side length to calculate the X and Y coordinates of each vertex,
*which is then used to generate a polygon for display
*/

public class Hexagon implements CellShape{

private double[]points=new double[12];
private double radianssixty=60*2*3.14159/360;


	public Hexagon(double X,double Y,double side){
	 createShape(X,Y,side);
}
	
	public void createShape(double X,double Y,double side){
		points[0] = X-side;           
		 points[1] = Y;
	      points[2] = X-side*Math.cos(radianssixty);        
	      points[3] = Y-side*Math.sin(radianssixty);
	      points[4] = X+side*Math.cos(radianssixty);  
	      points[5] = Y-side*Math.sin(radianssixty);
	      points[6] = X+side;          
	      points[7] = Y;
	      points[8] = X+side*Math.cos(radianssixty);         
	      points[9] = Y+side*Math.sin(radianssixty);
	      points[10] = X-side*Math.cos(radianssixty);      
	      points[11] = Y+side*Math.sin(radianssixty);

		
	}
	
	
	
	
public Polygon getShape(){
	Polygon P= new Polygon(points);
	P.setStroke(Color.BLACK);
	P.setFill(Color.BLUE);
	return P;
}







}
