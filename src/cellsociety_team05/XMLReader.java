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


public class XMLReader {
	private  String fileName; 
	private String simulation;
	private  ArrayList<Cell> cells;
	private static String SIMNAME = "simulation";
	private static String TAGNAME = "cell";
	private static String PARAM = "parameters";
	private static String GRID = "grid";
	private HashMap<String, Double> parameters; 
	private int rule;
	private int width;
	private int height;

	public XMLReader(String file){
		fileName = file;
		cells = new ArrayList<Cell>();
		//myGrid = grid;
	}

	public void readFile(){
		try {

			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(fXmlFile);

			doc.getDocumentElement().normalize();

			//Getting simulation type
			NodeList nList = doc.getElementsByTagName(SIMNAME);
			Node nNode = nList.item(0);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element eElement = (Element) nNode;
				//Simulation type
				simulation = eElement.getElementsByTagName("type").item(0).getTextContent();
				//Simulation parameter. Maybe make a new class for different rules? 
				rule = Integer.valueOf(eElement.getElementsByTagName("param").item(0).getTextContent());
			}

			//Getting parameters
			nList = doc.getElementsByTagName(PARAM);
			nNode = nList.item(0);
			Element eElement = (Element) nNode;

			parameters.put(eElement.getAttribute("name"), Double.parseDouble(eElement.getAttribute("val")));

			//Getting grid dimensions
			nList = doc.getElementsByTagName(GRID);
			nNode = nList.item(0);
			eElement = (Element) nNode;
			width = Integer.parseInt(eElement.getAttribute("width"));
			height = Integer.parseInt(eElement.getAttribute("height"));


			//Getting cells
			nList = doc.getElementsByTagName(TAGNAME);

			for (int temp = 0; temp < nList.getLength(); temp++) {

				nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					eElement = (Element) nNode;
					int x = Integer.valueOf(eElement.getElementsByTagName("xcord").item(0).getTextContent());
					int y = Integer.valueOf(eElement.getElementsByTagName("ycord").item(0).getTextContent());
					int state = Integer.valueOf(eElement.getElementsByTagName("state").item(0).getTextContent());
					cells.add(new Cell(x,y,state));

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public HashMap<String, Double> getParams(){
		return parameters;
	}
	public ArrayList<Cell> getCells(){
		return cells;
	}
	public String getSimulation(){
		return simulation;
	}
	public int getGridWidth(){
		return width;
	}
	public int getGridHeight(){
		return height;
	}
}
