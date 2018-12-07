//Code written by Robert Duvall

package cellsociety_team03;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.util.List;

import javax.xml.bind.annotation.XmlElement.DEFAULT;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class XML {
    // name of root attribute that notes the type of file expecting to parse
    private File dataFile;
    private Default defaults;
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
	
    public static final String TYPE_ATTRIBUTE = "type";
    public static final String GRID_ATTRIBUTE = "grid";
    public static final String TITLE_ELEMENT="title";
    public static final String AUTHOR_ELEMENT="author";
    public static final String SHAPE_ELEMENT="shape";
    // keep only one documentBuilder because it is expensive to make and can reset it before parsing
    private static final DocumentBuilder DOCUMENT_BUILDER = getDocumentBuilder();
    /**
     * Get the data contained in this XML file as an object
     */
    public XML(File data){
    	dataFile=data;
    	defaults=getDefaults();
    }
    public XML(String fileName){
    	dataFile=new File(fileName);
    	defaults=getDefaults();
    }
    public Default getDefaults () {
        
    	Element root = getRootElement(dataFile);
        Default myDefault=GAMECHOICE.get(getAttribute(root,TYPE_ATTRIBUTE));
    	
        if (! isValidFile(root, myDefault.getType())) {
        	
        	
        	throw new XMLException("XML file does not represent %s", myDefault.getType());
        }
        // read data associated with the fields given by the object
        
        Map<String, String> results = new HashMap<>();
        for (String field : myDefault.getFields()) {
            
        	results.put(field, getTextValue(root, field));
        	
        }
       
        myDefault.setDataValues(results);
        if((Integer.parseInt(getAttribute(root,GRID_ATTRIBUTE))==1)){
        	
        	myDefault.initialGrid=true;
        	String[]checkStates=getTextValue(root,"states").split(" ");
        	for(int i=0;i<checkStates.length;i++){
        		if(!myDefault.getStates().contains(checkStates[i])){
        			throw new XMLException("XML file contains invalid state %s", checkStates[i]);
        		}
        	}
        	int correctSize=squared(Integer.parseInt(getTextValue(root,"size")));
        	if(checkStates.length!=correctSize){
        		throw new XMLException("XML file contains %d states instead of %d",checkStates.length,correctSize);
        	}
        	myDefault.setStates(getTextValue(root,"states"));
        	
        	}
        
        myDefault.setShape(getTextValue(root,"SHAPE_ELEMENT"));
        myDefault.setTitle(getTextValue(root,TITLE_ELEMENT));
        myDefault.setAuthor(getTextValue(root,AUTHOR_ELEMENT));
        if(getTextValue(root,"SHAPE_ELEMENT").equals("")){
        	throw new XMLException("XML file does not contain %s element",SHAPE_ELEMENT);
        }
        return myDefault;
    }
  
    
    public List<String> getAttributes(){
    	return defaults.getFields();
    }
    private Element getRootElement (File xmlFile) {
        try {
            DOCUMENT_BUILDER.reset();
            Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile);
            return xmlDocument.getDocumentElement();
        }
        catch (SAXException | IOException e) {
            throw new XMLException(e);
        }
    }
    // Returns if this is a valid XML file for the specified object type
    private boolean isValidFile (Element root, String type) {
        return getAttribute(root, TYPE_ATTRIBUTE).equals(type);
    }
    // Get value of Element's attribute
    private String getAttribute (Element e, String attributeName) {
        return e.getAttribute(attributeName);
    }
    // Get value of Element's text
    private String getTextValue (Element e, String tagName) {
        NodeList nodeList = e.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        else {
            // FIXME: empty string or null, is it an error to not find the text value?
            return "";
        }
    }
    // Helper method to do the boilerplate code needed to make a documentBuilder.
    private int squared(int i){
    	return i*i;
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
