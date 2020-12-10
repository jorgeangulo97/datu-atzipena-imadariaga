/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Json;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class JSONtoXML {

    public static void main(String[] args) throws FileNotFoundException {

        JsonReader reader = Json.createReader(new FileReader("Mendiak2.json"));
        JsonStructure jsonst = reader.read();

        JsonArrayBuilder jab = Json.createArrayBuilder();

        for (int i = 0; i < jsonst.asJsonArray().size(); i++) {
            jab.add(jsonst.asJsonArray().get(i));
        }

        JsonArray ar = jab.build();

        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = (Document) documentBuilder.newDocument();

            Element root = document.createElement("Mendiak");
            document.appendChild(root);

            for (int i = 0; i < ar.size(); i++) {

                Element Mendia = document.createElement("Mendia");
                root.appendChild(Mendia);

                Element izena = document.createElement("izena");
                izena.appendChild(document.createTextNode(ar.get(i).asJsonObject().getString("Mendia")));
                Mendia.appendChild(izena);

                Element altuera = document.createElement("altuera");
                altuera.appendChild(document.createTextNode(Integer.toString(ar.get(i).asJsonObject().getInt("Altuera"))));
                Mendia.appendChild(altuera);

                Element probintzia = document.createElement("probintzia");
                probintzia.appendChild(document.createTextNode(ar.get(i).asJsonObject().getString("Probintzia")));
                Mendia.appendChild(probintzia);

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("mendiak2.xml"));
            transformer.transform(domSource, streamResult);

        } catch (Exception e) {

        }
    }

}
