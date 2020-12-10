/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Xml;

import java.io.File;
import java.io.FileWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLtoCSV {

    public static void main(String[] args) {
        String izena;
        String abizena;
        String zenbakia;
        String linea;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("ikasleak.xml"));
            
            try (FileWriter writer = new FileWriter("ikasleak.csv")) {
                NodeList ikasleNodoak = document.getElementsByTagName("Ikaslea");
                for(int i=0; i<ikasleNodoak.getLength();i++){
                    Node nodoa = ikasleNodoak.item(i);
                    Element elemIkaslea = (Element) nodoa;
                    zenbakia= elemIkaslea.getAttribute("id");
                    // En el caso de que haya mas de un nombre, recorrer 
                    /*NodeList nl = elemIkaslea.getElementsByTagName("Izena");
                    for...
                    Node n = nl.item(i);
                    String s = n.getTextContent();
                    */
                    izena= elemIkaslea.getElementsByTagName("Izena").item(0).getTextContent();
                    abizena=elemIkaslea.getElementsByTagName("Abizena").item(0).getTextContent();
                    
                    linea= zenbakia + ";" + izena + ";" + abizena + "\n";
                    writer.write(linea);
                    System.out.println("Ikaslea berria sartu da CSVan");
                    
                }
            }
            System.out.println("CSV fitxategia sortu da");

        } catch (Exception e) {

        }
    }
            
}
