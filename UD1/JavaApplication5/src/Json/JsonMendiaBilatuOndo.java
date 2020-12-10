/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Json;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;

/**
 *
 * @author Mikel
 */
public class JsonMendiaBilatuOndo {

    public static void main(String[] args) throws IOException {
        JsonParser parser;
        String azkenIzena = "";
        int azkenAltuera = 0;
        String azkenProbintzia = "";
        try {
            FileWriter writer = new FileWriter("jsonMendiak500.json");
            JsonGenerator gen = Json.createGenerator(writer);
            gen.writeStartArray();
            try {
                parser = Json.createParser(new FileReader("datuberriak.json"));
                while (parser.hasNext()) {
                    JsonParser.Event event = parser.next();

                    if (event == JsonParser.Event.START_OBJECT) { //Mendiaren hasiera
                        parser.next();
                        parser.next();
                        azkenIzena = parser.getString();
                        parser.next();
                        parser.next();
                        azkenAltuera = parser.getInt();
                        parser.next();
                        parser.next();
                        azkenProbintzia = parser.getString();
                        if (azkenAltuera > 500) {
                            System.out.println("Mendia:" + azkenIzena + " / " + "Altuera:" + azkenAltuera + " / " + "Probintzia:" + azkenProbintzia);
                            gen.writeStartObject()
                                    .write("Mendia", azkenIzena)
                                    .write("Altuera", azkenAltuera)
                                    .write("Probintzia", azkenProbintzia)
                                    .writeEnd();
                        }
                    }

                }
                gen.writeEnd();
                gen.close();

            } catch (IOException ex) {
                Logger.getLogger(JsonMendiaBilatuOndo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(JsonMendiaBilatuOndo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
