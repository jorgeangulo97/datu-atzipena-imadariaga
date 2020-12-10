/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgFitxategiak;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 *
 * @author maneru.mikel
 */
public class LiburuGehituUrtea {
     public static Document document;
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerConfigurationException, TransformerException {
        int urtea=2019;
        String isbn="023";
        String izenburu="prueba2";
        String egilea="jorge";
     
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Fitxategikitik abiatuta
        document = builder.parse(new File("Liburuak.xml"));
        if(comprobarUrtea(urtea)){
             gehitu(isbn, izenburu, egilea,urtea);
            System.out.println("Gehitu dut!");            
        }else{
            System.out.println("Urtea handiagoa da !");
        }             
        
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new java.io.File("Liburuak5.xml"));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");   
        transformer.transform(source, result);
    }
    public static boolean comprobarUrtea(int urtea){        
        LocalDate data = java.time.LocalDate.now();
        
        String dataFinal= String.valueOf(data);
        
       String datak[] = dataFinal.split("-");
       if(Integer.parseInt(datak[0]) >= urtea ){
           return true;
       }        
        return false;        
    }
    
  
   
    
    public static boolean gehitu(String isbn,String izenburu,String egilea,int urtea){       
        Element elemLiburu= document.createElement("liburu");
        elemLiburu.setAttribute("isbn", isbn);
        Element elemIzenburu=document.createElement("izenburu");
        Element elemEgile=document.createElement("egile");
        Element elemUrtea=document.createElement("urtea");
        Text textIzenburu=document.createTextNode(izenburu);
        Text textEgile=document.createTextNode(egilea);
        Text textUrtea=document.createTextNode(String.valueOf(urtea));
        document.getDocumentElement().appendChild(elemLiburu);
        elemLiburu.appendChild(elemIzenburu);
        elemIzenburu.appendChild(textIzenburu);
        elemLiburu.appendChild(elemEgile);
        elemEgile.appendChild(textEgile);
        elemLiburu.appendChild(elemUrtea);
        elemUrtea.appendChild(textUrtea);   
        
        return true;
    }
}
