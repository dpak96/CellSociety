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
	private int width;
	private int height;
	private HashMap<String, Cell> myPossibleCells;

	public XMLReader(String file){
		fileName = file;
		cells = new ArrayList<Cell>();
		parameters = new HashMap<String, Double>();
		//myGrid = grid;
		myPossibleCells = new HashMap<String, Cell>();
		myPossibleCells.put("segregation", new SegregationCell());
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
				simulation = eElement.getElementsByTagName("name").item(0).getTextContent();
			}

			//Getting grid dimensions
			nList = doc.getElementsByTagName(GRID);
			nNode = nList.item(0);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element eElement = (Element) nNode;
				width = Integer.parseInt(eElement.getElementsByTagName("width").item(0).getTextContent());
				height = Integer.parseInt(eElement.getElementsByTagName("height").item(0).getTextContent());
			}

			
			//Getting parameters
			nList = doc.getElementsByTagName(PARAM);		
			for (int temp = 0; temp < nList.getLength(); temp++) {
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					nNode = nList.item(temp);
					Element eElement = (Element) nNode;
					String name = eElement.getElementsByTagName("name").item(0).getTextContent();
					Double val = Double.parseDouble(eElement.getElementsByTagName("value").item(0).getTextContent());
					parameters.put(name, val);
				}
			}
			
			//Getting cells
			nList = doc.getElementsByTagName(TAGNAME);

			for (int temp = 0; temp < nList.getLength(); temp++) {

				nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					int x = Integer.valueOf(eElement.getElementsByTagName("xcord").item(0).getTextContent());
					System.out.println(x);
					int y = Integer.valueOf(eElement.getElementsByTagName("ycord").item(0).getTextContent());
					System.out.println(y);
					int state = Integer.valueOf(eElement.getElementsByTagName("state").item(0).getTextContent());
					System.out.println(state);
					Cell tempCell = myPossibleCells.get(simulation);
					tempCell.setCell(x, y, state, parameters);
					cells.add(tempCell);
					//cells.add(new GameOfLifeCell(x,y,state));

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
	public static void main (String args[]){
		XMLReader test = new XMLReader("/Users/danielpak/Desktop/test.xml");
		test.readFile();

		System.out.println(test.getSimulation());
		System.out.println(test.getParams().get("test"));
		System.out.println(test.getParams().get("test1"));
		System.out.println(test.getGridHeight());
		System.out.println(test.getGridWidth());
	}
}
