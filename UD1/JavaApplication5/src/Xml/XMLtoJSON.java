/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLtoJSON {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        String izena;
        String abizena;
        String zenbakia;
        JsonArrayBuilder jab = Json.createArrayBuilder();//1.

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("ikasleak.xml"));
            
            NodeList ikasleNodoak = document.getElementsByTagName("Ikaslea");
            for (int i = 0; i < ikasleNodoak.getLength(); i++) {
                Node nodoa = ikasleNodoak.item(i);
                Element elemIkaslea = (Element) nodoa;
                zenbakia = elemIkaslea.getAttribute("id");
                izena = elemIkaslea.getElementsByTagName("Izena").item(0).getTextContent();
                abizena = elemIkaslea.getElementsByTagName("Abizena").item(0).getTextContent();
                
                JsonObject model = Json.createObjectBuilder()
                   .add("Izena", izena)
                   .add("Abizena", abizena)
                   .add("Zenbakia", zenbakia)
                   .build();
                
                jab.add(model);
            }
             
            JsonArray model2 = jab.build();//4.
  
           
            FileWriter fw = new FileWriter("ikasleak.json");
            JsonWriter jsonwriter = Json.createWriter(fw);
                jsonwriter.writeArray(model2);
                jsonwriter.close();
                fw.close();     
        } catch (Exception e) {

        }

    }
}
