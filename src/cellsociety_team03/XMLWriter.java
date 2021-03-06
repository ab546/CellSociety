//Written by Alex Blumenstock
package cellsociety_team03;
import java.io.File;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javafx.scene.Node;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;

public class XMLWriter {
	private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
	private Default myDefault;
	int[]params;
	Cell[][]myCells;
	public static final String TYPE_ATTRIBUTE = "type";
    public static final String GRID_ATTRIBUTE = "grid";
    public static final String STATE_ELEMENT="states";
    public static final String TITLE_ELEMENT="title";
    public static final String AUTHOR_ELEMENT="author";
    public static final String SHAPE_ELEMENT="shape";
    public static final String TOROIDAL_ELEMENT="toroidal";
	public XMLWriter(Default d, int[] param,Cell[][] cells){
		myDefault=d;
		params=param;
		myCells=cells;
	}
 //Prepares the Document in a DocumentBuilder by appending elements and attributes
	public void getXML(){
	Document doc=DOCUMENT_BUILDER.newDocument();
	Element root=doc.createElement("Default"); 
	doc.appendChild(root);
	setAttributes( root, doc);
	
	for(String s:myDefault.getFields()){
		
	Element element=doc.createElement(s);
	
	int location=myDefault.getFields().indexOf(s);
	element.appendChild(doc.createTextNode(""+params[location]));
	root.appendChild(element);
	 }
	
	getNamingElements(TITLE_ELEMENT,root,doc);
	getNamingElements(AUTHOR_ELEMENT,root,doc);
	Element element=doc.createElement(SHAPE_ELEMENT);
	element.appendChild(doc.createTextNode(myDefault.getShape()));
	root.appendChild(element);
	String states=getStates();
	
	Element state=doc.createElement(STATE_ELEMENT);
	state.appendChild(doc.createTextNode(states));
	root.appendChild(state);
	Element toroidal=doc.createElement(TOROIDAL_ELEMENT);
	toroidal.appendChild(doc.createTextNode(Boolean.toString(myDefault.isToroidal())));
	root.appendChild(toroidal);
	
	printFile(doc);
 }
 private void getNamingElements(String s, Element root,Document doc){
	 Element e=doc.createElement(s);
	 e.appendChild(doc.createTextNode(promptUser(s)));
	 root.appendChild(e);
 }
 private String promptUser(String prompt){
	 String s="";
	 TextInputDialog input=new TextInputDialog(String.format("Please enter the %s for this simulation",prompt));
	 input.setWidth(100);
	 Optional<String> response = input.showAndWait();
	 if (response.isPresent()){
		 input.close();
		 s=response.get();
	 }
 return s;
 }
 private String getStates(){
	 String states="";
		for(int i=0;i<myCells.length;i++){
			for(int j=0;j<myCells.length;j++){
				states+=myCells[i][j].getType();
				if(!(i==j)||!(j==myCells.length-1))
					states+=" ";
			}
		}
		return states;
 }
 private void setAttributes(Element root, Document doc){
	 Attr grid=doc.createAttribute(GRID_ATTRIBUTE);
		grid.setValue(Integer.toString(1));
		Attr type=doc.createAttribute(TYPE_ATTRIBUTE);
		type.setValue(myDefault.getType());
		root.setAttributeNode(grid);
		root.setAttributeNode(type);
 }
 private void printFile(Document doc){
	 TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DOMSource source = new DOMSource(doc);
		FileChooser filechooser=new FileChooser();
		filechooser.setTitle("Select location to save XML file");
		filechooser.getExtensionFilters().addAll(new ExtensionFilter(".xml files","*.xml"));
		Stage ownerWindow=new Stage();
		filechooser.setInitialDirectory(new File("C:\\Users\\Alex\\workspace\\cellsociety_team03\\data"));
		Result result=null;
		
		 result = new StreamResult(filechooser.showSaveDialog(ownerWindow));
		
		//StreamResult result = new StreamResult(System.out);
		try {
			
			transformer.transform(source, result);
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 }
 private static DocumentBuilder getDocumentBuilder () {
	        try {
	            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
	        }
	        catch (ParserConfigurationException e) {
	            throw new XMLException(e);
	        }
	    }
}