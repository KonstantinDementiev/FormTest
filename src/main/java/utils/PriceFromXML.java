package utils;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;

public class PriceFromXML {

    public Double findPrice(String article) throws DOMException {
        Double price = 0.0;
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("F:\\JavaProject\\FormTest\\src\\main\\resources\\13092019.xml");

            XPathFactory pathFactory = XPathFactory.newInstance();
            XPath xpath = pathFactory.newXPath();

            XPathExpression expr = xpath.compile("this/prices/elemtnts/elemtnt[@artikul='" + article + "']");

            NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++) {
                Node n = nodes.item(i);
                if (!n.getAttributes().getNamedItem("cena").getTextContent().equals("")){

                    String s = n.getAttributes().getNamedItem("cena").getTextContent().replaceAll(",", ".");
                    price = Double.valueOf(delSpace(s));

                }
            }
        } catch (XPathExpressionException | ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace(System.out);
        }
        return price;
    }

    private String delSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isSpaceChar(s.charAt(i))) sb.append(s.charAt(i));
        }
        return sb.toString();
    }


}
