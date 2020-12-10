/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Xml;

import java.io.File;
import java.util.ArrayList;
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

public class ZerotikSortu {

    public static void main(String[] args) throws ParserConfigurationException {

        ArrayList<Ikaslea> ikasleak = new ArrayList<>();

        Ikaslea i1 = new Ikaslea(1, "Alvaro", "Viguera");
        Ikaslea i2 = new Ikaslea(2, "Mikel", "Mañeru");
        Ikaslea i3 = new Ikaslea(3, "Jorge", "Angulo");

        ikasleak.add(i1);
        ikasleak.add(i2);
        ikasleak.add(i3);
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = (Document) documentBuilder.newDocument();

            Element root = document.createElement("Ikasleak");
            document.appendChild(root);

            for (int i = 0; i < ikasleak.size(); i++) {

                Element Ikaslea = document.createElement("Ikaslea");
                root.appendChild(Ikaslea);
                
                Attr attr = document.createAttribute("id");
                attr.setValue(ikasleak.get(i).getZenbakia()+"");
                Ikaslea.setAttributeNode(attr);
                
                Element zenbakia = document.createElement("Zenbakia");
                zenbakia.appendChild(document.createTextNode(ikasleak.get(i).getZenbakia() + ""));
                Ikaslea.appendChild(zenbakia);

                Element izena = document.createElement("Izena");
                izena.appendChild(document.createTextNode(ikasleak.get(i).getIzena()));
                Ikaslea.appendChild(izena);

                Element abizena = document.createElement("Abizena");
                abizena.appendChild(document.createTextNode(ikasleak.get(i).getAbizena1()+ ""));
                Ikaslea.appendChild(abizena);

            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("ikasleak.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(domSource, streamResult);

        } catch (Exception e) {

        }
    }

}
