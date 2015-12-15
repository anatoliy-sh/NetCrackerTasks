package parse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Created by Анатолий on 30.11.2015.
 */
public class XPathCl {
    public static void makeXpath(){
        try {
            FileInputStream file = new FileInputStream(new File("output.xml"));

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

             DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(file);

            javax.xml.xpath.XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/citizens/creature/listItems/name";
            System.out.println(expression);
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public static void returnItems(int id){
        try {
            FileInputStream file = new FileInputStream(new File("output.xml"));

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document xmlDocument = builder.parse(file);

            javax.xml.xpath.XPath xPath = XPathFactory.newInstance().newXPath();

            String expression = "/citizens/creature[@id = "+id+"]/listItems/*/text()";
            System.out.println(expression);
            NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);
            //System.out.println(nodeList.item(0).getChildNodes().getLength());
            System.out.println(nodeList.item(1));
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.println(nodeList.item(i));
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
