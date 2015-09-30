package cellsociety_team05;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLEditor {
    private String fileName;
    private Map<String, Double> parameters;
    private Simulation mySimulation;
    private String mySimName;
    private static String SIMULATION = "simulation";
    private static String NAME = "name";

    public XMLEditor (String s, Simulation simulation) {
        fileName = s;
        mySimName = simulation.getName();
        parameters = simulation.getParams();
        mySimulation = simulation;
    }

    public Document readFile () {
        try {
            String filepath = fileName;
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);
            return doc;
        }
        catch (ParserConfigurationException pce) {
            pce.printStackTrace();
            return null;
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
        catch (SAXException sae) {
            sae.printStackTrace();
            return null;
        }
    }

    public void editFile () {
        Document doc = readFile();
        Node first = doc.getFirstChild();
        addSimulation(doc, first, mySimName);
        addParameters(doc, first, parameters);
        addCells(doc, first, mySimulation);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileName));
            transformer.transform(source, result);
        }
        catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        catch (TransformerException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
    }

    public void addSimulation (Document doc, Node first, String sim) {
        removeNode(first, "simulation");
        addNode(doc, first, "simulation", "name", sim);
    }

    public void addParameters (Document doc, Node first, Map<String, Double> params) {
        removeNode(first, "parameters");
        for (String s : params.keySet()) {
            Node temp = addNode(doc, first, "parameters", "name", s);
            addElement(doc, temp, "value", params.get(s));
        }
    }

    public void addCells (Document doc, Node first, Simulation sim) {
        removeNode(first, "row");
        for (int i = 0; i < sim.getMyHeight(); i++) {
            String row = listToString(sim.getGridRow(i));
            addNode(doc, first, "row", "values", row);
        }
    }

    public boolean hasNode (Node curr, String check) {
        NodeList list = curr.getChildNodes();
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeName().equals(check)) {
                return true;
            }
        }
        return false;
    }

    public void removeNode (Node curr, String check) {
        NodeList list = curr.getChildNodes();
        for (int i = list.getLength() - 1; i > 0; i--) {
            if (list.item(i).getNodeName().equals(check)) {
                curr.removeChild(list.item(i));
            }
        }
    }

    /**
     * Add a node to the curr Node with a new element of a given string value.
     *
     * @param doc
     * @param curr
     * @param node
     * @param element
     * @param value
     */
    public Node addNode (Document doc, Node curr, String node, String element, String value) {
        Node temp = doc.createElement(node);
        Element tempEl = doc.createElement(element);
        tempEl.appendChild(doc.createTextNode(value));
        temp.appendChild(tempEl);
        curr.appendChild(temp);
        return temp;
    }

    /**
     * Add a node to the curr Node with a new element of a given double value.
     *
     * @param doc
     * @param curr
     * @param node
     * @param element
     * @param value
     * @return
     */
    public Node addNode (Document doc, Node curr, String node, String element, Double value) {
        return addNode(doc, curr, node, element, Double.toString(value));
    }

    /**
     * Add an element to the curr node with the given string value.
     *
     * @param doc
     * @param curr
     * @param element
     * @param value
     */
    public void addElement (Document doc, Node curr, String element, String value) {
        Element temp = doc.createElement(element);
        temp.appendChild(doc.createTextNode(value));
        curr.appendChild(temp);
    }

    /**
     * Add an element to the curr node with the given double value
     *
     * @param doc
     * @param curr
     * @param element
     * @param value
     */
    public void addElement (Document doc, Node curr, String element, Double value) {
        addElement(doc, curr, element, Double.toString(value));
    }

    /**
     * Convert a list of cells into a list of states separated by commas.
     *
     * @param list
     * @return
     */
    public String listToString (List<Cell> list) {
        String temp = "";
        for (int i = 0; i < list.size() - 1; i++) {
            temp += Integer.toString(list.get(i).getMyCurrentState()) + ",";
        }
        temp += Integer.toString(list.get(list.size() - 1).getMyCurrentState());
        return temp;
    }

}
