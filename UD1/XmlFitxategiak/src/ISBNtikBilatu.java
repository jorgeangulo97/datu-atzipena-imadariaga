
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
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
public class ISBNtikBilatu {
    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException {
        String isbn = "013";
        String egilea = "";
        boolean aurkituta = false;

        //DOM zuhaitza sortu
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Fitxategikitik abiatuta
        Document document = builder.parse(new File("Liburuak.xml"));
        
        NodeList liburuNodoak = document.getElementsByTagName("liburu");
        for(int i =0;i<liburuNodoak.getLength() && !aurkituta; i++) { 
            Element elemLiburua = (Element) liburuNodoak.item(i);
            String unekoISBNa = elemLiburua.getAttribute("isbn");
            String unekoLiburuarenEgilea = elemLiburua.getElementsByTagName("egilea").item(0).getTextContent();
            if(unekoISBNa.equals(isbn)){
                aurkituta = true;
                egilea = unekoLiburuarenEgilea;
                
            }
        }
        
        if(aurkituta){
            System.out.println(isbn + " liburuaren ISBN " + egilea + " da.");
        } else {
            System.out.println("Ez daukagu liburu hori.");
        }
    }
}
