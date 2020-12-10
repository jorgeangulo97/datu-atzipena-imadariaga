
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
public class EgileaBilatu {
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        String izenburua = "La sombra del viento";
        String egilea = "";
        boolean aurkituta = false;
        
        // Zuhaitza sortu
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        // Fitxategikitik abiatuta
        Document document = builder.parse(new File("Liburuak.xml"));
        
        //Zuhaitza irakurri
        NodeList liburuNodoak = document.getElementsByTagName("liburu");
        for(int i =0;i<liburuNodoak.getLength() && !aurkituta; i++) { // para cada libro.
            Element elemLiburua = (Element) liburuNodoak.item(i);
            String unekoIzenburua = elemLiburua.getElementsByTagName("izenburua").item(0).getTextContent();
            String unekoEgilea = elemLiburua.getElementsByTagName("egilea").item(0).getTextContent();
            if (unekoIzenburua.equals(izenburua)){
                  aurkituta = true;
                  egilea = unekoEgilea;
            }
            
            /*Node nodoa = liburuNodoak.item(i);
            NodeList liburuNodoarenSemeak = nodoa.getChildNodes();
            for (int j=0;j<liburuNodoarenSemeak.getLength();j++){ // para cada hijo del libro (izenburua, egilea)
                Node semea = liburuNodoarenSemeak.item(j);
                if (semea.getNodeName() == "izenburua"){
                    String unekoLiburuarenIzenburua = semea.getTextContent();
                    if(izenburua.equals(unekoLiburuarenIzenburua)){
                        aurkituta = true;
                    }                    
                }
                if(semea.getNodeName() == "egilea" && aurkituta == true){
                    egilea = semea.getTextContent();
                }
            }*/            
        }
        
        if(aurkituta){
            System.out.println(izenburua + " liburuaren egilea " + egilea + " da.");
        } else {
            System.out.println("Ez daukagu liburu hori.");
        }
    }
}
