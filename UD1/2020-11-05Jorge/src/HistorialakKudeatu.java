
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
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
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author imadariaga
 */
public class HistorialakKudeatu {

    static Document xmlDOM;

    public static void main(String[] args) {
        String xmlFileIn = "Historialak.xml";//XML hasierako fitxategia
        String csvFileIn = "HistorialGehiago.csv";//Gehitu beharreko fitxategiak, .csv formatuan
        String xmlFileOut = "HistorialGuztiak.xml";//Aurreko bien batura
        String jsonFileOut = "Historialak.json";//Sarrera objetuen array bat 

        //Metodoak programatzen joan ala, hurrengo lerroak deskomentatuz joan.          
        //BAT: XML fitxategiko datuak memorian, DOM fitxategi baten kargatu
        xmlDOM = xmlFitxategiaMemorianKargatu(xmlFileIn);

        //BI: CSV fitxategi baten dauzkagun datuak DOM zuhaitzera gehitu
        //datuakGehitu(csvFileIn);
        //HIRU: Orainarte dauden positiboak zenbatu eta inprimatu
        System.out.println("Orainarte daukagun positibo kopurua: " + positiboKopurua());

        /*//LAU: XML formatuko fitxategi baten gorde
        if (historialakXMLnGorde(xmlFileOut)) {
            System.out.println(xmlFileOut + " ondo gorde da.");
        }

        //BOST: Ikaslea eskatu eta bere datuak .csv formatuan gorde.        
        Scanner sc = new Scanner(System.in);
        System.out.print("Zein ikasleren historiala nahi duzu ikusi? ");
        String ikaslea = sc.next();
        ikasleBatenHistorialaCsvra(ikaslea);

        //SEI: Datuak Json formatuan esportatu. Zuzenean DOM zuhaitzetik, StreamParser erabilita
        if (historialakJsonera(jsonFileOut)) {
            System.out.println("Datuak ondo esportatu dira " + jsonFileOut + " fitxategira.");
        }
         */
    }

    public static Document xmlFitxategiaMemorianKargatu(String fitxategia) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(fitxategia));
            return document;
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.err.println("Errore bat gertatu da.");
        }
        return null;
    }

    public static void datuakGehitu(String csvFileIn) {
        BufferedReader br;
        String linea;
        try {
            br = new BufferedReader(new FileReader(csvFileIn));
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            Document document = builder.parse(new File("Historialak.xml"));

            Element root = document.createElement("historiala");
            document.appendChild(root);

            while ((linea = br.readLine()) != null) {
                String[] lerroakZatituta = linea.split(",");

                Element sarrera = document.createElement("sarrera");
                sarrera.setAttribute("id", lerroakZatituta[0]);
                root.appendChild(sarrera);

                Element elemEguna = document.createElement("eguna");
                elemEguna.appendChild(document.createTextNode(lerroakZatituta[1]));
                sarrera.appendChild(elemEguna);

                Element elemIkaslea = document.createElement("ikaslea");
                elemIkaslea.appendChild(document.createTextNode(lerroakZatituta[2]));
                sarrera.appendChild(elemIkaslea);

                Element elemTemperatura = document.createElement("temperatura");
                elemTemperatura.appendChild(document.createTextNode(lerroakZatituta[3]));
                sarrera.appendChild(elemTemperatura);

                if (lerroakZatituta[4] != null) {
                    Element elemKonfinauta = document.createElement("konfinauta");
                    elemKonfinauta.appendChild(document.createTextNode(lerroakZatituta[4]));
                    sarrera.appendChild(elemKonfinauta);
                }

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer;
            transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("Historialak.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(domSource, streamResult);

        } catch (IOException | ParserConfigurationException | TransformerException | SAXException ex) {
            System.err.println("Errore bat gertatu da");
        }
    }

    public static int positiboKopurua() {
        String konfina = "bai";
        int zenbat = 0;
        boolean aurkituta = false;

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("Historialak.xml"));

            NodeList sarreraNodoak = document.getElementsByTagName("sarrera");

            for (int i = 0; i < sarreraNodoak.getLength() && !aurkituta; i++) {
                Element elemSarrera = (Element) sarreraNodoak.item(i);

                String konfinauta = elemSarrera.getElementsByTagName("konfinauta").item(0).getTextContent();

                if (konfinauta.equals(konfina)) {
                    aurkituta = true;
                    zenbat++;
                }
            }

        } catch (IOException | ParserConfigurationException | SAXException ex) {
            System.err.println("Errore bat gertatu da");
        }
        return zenbat;
    }

    public static boolean historialakXMLnGorde(String fitxategia) {
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(fitxategia));

            if (document == null) {
                System.out.println("Error, no se ha podido guardar el fichero.");
                return false;
            } else {
                DOMSource source = new DOMSource(document);
                StreamResult result = new StreamResult(new File(fitxategia));
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(source, result);
                return true;
            }
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
            System.err.println("Errore bat gertatu da.");
        }
        return false;
    }
}
