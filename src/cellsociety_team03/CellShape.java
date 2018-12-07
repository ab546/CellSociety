package cellsociety_team03;
import javafx.scene.shape.*;


//generate the cell's shape

public interface CellShape {

	
	public void createShape(double X,double Y, double side);
	public Polygon getShape();
}
