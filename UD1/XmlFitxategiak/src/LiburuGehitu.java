
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
 * @author angulo.jorge
 */
public class LiburuGehitu {
        private static Document document;
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        String isbn = "015";
        String izenburua = "Prueba";
        String egilea = "Jorge";
        
        
        // Zuhaitza sortu
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Fitxategikitik abiatuta
        document = builder.parse(new File("Liburuak.xml"));
        
        if (comprobarISBN(isbn)){
            System.out.println(isbn + "ISBN hori existitzen da.");
            return;
        }else{
            gehitu(isbn, izenburua, egilea);
        }
        
        DOMSource source = new DOMSource(document);
        
        StreamResult result = new StreamResult(new java.io.File("Liburuak5.xml"));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");   
        transformer.transform(source, result);
    }
    
    public static boolean comprobarISBN(String isbn){
        NodeList liburuNodoak = document.getElementsByTagName("liburu");
        for(int i =0;i<liburuNodoak.getLength(); i++) { 
            Element elemLiburua = (Element) liburuNodoak.item(i);
            String unekoISBNa = elemLiburua.getAttribute("isbn");
            if(unekoISBNa.equals(isbn)){
                return true;               
            }
        }
        return false;
    }
    
    public static boolean gehitu(String isbn, String izenburua, String egilea){
        Element elemLiburu = document.createElement("liburu");
        elemLiburu.setAttribute("isbn", isbn);
        Element elemIzenburu = document.createElement("izenburu");
        Element elemEgilea = document.createElement("egilea");
        Text textIzenburu = document.createTextNode(izenburua);
        Text textEgilea = document.createTextNode(egilea);
        document.getDocumentElement().appendChild(elemLiburu);
        elemLiburu.appendChild(elemIzenburu);
        elemIzenburu.appendChild(textIzenburu);
        elemLiburu.appendChild(elemEgilea);
        elemEgilea.appendChild(textEgilea);       
        
        return true;
    }
}
