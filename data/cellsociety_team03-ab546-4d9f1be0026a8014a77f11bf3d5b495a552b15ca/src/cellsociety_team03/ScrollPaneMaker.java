package cellsociety_team03;

import javafx.scene.control.ScrollPane;

public interface ScrollPaneMaker {
	public static final int sizex= 320;
	public static final int sizey= 290;
	
	
	public static final int startx=50;
	public static final int starty=50;
	public ScrollPane getScrollPane();	
	public void setCellArray(Cell[][] cells);

}
