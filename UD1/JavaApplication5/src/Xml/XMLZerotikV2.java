/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * Crear XML desde 0 con mas de un objeto, recorrerlo y pasarlo a csv.
 * 
 */
public class XMLZerotikV2 {

    public static void main(String[] args) {
        ArrayList<Clasea> klaseak = new ArrayList<>();
        ArrayList<Ikaslea> ikasleak = new ArrayList<>();
        ArrayList<Ikaslea> ikasleak2 = new ArrayList<>();

        Ikaslea i1 = new Ikaslea(1, "Alvaro", "Viguera");
        Ikaslea i2 = new Ikaslea(2, "Mikel", "Mañeru");
        Ikaslea i3 = new Ikaslea(3, "Jorge", "Angulo");

        ikasleak.add(i1);
        ikasleak.add(i2);
        ikasleak.add(i3);

        Clasea clase1 = new Clasea("dam", "prueba", ikasleak);

        Ikaslea a1 = new Ikaslea(4, "xd", "xd");
        Ikaslea a2 = new Ikaslea(5, "a", "a");
        Ikaslea a3 = new Ikaslea(6, "b", "b");
        ikasleak2.add(a1);
        ikasleak2.add(a2);
        ikasleak2.add(a3);

        Clasea clase2 = new Clasea("xddd", ikasleak2);

        klaseak.add(clase1);
        klaseak.add(clase2);
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = (Document) documentBuilder.newDocument();

            Element root = document.createElement("Ikasleak");
            document.appendChild(root);

            for (int i = 0; i < klaseak.size(); i++) {
                Element klasea = document.createElement("Klaseak");
                //klasea.appendChild(document.createTextNode(klaseak.get(i).getClasea())); // Para añadir un texto a objeto en si....
                root.appendChild(klasea);
                Attr attr = document.createAttribute("izena");
                Attr attr2 = document.createAttribute("abizena");
                
                attr.setValue(klaseak.get(i).getClasea() + "");
                attr2.setValue(klaseak.get(i).getBiggarenAtrib());
                
                klasea.setAttributeNode(attr);
                klasea.setAttributeNode(attr2);
                
                for (int j = 0; j < klaseak.get(i).getClaseak().size(); j++) {
                    Element ikaslea = document.createElement("Ikaslea");
                    klasea.appendChild(ikaslea);

                    Element zenbakia = document.createElement("Zenbakia");
                    zenbakia.appendChild(document.createTextNode(klaseak.get(i).getClaseak().get(j).getZenbakia() + ""));
                    ikaslea.appendChild(zenbakia);

                    Element izena = document.createElement("Izena");
                    izena.appendChild(document.createTextNode(klaseak.get(i).getClaseak().get(j).getIzena() + ""));
                    ikaslea.appendChild(izena);

                    Element abizena = document.createElement("Abizena");
                    abizena.appendChild(document.createTextNode(klaseak.get(i).getClaseak().get(j).getAbizena1() + ""));
                    ikaslea.appendChild(abizena);
                }

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("ikasleakKlasea.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(domSource, streamResult);

          
        String izena;
        String abizena;
        String zenbakia;
        String claseaAtrib;
        String claseaText;
        
        String linea;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document document2 = builder.parse(new File("ikasleakKlasea.xml"));
            Document documentBerria = builder.newDocument();
            
            FileWriter writer = new FileWriter("ikasleakKlasea.csv");
            
            NodeList ikasleNodoak = document.getElementsByTagName("Ikasleak");
            
            System.out.println("Numero de nodos:" + ikasleNodoak.getLength());
            for (int i = 0; i < ikasleNodoak.getLength(); i++) {
                Node nodoa = ikasleNodoak.item(i);
                NodeList klaseSemak = nodoa.getChildNodes();
                for (int j = 0; j < klaseSemak.getLength(); j++) {
                    Node nodoa2 = klaseSemak.item(j);
                    NodeList ikasleKlasea = nodoa2.getChildNodes();
                    Element ikasleAtrib = (Element) nodoa2;
                    claseaAtrib=ikasleAtrib.getAttribute("izena");                    
                    for(int h=0; h<ikasleKlasea.getLength();h++){
                        Element elemIkaslea = (Element) nodoa2;
                        zenbakia = elemIkaslea.getElementsByTagName("Zenbakia").item(h).getTextContent();
                        izena = elemIkaslea.getElementsByTagName("Izena").item(h).getTextContent();
                        abizena = elemIkaslea.getElementsByTagName("Abizena").item(h).getTextContent();
                        linea = claseaAtrib + ";" + zenbakia + ";" + izena + ";" + abizena + "\n";
                        writer.write(linea);
                        System.out.println("Ikaslea berria sartu da CSVan");
                    }
                   
                }

            }
            writer.close();
            System.out.println("CSV fitxategia sortu da");

        } catch (IOException | ParserConfigurationException | SAXException ex) {
            System.err.println("Errore bat gertatu da");
        }

        } catch (Exception e) {

        }

    }
}
