package cellsociety_team03;
//In absence of other XML files, This factory intitializes the appropriate defaults 
public class DefaultFactory {
	private String type;
public DefaultFactory(String s){
type=s;	
}
public Default getDefault(){
	if(type=="Fire"){
		XML reader=new XML("data/FireDefaults.xml");
		return reader.getDefaults();
	}
	if(type=="Wator"){
		XML reader=new XML("data/WatorDefaults.xml");
		return reader.getDefaults();
	}
	if(type=="Conway"){
		XML reader=new XML("data/ConwayDefaults.xml");
		return reader.getDefaults();
	}
	if(type=="Segregation"){
		XML reader=new XML("data/SegregationDefaults.xml");
		return reader.getDefaults();
	}
	if(type=="Ants"){
		XML reader=new XML("data/AntsDefaults.xml");
		return reader.getDefaults();
	}
	else{
		//throw exception
	}
	return null;
}
}
