/**
* Lumbini Parnas
* Human Computer Interaction
* Project 4
* This handles Reading an XML file
*
* */


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;


public class ReadXML{

    //arraylist to store manpages
    private ArrayList<Page> manpages;

    public ReadXML(){}

    public ArrayList getManpages(){
        return manpages;
    }
    //parses man pages from xml file man.xml
    public void initPage() {
        manpages = new ArrayList<Page>();
        //XML parser code from:
        //http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
        try {
            //File fXmlFile = new File("src/Resources/Linux_ManPages.xml");
            InputStream fXmlFile = getClass().getClassLoader().getResourceAsStream("Linux_ManPages.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("page");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    String name = eElement.getElementsByTagName("Name").item(0).getTextContent();
                    String usage = eElement.getElementsByTagName("Usage").item(0).getTextContent();
                    String description = eElement.getElementsByTagName("Description").item(0).getTextContent();
                    String references = eElement.getElementsByTagName("OtherReferences").item(0).getTextContent();
                    String type = eElement.getElementsByTagName("Type").item(0).getTextContent();

                    Page page = new Page(name, usage, description, references, type);
                    this.manpages.add(page);
                    //System.out.println(page);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
