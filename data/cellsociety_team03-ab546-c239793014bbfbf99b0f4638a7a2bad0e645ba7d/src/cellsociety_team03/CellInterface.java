package cellsociety_team03;

import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;

import javafx.application.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.geometry.*;

public class CellInterface extends Application{
	
	private Scene myscene;
	public static final int gamewidth= 500;
	public static final int gameheight=500;
	public static final Paint BACKGROUND= Color.WHITE;
	public static final String TITLE="CELLSOCIETY";
	private double FRAMES_PER_SECOND = 1;
    private double MILLISECOND_DELAY;
    private double SECOND_DELAY;
	public static final double BUTTON_PADDING=50;
	private int size = 15;
	

	private GridPane root;	
	private Timeline animation;
	private Game sg;
	private boolean paused;
	
	
	public CellInterface(Game startgame,int speed){
		sg=startgame;
		size= sg.getsize();
		FRAMES_PER_SECOND=speed;
		MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
		SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	}
	
	
	
    public void start(Stage primarystage) {
    	
        
        setupGame();
       	primarystage.setScene(myscene);
        primarystage.show();
        createanimation();
    }
    
    private void createanimation(){
    	
    	 KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                 e -> step());
         animation = new Timeline();
         animation.setCycleCount(Timeline.INDEFINITE);
         animation.getKeyFrames().add(frame);
         animation.play();
    	
    }
    
    
    private void setupGame(){
    	
    	paused= false;
    	root = new GridPane();

    	myscene=new Scene(root, gamewidth,gameheight);
    	
    	 //sg= new FireGame(size, 40);
 		//sg.initializeGame();
 		

    	


    	Cell[][] cellarray = sg.getgrid();

    	
    	initializecells(cellarray);
    	
    	myscene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
    }
    
    
    private void handleKeyInput(KeyCode code){
    	
    	if(code.equals(KeyCode.SPACE)){
    		paused=!paused;
    	}
    	if(code.equals(KeyCode.A)){
    		
    		paused=false;
    		step();
    		paused=true;
    	}
    }
 
    
    
    private void initializecells(Cell[][] cellarray){
    	
    	//Cite code
        for (int i = 0; i < size; i++) {
            root.getColumnConstraints().add(new ColumnConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, HPos.CENTER, true));
            root.getRowConstraints().add(new RowConstraints(5, Control.USE_COMPUTED_SIZE, Double.POSITIVE_INFINITY, Priority.ALWAYS, VPos.CENTER, true));
        }
        
        colorcells(cellarray);
      
    }
    

    private void step(){    
    	if(!paused){
    	sg.step();



    	Cell[][] cellarray = sg.getgrid();

    	colorcells(cellarray);
    	}
    	
    }
    
    private void colorcells(Cell[][] cellarray){
    	for (int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				Cell currcell= cellarray[i][j];
				StackPane canvas = new StackPane();
				String cellcolor = currcell.getTypecolor();
				canvas.setStyle("-fx-background-color: " +cellcolor  +";");
				root.add(canvas, i,j);
			}
    		}
    }
/*
    public static void main(String[] args) {
        launch(args);
    }*/
}
