package cellsociety_team05;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class XMLReader {
	private  String fileName; 
	private String simulation;
	private  ArrayList<CellInfo> cells;
	private static String SIMNAME = "simulation";
	private static String TAGNAME = "row";
	private static String PARAM = "parameters";
	private HashMap<String, Double> parameters; 
	private int width;
	private int height;


	public XMLReader(String file){
		fileName = file;
		cells = new ArrayList<CellInfo>();
		parameters = new HashMap<String, Double>();
		//myGrid = grid;
	}

	/**
	 * 
	 */
	public void readFile(){
		try {
			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			initSimType(doc);
			initParameters(doc);
			initCells(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initSimType(Document doc){
		//Getting simulation type
		NodeList nList = doc.getElementsByTagName(SIMNAME);
		Node nNode = nList.item(0);
		if(nNode.getNodeType()==Node.ELEMENT_NODE){
			Element eElement = (Element) nNode;
			//Simulation type
			simulation = eElement.getElementsByTagName("name").item(0).getTextContent();
		}
	}
	
	public void initParameters(Document doc){
		NodeList nList = doc.getElementsByTagName(PARAM);
		Node nNode = nList.item(0);
		for (int temp = 0; temp < nList.getLength(); temp++) {
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				String name = eElement.getElementsByTagName("name").item(0).getTextContent();
				Double val = Double.parseDouble(eElement.getElementsByTagName("value").item(0).getTextContent());
				parameters.put(name, val);
			}
		}
	}
	
	public void initCells(Document doc){
		NodeList nList = doc.getElementsByTagName(TAGNAME);
		height = nList.getLength();
		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
			
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				String[] states = eElement.getElementsByTagName("values").item(0).getTextContent().split(",");
				for(int x = 0; x < states.length; x++){
					cells.add(new CellInfo(x,temp,Integer.valueOf(states[x])));
					System.out.print(states[x]);
				}
				System.out.println();
				width = states.length; 
			}
		}
	}
	public HashMap<String, Double> getParams(){
		return parameters;
	}
	public ArrayList<CellInfo> getCells(){
		return cells;
	}
	public String getSimulationName(){
		return simulation;
	}
	public int getGridWidth(){
		return width;
	}
	public int getGridHeight(){
		return height;
	}
}
