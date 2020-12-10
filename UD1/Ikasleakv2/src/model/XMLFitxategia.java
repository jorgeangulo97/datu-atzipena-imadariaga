/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

/**
 *
 * @author angulo.jorge
 */
public class XMLFitxategia {
    
    public static ObservableList<Ikaslea> datuakMemorianKargatu(String fitxategia) throws ParserConfigurationException, SAXException {
        ObservableList<Ikaslea> data;
        
        try{
            // Zuhaitza sortu
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Fitxategikitik abiatuta
            Document document = builder.parse(new File(fitxategia));
            
            
            data = domLista(document);
        }catch (IOException e){
            return FXCollections.observableArrayList();
        }
        return data;
    }
    
    public static boolean datuakFitxategianGorde(ObservableList<Ikaslea> lista, String fitxategia) throws ParserConfigurationException, SAXException, IOException, TransformerConfigurationException, TransformerException {       
        Document document = listaDom(lista);
        
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
    }    
    
    public static ObservableList<Ikaslea> domLista(Document document) { // Pasar document (Estructura) a lista
        ObservableList<Ikaslea> data = FXCollections.observableArrayList();
        NodeList ikasleNodoak = document.getElementsByTagName("ikasle");
        for(int i =0;i<ikasleNodoak.getLength();i++) {
            Node nodoa = ikasleNodoak.item(i);
            Element elemIkaslea=(Element)nodoa;
            int zenbakia = Integer.parseInt(elemIkaslea.getAttribute("zenbakia"));
            String izena = "";
            String abizena = "Desconocido";

            NodeList ikasleNodoarenSemeak = nodoa.getChildNodes();
            for (int j=0;j<ikasleNodoarenSemeak.getLength();j++){
                Node semea = ikasleNodoarenSemeak.item(j);
                if(semea.getNodeName().equals("izena")){
                    izena = ((Element)semea.getChildNodes()).getTextContent();
                } else if(semea.getNodeName().equals("abizena")) {
                    abizena = ((Element)semea.getChildNodes()).getTextContent();
                }
            }

            Ikaslea ikasle = new Ikaslea(zenbakia, izena, abizena);
            data.add(ikasle);
        }
        return data;
    }
    
    public static Document listaDom(ObservableList<Ikaslea> lista) { // Pasar la lista a document (Estructura)
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            return null;
        }
        Document document = builder.newDocument();
        Element root = document.createElement("ikasleak"); // root es el nodo Ikasleak que va a tener los ikasles
        document.appendChild(root);

        for (Ikaslea ikasle : lista ){
            String zenbakia = String.valueOf(ikasle.getZenbakia());
            String izena = ikasle.getIzena();
            String abizena = ikasle.getAbizena1();

            Element elemIkasle = document.createElement("ikasle");

            elemIkasle.setAttribute("zenbakia", zenbakia);
            Element elemIzena = document.createElement("izena");
            Element elemAbizena = document.createElement("abizena");
            Text textIzena = document.createTextNode(izena);
            Text textAbizena = document.createTextNode(abizena);
            elemIkasle.appendChild(elemIzena);
            elemIzena.appendChild(textIzena);
            elemIkasle.appendChild(elemAbizena);
            elemAbizena.appendChild(textAbizena);

            root.appendChild(elemIkasle);

        }
        
        return document;        
    }
}
