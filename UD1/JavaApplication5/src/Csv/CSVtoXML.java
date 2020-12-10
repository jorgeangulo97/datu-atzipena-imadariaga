package Csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class CSVtoXML {

    public static void main(String[] args) {

        BufferedReader br;
        String linea;

        try {
            br = new BufferedReader(new FileReader("mendiak2.csv"));
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = (Document) documentBuilder.newDocument();

            Element root = document.createElement("Mendiak");
            document.appendChild(root);
            while ((linea = br.readLine()) != null) {
                String[] mendiak = linea.split(";");

                Element Mendia = document.createElement("Mendia");
                root.appendChild(Mendia);

                Element izena = document.createElement("izena");
                izena.appendChild(document.createTextNode(mendiak[0]));
                Mendia.appendChild(izena);

                Element altuera = document.createElement("altuera");
                altuera.appendChild(document.createTextNode(mendiak[1]));
                Mendia.appendChild(altuera);

                Element probintzia = document.createElement("probintzia");
                probintzia.appendChild(document.createTextNode(mendiak[2]));
                Mendia.appendChild(probintzia);

            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("mendiak2.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(domSource, streamResult);

        } catch (ExceptionInInitializerError | TransformerException | ParserConfigurationException | IOException ex) {
            System.err.println("Errore bat gertatu da");
        }
    }

}
