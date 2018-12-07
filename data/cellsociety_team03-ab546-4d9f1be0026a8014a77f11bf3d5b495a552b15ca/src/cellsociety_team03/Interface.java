package cellsociety_team03;

import javafx.animation.KeyFrame;
	import javafx.animation.Timeline;
	import javafx.application.Application;
	import javafx.scene.Group;
	import javafx.scene.Scene;
	import javafx.scene.shape.Circle;
	import javafx.scene.text.Text;
	import javafx.scene.image.Image;
	import javafx.scene.image.ImageView;
	import javafx.scene.input.KeyCode;
	import javafx.scene.paint.Color;
	import javafx.scene.paint.Paint;
	import javafx.scene.shape.Rectangle;
	import javafx.scene.shape.Shape;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Duration;

	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;

import java.util.Random;

import javax.xml.transform.stream.StreamResult;

import cellsociety_team03.ConwayDefault;
import cellsociety_team03.Default;
import cellsociety_team03.DefaultFactory;
import cellsociety_team03.FireDefault;
import cellsociety_team03.SegregationDefault;
import cellsociety_team03.WatorDefault;

import java.io.File;
import java.math.*;
	import javafx.scene.control.Label;
	import javafx.scene.control.Slider;
	import javafx.event.ActionEvent;
	import javafx.event.EventHandler;
	import java.util.List;
import java.util.Map;
import java.util.ArrayList;
	import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.util.Arrays;
import java.util.HashMap;
public class Interface extends Application{
	
	/**
	 * A basic example JavaFX program for the first lab.
	 * 
	 * @author Robert C. Duvall
	 */
		
		
	    public static final String TITLE = "CellSociety";
	    public static final int GRID_SPACE=300;
	    public static final int SIZE =800;
	    public static final Paint BACKGROUND = Color.WHITE;
	    public static final int FRAMES_PER_SECOND = 60;
	    public static final int MILLISECOND_DELAY = 10000 / FRAMES_PER_SECOND;
	    public static final double SECOND_DELAY = 10.0 / FRAMES_PER_SECOND;
	    public static final int KEY_INPUT_SPEED = 20;
	    public static final int offset=50;
	    public static final int scrollpositionx= 250;
	    public static final int scrollpositiony = 10;
	    public static final int sliderxposition =350;
	    private boolean isStarted=false;
	    public static final Map<String,Default> GAMECHOICE=getGames();
	    
		private static Map<String, Default> getGames()
		{
			 Map<String,Default> myMap = new HashMap<String,Default>();
			myMap.put("Wator", new WatorDefault());
			myMap.put("Fire", new FireDefault());
			myMap.put("Conway", new ConwayDefault());
			myMap.put("Segregation", new SegregationDefault());
			return myMap;
		};

	    private Scene myScene;
	    private Default myDefault;
	    private Stage myStage;
	   private Group root;
	  private List<Slider>sliders;
	   private String cellShape;
	    private Cell[][] myGrid;
	   
	private Game myGame;
       private int[] myValues;

	   public static final int side=20;
	   private int gridSize=10;
	   ScrollPaneMaker hs;
	   

       /**
	     * Initialize what will be displayed and how it will be updated.
	     */
	    //@Override
	    public void start (Stage s) {
	       myStage=s;
	    	myScene = promptWhichGame(SIZE, SIZE, BACKGROUND);
	        
	    	s.setScene(myScene);
	        s.setTitle(TITLE);
	        s.show();
	        	
	    
	    }
	
	   private Scene promptWhichGame(int width, int height, Paint background){
		    root=new Group();
		    myScene=new Scene(root,width,height,background);
		  makeText(root);
		    makeGameButtons(root);
		   
		   return myScene;
	   }
	   private void makeText(Group root){
		   Text t=new Text("Which game would you like to play?");
		   t.setX(SIZE/2-t.getBoundsInParent().getWidth()/2);
		   t.setY(SIZE/8);
		   root.getChildren().add(t);
	   }
	   private void makeXMLButton(Group root){
		   Button button=new Button("Upload XML file");
		   root.getChildren().add(button);
		   button.setLayoutX(0);
		   button.setLayoutY(SIZE*.9);
		   button.setOnAction(new EventHandler<ActionEvent>(){
			   public void handle(ActionEvent event){
				  FileChooser filechooser=new FileChooser();
				  filechooser.setTitle("Select XML file");
				  filechooser.getExtensionFilters().addAll(new ExtensionFilter(".xml files","*.xml"));
				  Stage ownerWindow=new Stage();
				  filechooser.setInitialDirectory(new File("C:\\Users\\Alex\\workspace\\cellsociety_team03\\data"));
				  File file=filechooser.showOpenDialog(ownerWindow);
				  XML myXML=new XML(file);
				  myDefault=myXML.getDefaults();
				  myStage.setScene(setupGame(SIZE,SIZE,BACKGROUND));
			   }
		   });
	   }
	   private void makeGameButtons(Group root){
		   int i=0;
		   for(String s:GAMECHOICE.keySet()){
			   Button button=new Button(s);
			   root.getChildren().add(button);
			   button.setLayoutX(SIZE/2-button.getBoundsInLocal().getWidth()/2);
			   button.setLayoutY(SIZE/4+offset*i);
			   button.setOnAction(new EventHandler<ActionEvent>() {
				 
				   public void handle(ActionEvent event) {
					   myDefault= new DefaultFactory(s).getDefault();
				    	 myStage.setScene(setupGame(SIZE,SIZE,BACKGROUND));
				    	 myStage.setTitle(myDefault.getHeader());
					   KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                               e -> step(SECOND_DELAY));
						 Timeline animation = new Timeline();
						 animation.setCycleCount(Timeline.INDEFINITE);
						 animation.getKeyFrames().add(frame);
						 animation.play();
				    }
				});
		   i++;
		   }
	   }
	    private Scene setupGame (int width, int height, Paint background) {
	        // create one top level collection to organize the things in the scene
	         
	    	root = new Group();
	        // create a place to see the shapes
	        myScene = new Scene(root, width, height, background);
	        // make some shapes and set their properties
	        sliders=new ArrayList<Slider>();
	       
	        //for(int i=0;i<new cellsociety_team03.XML("data/test.xml").getAttributes().size();i++){
	      int c=0;
	        for(String s:myDefault.getFields()){

	        	Slider slider=getSlider(sliderxposition,350+offset*c,s,c);

	        sliders.add(slider);
	        
	        root.getChildren().add(slider);
	        c++;
	        }
	        if(myValues!=null){
	        gridSize=myValues[myDefault.getFields().size()-2];
	        }
	        myValues= new int[myDefault.getFields().size()];
	      
	        for(int i=0;i<myDefault.getFields().size();i++){
	        myValues[i]=(int)sliders.get(i).getValue();
	        
	      }
	       cellShape=myDefault.getShape();
	        myGrid=getGrid();
	      addComboBox(); 
	      myDefault.getScrollPanes(gridSize, gridSize, side, myGrid);
	      Map<String,ScrollPaneMaker> map=myDefault.getScrollPaneChoices();
	     hs =map.get(cellShape);
	 
	     hs.getScrollPane().setLayoutX(scrollpositionx);
	      hs.getScrollPane().setLayoutY(scrollpositiony);

	       
	       root.getChildren().add(hs.getScrollPane());
	       
	       addStartButton();
	       addSaveButton();
	      makeXMLButton(root);
	       myGame=getGame();
	       return myScene;
	        
	    }
	    
	    
	    
	
	    public void step(double elapsedTime){


	    	int counter=0;
	    	for(int i=0;i<myDefault.getFields().size();i++){
	    		if(myValues[i]!=(int) sliders.get(i).getValue()&&!sliders.get(i).isValueChanging()){
		    		myValues[i]=(int) sliders.get(i).getValue();

		    		counter++;
	    		

	    		}
	    	}
	    	if(counter>0){
    			
    			Scene newScene=setupGame(SIZE,SIZE,BACKGROUND);
    	    	myStage.setScene(newScene);
    		}	
	    	if(isStarted){
	    		myGame.step();
	    		
	    		
	    		myGrid=myGame.getgrid();
	    		
	    		hs.setCellArray(myGrid);
	    	}

	    }
	    private void addComboBox(){
	    	myDefault.getScrollPanes(gridSize, gridSize, side, myGrid);
	    	ComboBox<String> box=new ComboBox<String>();
	    	box.getItems().addAll(myDefault.getScrollPaneChoices().keySet());
	    	 box.valueProperty().addListener((x,y,newValue) ->{
	      	   root.getChildren().remove(hs);
	    		 hs=myDefault.getScrollPaneChoices().get(newValue);
	      	 hs.getScrollPane().setLayoutX(scrollpositionx);
		       hs.getScrollPane().setLayoutY(scrollpositiony);
		       cellShape=newValue;
		       
		       root.getChildren().add(hs.getScrollPane());
	         });
	    	 root.getChildren().add(box);
	    }
	   private void addStartButton(){
		   Button startButton=new Button("Start");
		   root.getChildren().add(startButton);
		   startButton.setLayoutX(0);
		   startButton.setLayoutY(0);
		   startButton.setOnAction(new EventHandler<ActionEvent>() {
			 
			   public void handle(ActionEvent event) {

				   
				   
				 isStarted=true;
				
			   }
			   });
		   
	   }
	   private void addSaveButton(){
		   Button saveButton=new Button("Save Simulation as XML");
		   root.getChildren().add(saveButton);
		  saveButton.setLayoutX(700);
		   saveButton.setLayoutY(0);
		   saveButton.setOnAction(new EventHandler<ActionEvent>() {
				 
			   public void handle(ActionEvent event) {

				   
				   
				 XMLWriter write=new XMLWriter(myDefault,myValues,myGrid);
				 write.getXML();

			   }
			   });
	   }
	    

	

	    public Slider getSlider(double X, double Y,String s,int i){
	    	Slider slider = new Slider();
	        slider.setMin(0);
	      
	        slider.setValue(Integer.parseInt(myDefault.getAttributes().get(s)));
	        if(myValues!=null){	 
	        slider.setValue(myValues[i]);
	        }

	        slider.setMax(myDefault.getMaxes().get(s));
	        
	        slider.setShowTickLabels(true);
	        slider.setShowTickMarks(true);
	        slider.setMajorTickUnit(slider.getMax()/5);
	        slider.setMinorTickCount(5);
	        slider.setBlockIncrement(10);
	        slider.setLayoutY(Y);
	        slider.setLayoutX(X);
	        
	        Label label2=new Label(s);
	        label2.setLayoutY(Y);
	        label2.setLayoutX(X-150);
	       
	        
	        root.getChildren().add(label2);
	        
	        return slider;
	    }

	  private Game getGame(){
		 Game startgame=myDefault.getnewgame(myValues,cellShape);
		 startgame.setgrid(myGrid);
		  return startgame;

	  }
	  private Cell[][] getGrid(){
			 Cell[][] newgrid=myDefault.getGrid(myValues);
			 myGrid=newgrid;
			  return newgrid;
		  }

	  public Cell[][] giveGrid(){
		  return myGrid;
	  }
	public int[] giveInputs(){
		return myValues;
	}
	    /**
	     * Start the program.
	     */
	    public static void main (String[] args) {
	       launch(args);
	    }
	
}