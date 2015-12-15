package parse; /**
 * Created by Анатолий on 29.11.2015.
 */
import org.jdom.*;
import org.jdom.Element;
import org.jdom.output.*;
import org.jdom.input.*;

import javax.xml.bind.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.util.List;
import java.io.FileWriter;


public class Main {

    private static int i;

    public static void main(String[] args){
        i=0;
        createXML();
        check();
        parseDom();
        XPathCl.makeXpath();
        XPathCl.returnItems(0);
    }

    public static void createXML(){
        Element citizens = new Element("citizens");
        Document myDocument = new Document(citizens);

        citizens.addContent(returnCreature("Ork", "Worker", "Ork1", "City", "4"));
        citizens.addContent(returnCreature("Human", "Worker", "Human1", "City", "6"));
        citizens.addContent(returnCreature("Ork", "Warrior", "Ork2", "City", "6"));
        citizens.addContent(returnCreature("Human", "Warrior", "Human2", "City", "8"));

        try {
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat());
            FileWriter fw = new FileWriter("output.xml");
            outputter.output(myDocument, fw);
            fw.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static Element returnCreature(String race, String clas, String name, String city, String livedays){
        Element creature = new Element("creature");
        creature.setAttribute("id",""+(i++));
        Element listItems = new Element("listItems");
        listItems.addContent(new Element("race").addContent(race));
        listItems.addContent(new Element("class").addContent(clas));
        listItems.addContent(new Element("name").addContent(name));
        listItems.addContent(new Element("city").addContent(city));
        listItems.addContent(new Element("livedDays").addContent(livedays));
        creature.addContent(listItems);
        return creature;
    }

    public static void check(){
        try
        {
            File xmlFile = new File("output.xml");
            File xsdFile = new File("classes.xsd");
            SAXBuilder builder = new SAXBuilder();
            builder.setFeature(
                    "http://apache.org/xml/features/validation/schema", true
            );
            builder.setProperty("http://apache.org/xml/properties/schema"
                            + "/external-noNamespaceSchemaLocation", xsdFile.toURL().toString());

            Document doc = builder.build(xmlFile);


            System.out.println("Successfully parsed and validated");
        }
        catch ( Exception cause ) {
            System.err.println(cause.toString());
        }
    }

    public static void parseDom(){

        try {
            SAXBuilder parser = new SAXBuilder();
            FileReader fr = new FileReader("output.xml");
            Document rDoc = parser.build(fr);
            System.out.println(rDoc.getRootElement().getName());
            List allChildren = rDoc.getRootElement().getChildren();
            for (int i = 0;  i<allChildren.size(); ++i) {
                Element current = ((Element) allChildren.get(i));
                System.out.println(current.getChild("race").getContent());
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


}

