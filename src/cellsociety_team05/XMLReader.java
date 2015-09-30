package cellsociety_team05;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {
	private String fileName;
	private String simulation;
	private List<CellInfo> cells;
	private final static String SIMNAME = "simulation";
	private final static String TAGNAME = "row";
	private final static String PARAM = "parameters";
	private final static String NAME = "name";
	private final static String VALUE = "value";
	private final static int ZERO = 0;
	private Map<String, Double> parameters;
	private int width;
	private int height;

    public XMLReader (String file) {
        fileName = file;
        cells = new ArrayList<CellInfo>();
        parameters = new HashMap<String, Double>();
    }


	/**
     *
     */
	public void readFile() {
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

	public void init(Document doc) {

	}

	public void initSimType(Document doc) throws SimulationException {
		// Getting simulation type
		NodeList nList = doc.getElementsByTagName(SIMNAME);
		Node nNode = nList.item(ZERO);
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			// Simulation type
			simulation = eElement.getElementsByTagName(NAME).item(ZERO)
					.getTextContent();
		}
		if (simulation.length() <= ZERO) {
			throw (new SimulationException("No simulation type given."));
		}
	}

	public void initParameters(Document doc) {
		NodeList nList = doc.getElementsByTagName(PARAM);
		Node nNode = nList.item(ZERO);
		for (int temp = ZERO; temp < nList.getLength(); temp++) {
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				String name = eElement.getElementsByTagName(NAME).item(ZERO)
						.getTextContent();
				Double val = Double.parseDouble(eElement
						.getElementsByTagName(VALUE).item(ZERO)
						.getTextContent());
				parameters.put(name, val);
			}
		}
	}

	public void initCells(Document doc) throws SimulationException {
		NodeList nList = doc.getElementsByTagName(TAGNAME);
		height = nList.getLength();
		boolean first = true;
		for (int temp = ZERO; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String[] states = eElement.getElementsByTagName("values")
						.item(ZERO).getTextContent().split(",");
				for (int x = ZERO; x < states.length; x++) {
					cells.add(new CellInfo(x, temp, Integer.valueOf(states[x])));
				}
				if (first) {
					width = states.length;
					first = !first;
				} else {
					if (width != states.length) {
						throw (new SimulationException(
								"Incorrect number of cells in row."));
					}
				}
			}
		}
	}

	public Map<String, Double> getParams() {
		return parameters;
	}

	public List<CellInfo> getCells() {
		return cells;
	}

	public String getSimulationName() {
		return simulation;
	}

	public int getGridWidth() {
		return width;
	}

	public int getGridHeight() {
		return height;
	}
}
